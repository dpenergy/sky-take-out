package com.wudd.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect // AspectJ的注解
@Component // SpringBoot的原生注解，被扫描，aspect注解和component注解都必须添加上Aspect类才会生效
public class ControllerLogAspect {
    // 用于给controller层统一添加模板日志
    // 1. 记录请求参数的具体情况
    // 2. 总结该次请求，操作，成败，操作时长

    @Pointcut("execution(* com.wudd.controller.*.*(..))")
    public void controllerPt(){}

    @Around("controllerPt()")
    public Object addControllerLog(ProceedingJoinPoint pjp) throws Throwable { // pjp对象封装了连接点（目标方法）上下文信息
        Long start = System.currentTimeMillis();
        Object result = pjp.proceed(); // 可能抛出异常
        Long end = System.currentTimeMillis();

        // 合成日志信息
        // 1. 获取全类名
        Object targetClass = pjp.getTarget();
        String classAllName = targetClass.getClass().getName();

        // 2. 获取方法名，参数名和具体参数值
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String methodName = methodSignature.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = pjp.getArgs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            sb.append(parameterNames[i]+"="+args[i].toString());
            if (i != parameterNames.length - 1) {
                sb.append(", ");
            }
        }

        String argsStr = sb.toString();

        // 输出日志
        log.info("总结：{}:{}({}) 耗时{}ms", classAllName, methodName, argsStr, end-start);

        return  result;
    }
}
