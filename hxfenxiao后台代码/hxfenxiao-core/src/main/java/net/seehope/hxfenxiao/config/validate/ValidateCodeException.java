package net.seehope.hxfenxiao.config.validate;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ValidateCodeException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

}
