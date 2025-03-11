package com.megarepuestos.megarepuestos.exception.custom;


import com.megarepuestos.megarepuestos.exception.error.ErrorCode;

public class UnauthorizeException extends ServiceException {

    public UnauthorizeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnauthorizeException(String code, String message) {
        super(code, message);
    }
}
