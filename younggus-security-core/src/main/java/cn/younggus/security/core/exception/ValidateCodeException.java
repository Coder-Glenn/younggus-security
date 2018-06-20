package cn.younggus.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Glenn.Zheng
 * @date 2018/6/20 22:36
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
