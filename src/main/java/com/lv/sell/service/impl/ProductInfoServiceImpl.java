package com.lv.sell.service.impl;

import com.lv.sell.dataobject.ProductInfo;
import com.lv.sell.dto.CartDto;
import com.lv.sell.enums.ProductStatueEnums;
import com.lv.sell.enums.ResultEnum;
import com.lv.sell.excetion.SellExcetion;
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
        return productInfoRepository.getOne(productId);
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

    /**
     * 加库存
     * @param cartDtoList
     */
    @Override
    public void addinStock(List<CartDto> cartDtoList) {
        if (cartDtoList.size() > 0) {
            cartDtoList.forEach(o-> {
                ProductInfo productInfo = productInfoRepository.getOne(o.getProductId());
                if (productInfo == null) {
                    throw new SellExcetion(ResultEnum.PRODUCT_NOT_EXIST);
                }
                Integer num = productInfo.getProductStock() + o.getProductStock();
                productInfo.setProductStock(num);
                productInfoRepository.save(productInfo);
            });
        }
    }

    /**
     * 减库存
     * @param cartDtoList
     */
    @Override
    public void reduceStock(List<CartDto> cartDtoList) {
        if (cartDtoList.size() > 0) {
            cartDtoList.forEach(o-> {
                ProductInfo productInfo = productInfoRepository.getOne(o.getProductId());
                if (productInfo == null) {
                    throw new SellExcetion(ResultEnum.PRODUCT_NOT_EXIST);
                }
                Integer num = productInfo.getProductStock() - o.getProductStock();
                if (num < 0) {
                    throw new SellExcetion(ResultEnum.PRODUCT_NUM_ERR);
                } else {
                    productInfo.setProductStock(num);
                    productInfoRepository.save(productInfo);
                }
            });
        }

    }
}
