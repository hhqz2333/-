package com.lv.sell.controller;

import com.lv.sell.dataobject.ProductCategory;
import com.lv.sell.dataobject.ProductInfo;
import com.lv.sell.enums.ProductStatueEnums;
import com.lv.sell.service.impl.ProductCategoryServiceImpl;
import com.lv.sell.service.impl.ProductInfoServiceImpl;
import com.lv.sell.until.ResultUntilVo;
import com.lv.sell.vo.ProductInfoVO;
import com.lv.sell.vo.ProductVO;
import com.lv.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/24 0024  10:43
 * @modified By：
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVo list() {
        //查询上架的商品
        List<ProductInfo> productInfos = productInfoService.findUpAll(ProductStatueEnums.UP.getCode());
        //查询类目
        List<Integer> productCategoryList = productInfos.stream().map(m -> m.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.findBycategoryTypeIn(productCategoryList);
        //封装数据
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            ProductVO pRO = new ProductVO();
            pRO.setCategoryName(productCategory.getCategoryName());
            pRO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType() == productCategory.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            pRO.setProductInfoVOList(productInfoVOS);
            productVOS.add(pRO);
        }
        return ResultUntilVo.success(productVOS);

    }
}
