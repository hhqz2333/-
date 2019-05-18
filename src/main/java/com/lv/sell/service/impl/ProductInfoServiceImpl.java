package com.lv.sell.service.impl;

import com.lv.sell.dataobject.ProductInfo;
import com.lv.sell.enums.ProductStatueEnums;
import com.lv.sell.repository.ProductInfoRepository;
import com.lv.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/23 0023  23:27
 * @modified By：
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo getOne(String productId) {
        return productInfoRepository.getOne("123");
    }

    @Override
    public List<ProductInfo> findUpAll(Integer productStatue) {
        return productInfoRepository.findByproductStatus(ProductStatueEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}
