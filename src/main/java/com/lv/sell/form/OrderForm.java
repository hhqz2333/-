package com.lv.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/29 0029  16:04
 * @modified By：
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;
    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;
    /**
     * 买家地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;
    /**
     * openid
     */
    @NotEmpty(message = "openid不能为空")
    private String openid;
    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
