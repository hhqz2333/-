package com.lv.sell.service.impl;

import com.lv.sell.dataobject.OrderMaster;
import com.lv.sell.dto.OrderMasterDto;
import com.lv.sell.enums.ResultEnum;
import com.lv.sell.excetion.SellExcetion;
import com.lv.sell.service.BuyerServer;
import com.lv.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LV hang
 * @date ：Created in 2020/4/6 0006  18:06
 * @modified By：
 */
@Service
@Slf4j
public class BuyerServerImpl implements BuyerServer {
    @Autowired
    private OrderService orderService;

    /**
     * 查询订单详情时校验
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderMasterDto chenkDetail(String openid, String orderId) {
        return check(openid, orderId);
    }

    /**
     * 取消订单时校验
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderMasterDto chenkCancelOrder(String openid, String orderId) {
        OrderMasterDto orderMasterDto = check(openid, orderId);
        return orderService.cancelOrder(orderMasterDto);
    }

    /**
     * 校验该订单是否属于当前登录人
     * @param openid
     * @param orderId
     * @return
     */
    public OrderMasterDto check(String openid, String orderId) {
        OrderMasterDto masterDto = orderService.selectByOrderId(orderId);
        if (masterDto == null) {
            return null;
        } else {
            if (masterDto.getOrderId().equalsIgnoreCase(openid)) {
                log.error("【校验该订单是否属于当前登录人===》错误】", masterDto.getOrderId(), openid);
                throw new SellExcetion(ResultEnum.OPENID_BUYER_ERR);
            }
            return masterDto;
        }

    }
}
