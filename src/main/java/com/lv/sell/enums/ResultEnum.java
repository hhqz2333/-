package com.lv.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ORDER_NOT_EXIST(10,"订单不存在"),
    ;
    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
