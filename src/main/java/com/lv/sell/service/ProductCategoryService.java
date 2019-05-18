package com.lv.sell.service;

import com.lv.sell.dataobject.ProductCategory;

import java.util.List;

/**

* @Description:    类目

* @Author:         LV hang

* @CreateDate:     2019/3/6 0006 21:04

*/
public interface ProductCategoryService {
    //查询单条数据
    ProductCategory getOne(Integer id);

    //查询多条数据
    List<ProductCategory> fondAll();

    //买家端用到的
    List<ProductCategory> findBycategoryTypeIn(List<Integer> list);

    //保存
    void save(ProductCategory productCategory);
}
