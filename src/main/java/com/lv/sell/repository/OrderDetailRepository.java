package com.lv.sell.repository;

import com.lv.sell.dataobject.OrderDetail;
import com.sun.deploy.ref.AppModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/21 0021 17:29
 * @modified By：
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String oid);
}
