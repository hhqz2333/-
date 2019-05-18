package com.lv.sell.repository;

import com.lv.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void save(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1233");
        productInfo.setProductName("周周");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);
        productInfo.setProductPrice(new BigDecimal(123));
        productInfo.setProductStock(23);
        productInfo.setProductDescription("asyhcejicjapi");
        ProductInfo result= productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByproductStatue() throws Exception {
        List<ProductInfo> list=productInfoRepository.findByproductStatus(0);
        System.out.println(list);
        Assert.assertNotEquals(0,list);

    }

}