package com.lv.sell.service;

import com.lv.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/23 0023  23:19
 * @modified By：
 */
public interface ProductInfoService {


    /**
     * 查询一个
     * @param productId
     * @return
     */
    ProductInfo getOne(String productId);


    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll(Integer productStatue);


    /**
     * 查询所有商品()分页
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);


    /**
     * 保存
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     *@param
     *@return
     */

    /**
     * 减库存
     *@param
     *@return
     */




}
