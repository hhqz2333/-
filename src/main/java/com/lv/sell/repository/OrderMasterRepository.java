package com.lv.sell.repository;

import com.lv.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：LV hang
 * @date ：Created in 2019/5/20 0020 14:32
 * @modified By：
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster ,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
