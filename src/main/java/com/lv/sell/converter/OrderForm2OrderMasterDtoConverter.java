package com.lv.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lv.sell.dataobject.OrderDetail;
import com.lv.sell.dto.OrderMasterDto;
import com.lv.sell.enums.ResultEnum;
import com.lv.sell.excetion.SellExcetion;
import com.lv.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/29 0029  16:17
 * @modified By：
 */
@Slf4j
public class OrderForm2OrderMasterDtoConverter {
    public static OrderMasterDto converter(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyerName(orderForm.getName());
        orderMasterDto.setBuyerPhone(orderForm.getPhone());
        orderMasterDto.setBuyerOpenid(orderForm.getOpenid());
        orderMasterDto.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> list = new ArrayList<>();
        try {
            list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
            orderMasterDto.setOrderDetailList(list);
        } catch (Exception e) {
            log.error("【参数转换失败】=====》", orderForm.getItems());
            throw new SellExcetion(ResultEnum.PARAMETER_CHECKING_ERROR);
        }
        return orderMasterDto;

    }
}
