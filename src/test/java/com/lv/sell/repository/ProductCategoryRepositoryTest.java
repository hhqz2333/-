package com.lv.sell.repository;

import com.lv.sell.SellApplication;
import com.lv.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void test(){
        List<Integer> list= Arrays.asList(23,33,4,5,6);
        /*ProductCategory productCategory= productCategoryRepository.getOne(1);
        System.out.println(productCategory.toString());*/
        List<ProductCategory> productList=productCategoryRepository.findBycategoryTypeIn(list);
        Assert.assertNotEquals(0,productList);
    }
    @Test
    public void save(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("吕航");
        productCategory.setCategoryType(23);
        productCategoryRepository.save(productCategory);
    }

}