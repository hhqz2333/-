package com.lv.sell.service.impl;

import com.lv.sell.dataobject.ProductInfo;
import com.lv.sell.enums.ProductStatueEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void getOne() throws Exception {
        ProductInfo productInfo=productInfoService.getOne("1233");
        System.out.println(productInfo);
        Assert.assertEquals("1233",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list=productInfoService.findUpAll(ProductStatueEnums.UP.getCode());
        System.out.println(list);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<ProductInfo> list=productInfoService.findAll(request);
        System.out.println(list);
        System.out.println(list.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo  productInfo=new ProductInfo();
        productInfo.setProductId("133");
        productInfo.setProductName("can");
        productInfo.setCategoryType(2);
        productInfo.setProductPrice(new BigDecimal(123));
        productInfo.setProductDescription("asyhcejicjapi");
        productInfoService.save(productInfo);
    }

}