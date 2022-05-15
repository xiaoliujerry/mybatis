package com.jiangnan.common;

import lombok.Data;

@Data
public class Response<T> {

    private T data;

    private Boolean success;

    private int code;

    private String message;

    public static <T> Response<T> of(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        response.setSuccess(true);
        response.setCode(ResCodeEnum.SUCCESS.getCode());
        response.setMessage(ResCodeEnum.SUCCESS.getMessage());
        return response;
    }

    public static <T> Response<T> fail(ResCodeEnum codeEnum) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(codeEnum.getCode());
        response.setMessage(codeEnum.getMessage());
        return response;
    }

    public static <T> Response<T> fail(int code, String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
