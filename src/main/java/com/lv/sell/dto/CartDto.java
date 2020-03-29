package com.lv.sell.dto;

import lombok.Data;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/25 0025  20:30
 * @modified By：
 */
@Data
public class CartDto {
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 库存
     */
    private Integer productStock;

    public CartDto(String productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }
    public CartDto() {
        this.productId = productId;
        this.productStock = productStock;
    }
}
