package com.wte.be.wte.util;

public enum StatusEnum {
    OK("OK", 200),
    BAD_REQUEST("BAD_REQUEST", 400),
    NOT_FOUND( "NOT_FOUND", 404),
    INTERNAL_SERER_ERROR("INTERNAL_SERVER_ERROR", 500);

    int statusCode;
    String code;

    StatusEnum(String code, int statusCode) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
