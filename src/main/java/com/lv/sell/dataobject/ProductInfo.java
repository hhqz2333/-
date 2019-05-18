package com.lv.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/22 0022  21:24
 * @modified By：
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private int productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编码
     */
    private int categoryType;

    /**
     *0表示正常，1表示下架。
     */
    private int productStatus;


}
