package org.sample.website.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sample.website.exception.AuthenticationException;
import org.sample.website.service.JwtUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Order(2)
@Configuration
public class UserAuthenticator {

    private final JwtUserAuthenticationService jwtUserAuthenticationService;

    private static final String BEARER = "Bearer ";

    @Autowired
    public UserAuthenticator(JwtUserAuthenticationService jwtUserAuthenticationService) {
        this.jwtUserAuthenticationService = jwtUserAuthenticationService;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.length() > BEARER.length()) {
            return authorization.substring(BEARER.length());
        }
        return null;
    }

    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    @Pointcut("@annotation(org.sample.website.annotation.Authentication) && execution(public org.springframework.http.ResponseEntity org.sample.website.web.*.*(..))")
    public void controllerMethod() {}

    @Around("controllerMethod()")
    public Object authenticate(ProceedingJoinPoint jp) throws Throwable {
        String token = extractTokenFromRequest(getCurrentRequest());
        // TOKEN为空或者验证TOKEN时抛出异常都将直接跳转到ExceptionHandler
        if (token != null) {
            jwtUserAuthenticationService.verifyAndSetCurrentUser(token);
            Object obj = jp.proceed();
            jwtUserAuthenticationService.removeCurrentUser(); // 其实放在这里不太合适，后期考虑将多个切面合并起来。
            return obj;
        } else {
            throw new AuthenticationException("尚未登陆");
        }
    }
}
