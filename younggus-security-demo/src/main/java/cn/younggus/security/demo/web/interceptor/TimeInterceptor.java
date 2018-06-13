package cn.younggus.security.demo.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Glenn.Zheng
 * @date 2018/5/9 23:41
 */
/**
 * 还需要额外的配置才能是interceptor生效
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        logger.info("preHandle");
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        System.out.println("class name: " + ((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println("method name: " + ((HandlerMethod)handler).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        //请求执行完之后执行，抛出异常时不执行
        logger.info("postHandle");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor spend: " + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {
        //无论如何都会执行
        logger.info("afterCompletion");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor spend: " + (new Date().getTime() - start));
        System.out.println("exception is " + ex);
    }
}
