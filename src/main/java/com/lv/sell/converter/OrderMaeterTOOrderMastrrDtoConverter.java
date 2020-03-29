package com.lv.sell.converter;

import com.lv.sell.dataobject.OrderMaster;
import com.lv.sell.dto.OrderMasterDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/28 0028  16:07
 * @modified By：dto与实体类直接转换
 */
public class OrderMaeterTOOrderMastrrDtoConverter {
    public static OrderMasterDto converter(OrderMaster orderMaster) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster,orderMasterDto);
        return orderMasterDto;
    }
    public static List<OrderMasterDto> converterList(List<OrderMaster> orderMasterList) {
        List<OrderMasterDto> list = orderMasterList.stream().map(e->
                converter(e)).collect(Collectors.toList());
        return list;
    }
}
