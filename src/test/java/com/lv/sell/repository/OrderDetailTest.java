package com.lv.sell.repository;

import com.lv.sell.SellApplication;
import com.lv.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/21 0021  17:24
 * @modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
public class OrderDetailTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void save() {
        OrderDetail o = new OrderDetail();
        o.setDetailId("23");
        o.setOrderId("123");
        o.setProductIcon("1211");
        o.setProductId("112");
        o.setProductName("小鱼干");
        o.setProductPrice(new BigDecimal(2.1));
        o.setProductQuantity(22);
        OrderDetail save = orderDetailRepository.save(o);
        System.out.println(save.getProductName());
    }
    @Test
    public void findByOrderIdTest() {
        List<OrderDetail> list = orderDetailRepository.findByOrderId("12345");
        System.out.println(list);

    }
}
