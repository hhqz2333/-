package com.lv.sell.repository;

import com.lv.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/23 0023 22:51
 * @modified By：
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 查新上架商品。
     *@param
     *@return
     */

    List<ProductInfo> findByproductStatus(Integer productStatue);
}
