package com.lv.sell.excetion;

import com.lv.sell.enums.ResultEnum;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/24 0024  20:48
 * @modified By：
 */
public class SellExcetion extends RuntimeException {
    private String code;

    public SellExcetion(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = code;
    }
    public SellExcetion(ResultEnum resultEnum, String message) {
        super(message);
        this.code=code;
    }
}
