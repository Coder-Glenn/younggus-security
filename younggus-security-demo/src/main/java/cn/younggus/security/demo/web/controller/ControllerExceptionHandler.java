package cn.younggus.security.demo.web.controller;

import cn.younggus.security.demo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Glenn.Zheng
 * @date 2018/5/9 22:59
 */

/**
 * 监听并处理所有controller的异常
 * 该Controller并不处理web 请求
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody //response转成json
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerUserNotExistException(UserNotExistException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("id", ex.getId());
        errors.put("message", ex.getMessage());
        return errors;
    }
}
