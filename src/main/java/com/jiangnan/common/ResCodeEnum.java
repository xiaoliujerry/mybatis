package com.jiangnan.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResCodeEnum {

    SUCCESS(200, "success");

    private int code;

    private String message;
}
