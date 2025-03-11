package com.megarepuestos.megarepuestos.exception.custom;


import com.megarepuestos.megarepuestos.exception.error.ErrorCode;

public class BadRequestException extends ServiceException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
