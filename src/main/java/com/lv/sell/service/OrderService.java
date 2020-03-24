package com.lv.sell.service;

import com.lv.sell.dataobject.OrderMaster;
import com.lv.sell.dto.OrderMasterDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/23 0023 21:09
 * @modified By：
 */
public interface OrderService {
    // 创建订单
    OrderMasterDto insertOrder(OrderMasterDto orderMasterDto);
    // 查询单个订单
    OrderMasterDto selectByOrderId(String orderId);
    // 查询多个订单列表
    List<OrderMasterDto> sleectAll(String buyerOpenid, Pageable pageable);
    // 取消订单
    OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto);
    // 完结订单
    OrderMasterDto finshOrder(OrderMasterDto orderMasterDto);
    // 支付订单
    OrderMasterDto payOrder(OrderMasterDto orderMasterDto);
}
