package cn.younggus.security.demo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Glenn.Zheng
 * @date 2018/5/10 20:52
 * doc: docs.spring.io
 * 切入点：
 *      1. 在哪些方法上起作用
 *      2. 什么时候起作用(@Before() @After() @AfterThrowing @Around())
 * 增强(方法)
 *      1. 起作用时执行的业务逻辑(execution(返回类型 类全路径.方法名(方法参数)))
 */
@Aspect
@Component
public class TimeAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* cn.younggus.security.demo.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("handleControllerMethod");

        System.out.println("time aspect start");
        Long start = new Date().getTime();

        Object[] args = pjp.getArgs(); //获取方法参数
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }
        Object result = pjp.proceed();

        System.out.println("time aspect spend: " + (new Date().getTime() - start));
        System.out.println("time aspect finish");

        return result;
    }
}
