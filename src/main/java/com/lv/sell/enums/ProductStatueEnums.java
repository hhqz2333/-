package com.lv.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductStatueEnums {
    UP(0,"上架"),
    DOWN(1,"下架");
    private Integer code;
    private String messgae;

    ProductStatueEnums(Integer code, String messgae) {
        this.code = code;
        this.messgae = messgae;
    }
}
