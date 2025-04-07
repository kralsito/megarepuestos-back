package com.megarepuestos.megarepuestos.exception.error;

public enum Error implements ErrorCode {
    AUTH_ERROR("0001", "Error al iniciar sesion"),
    USER_NOT_LOGIN("0002", "No hay ningun usuario logueado"),
    PRODUCT_NOT_FOUND("0003", "No se encontró el producto"),
    PHONE_NUMBER_ALREADY_REGISTERED("0004", "El número de teléfono ya se encuentra registrado"),
    FORM_NOT_FOUND("0005", "No se encontró el formulario"),
    BRAND_NOT_FOUND("0006", "No se encontró la marca"),
    TYPE_REPLACEMENT_NOT_FOUND("0007", "No se encontró el tipo de repuesto"),
    ;

    private final String code;

    private final String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }
}
