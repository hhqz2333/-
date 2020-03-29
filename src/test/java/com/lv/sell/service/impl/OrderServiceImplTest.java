package com.lv.sell.service.impl;

import com.lv.sell.SellApplication;
import com.lv.sell.dataobject.OrderDetail;
import com.lv.sell.dto.CartDto;
import com.lv.sell.dto.OrderMasterDto;
import com.lv.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
   private final String ORDER_ID = "1585378672024887995";

    @Test
    public void insertOrder() throws Exception {
        final String BUYER_OPENID = "233";
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyerAddress("康嘉景园");
        orderMasterDto.setBuyerName("甜筒");
        orderMasterDto.setBuyerOpenid(BUYER_OPENID);
        orderMasterDto.setBuyerPhone("13579246810");
        OrderDetail cartDto = new OrderDetail();
        cartDto.setProductId("3");
        cartDto.setProductQuantity(3);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(cartDto);
        orderMasterDto.setOrderDetailList(orderDetailList);
        log.info("lala0", orderMasterDto);
        orderService.insertOrder(orderMasterDto);

    }

    @Test
    public void selectByOrderId() throws Exception {
        final String ORDER_ID = "1585378672024887995";
        OrderMasterDto orderMasterDto = orderService.selectByOrderId(ORDER_ID);
        log.info("【查询出来的内容】", orderMasterDto);
    }

    @Test
    public void sleectAll() throws Exception {
        final String BUYER_OPENID = "233";
        Pageable page = PageRequest.of(0,2);
        Page<OrderMasterDto> orderMasterDtos = orderService.sleectAll(BUYER_OPENID, page);System.out.println("111");
        System.out.println("123");
    }

    @Test
    public void cancelOrder() throws Exception {
        OrderMasterDto orderMasterDto = orderService.selectByOrderId(ORDER_ID);
        OrderMasterDto cancelOrder = orderService.cancelOrder(orderMasterDto);
        System.out.println(cancelOrder);

    }

    @Test
    public void finshOrder() throws Exception {
        OrderMasterDto orderMasterDto = orderService.selectByOrderId(ORDER_ID);
        OrderMasterDto cancelOrder = orderService.finshOrder(orderMasterDto);
        System.out.println(cancelOrder);
    }

    @Test
    public void payOrder() throws Exception {
        OrderMasterDto orderMasterDto = orderService.selectByOrderId(ORDER_ID);
        OrderMasterDto cancelOrder = orderService.payOrder(orderMasterDto);
        System.out.println(cancelOrder);
    }

}