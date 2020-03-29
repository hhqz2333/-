package com.lv.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_NUM_ERR(11, "商品库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    UPDATA_ORDER_STATUS_FAIL(15,"修改订单状态失败"),
    ORDER_PAY_STATUS_ERROR(16,"支付状态不正确"),
    ORDER_PAY__FAIL(17,"修改支付状态失败"),
    PARAMETER_CHECKING_ERROR(18,"参数校验失败"),
    SHOPPING_CART_CANNOT_BE_EMPTY(19,"购物车不能为空"),
    OPENID_NOT_NULL(20,"openid不能为空"),
    ;
    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
