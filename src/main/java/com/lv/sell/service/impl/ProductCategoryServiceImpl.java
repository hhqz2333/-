package com.lv.sell.service.impl;

import com.lv.sell.dataobject.ProductCategory;
import com.lv.sell.repository.ProductCategoryRepository;
import com.lv.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory getOne(Integer id) {
        return productCategoryRepository.getOne(id);
    }

    @Override
    public List<ProductCategory> fondAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findBycategoryTypeIn(List<Integer> list) {
        return productCategoryRepository.findBycategoryTypeIn(list);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }
}
