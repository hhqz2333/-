package com.lv.sell.service;

import com.lv.sell.dto.OrderMasterDto;

/**
 * @author ：LV hang
 * @date ：Created in 2020/4/6 0006  18:03
 * @modified By：
 */
public interface BuyerServer {
    //    查询详情时，校验该订单时候属于当前登录人
    public OrderMasterDto chenkDetail(String openid, String orderId);

    //    取消订单时，校验该订单时候属于当前登录人
    public OrderMasterDto chenkCancelOrder(String openid, String orderId);
}
