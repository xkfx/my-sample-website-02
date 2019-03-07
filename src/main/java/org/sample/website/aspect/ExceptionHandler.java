package org.sample.website.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
public class ExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @Pointcut("execution(public org.springframework.http.ResponseEntity org.sample.website.web.*.*(..))")
    public void controllerMethod() {}

    @Around("controllerMethod()")
    public Object handle(ProceedingJoinPoint jp) throws Throwable {
        ResponseEntity<?> result;
        try {
            // 基于平台不同校验方式也不同
            // if (需要认证("xx"))
            // if (getSubject(Http).hasPermissonOf(jp.ges)) {
            // } else
            // 从HTTP中解析出JWT来
            // 用户表、角色表、角色X权限组、权限组X权限、权限
            //
            result = (ResponseEntity<?>) jp.proceed();
        } catch (Throwable e) {
            Map<String, Object> nodes = new HashMap<>();
            if (e instanceof ValidationException) {
                nodes.put("message", e.getMessage());
                result = new ResponseEntity<>(nodes, HttpStatus.BAD_REQUEST);
            } else {
                LOGGER.error("", e);
                nodes.put("message", "服务器内部错误！");
                result = new ResponseEntity<>(nodes, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return result;
    }
}
