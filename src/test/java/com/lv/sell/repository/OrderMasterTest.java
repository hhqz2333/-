package com.lv.sell.repository;

import com.lv.sell.SellApplication;
import com.lv.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/21 0021  16:37
 * @modified By：
 */
//让测试运行于Spring测试环境
@RunWith(SpringRunner.class)
//classes属性指定启动类
@SpringBootTest(classes = SellApplication.class)
public class OrderMasterTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid("2");
        orderMaster.setBuyerAddress("2");
        orderMaster.setBuyerName("晴晴");
        orderMaster.setBuyerPhone("1234556");
        orderMaster.setOrderId("1234523");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster save = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(save);

    }

    @Test
    public void findByBuyerOpenidTect() {
      Pageable page = PageRequest.of(1,2);
        Page<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid("2", page);
        System.out.println(byBuyerOpenid);
        System.out.println("测试git撤销22222");

    }

}
