package com.lv.sell.service.impl;

import com.lv.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Test
    public void getOne() throws Exception {
        System.out.println(666);
        ProductCategory productCategory= productCategoryService.getOne(2);
        System.out.println(productCategory.toString());
      /*  Assert.assertEquals(1,productCategory.getCategoryId());*/
    }

    @Test
    public void fondAll() throws Exception {
        List<ProductCategory> list =productCategoryService.fondAll();
        System.out.println(list);
    }

    @Test
    public void findBycategoryTypeIn() throws Exception {
        List<Integer> typeList=Arrays.asList(23,33);
        List<ProductCategory> list =productCategoryService.findBycategoryTypeIn(typeList);
        System.out.println(list);
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("23");
        productCategory.setCategoryType(6);
        productCategoryService.save(productCategory);
    }

}