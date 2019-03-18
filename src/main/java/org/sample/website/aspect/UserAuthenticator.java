package org.sample.website.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sample.website.exception.AuthenticationException;
import org.sample.website.service.UserAuthenticationService;
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

    private final UserAuthenticationService authenticationService;

    private static final String BEARER = "Bearer ";

    @Autowired
    public UserAuthenticator(UserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
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
        System.out.println("token actually: " + token);
        if (token != null) {
            authenticationService.verifyAndSetCurrentUser(token);
            return jp.proceed();
        } else {
            throw new AuthenticationException("请先登陆");
        }
    }
}
