package com.lv.sell.service.impl;

import com.lv.sell.dataobject.OrderDetail;
import com.lv.sell.dataobject.OrderMaster;
import com.lv.sell.dataobject.ProductInfo;
import com.lv.sell.dto.OrderMasterDto;
import com.lv.sell.enums.ResultEnum;
import com.lv.sell.excetion.SellExcetion;
import com.lv.sell.repository.OrderDetailRepository;
import com.lv.sell.repository.OrderMasterRepository;
import com.lv.sell.service.OrderService;
import com.lv.sell.service.ProductInfoService;
import com.lv.sell.until.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/24 0024  20:34
 * @modified By：
 */
@Service
public class OrderServiceImpl implements OrderService {
   @Resource
    ProductInfoService productInfoService;
   @Resource
    OrderDetailRepository orderDetailRepository;
   @Resource
    OrderMasterRepository orderMasterRepository;
    @Override
    public OrderMasterDto insertOrder(OrderMasterDto orderMasterDto) {
        BigDecimal[] orderAmount = {new BigDecimal(BigInteger.ZERO)};
        String orderId = KeyUtil.getPrimaryKey();
             //   查询商品，数量，单价
        if (orderMasterDto.getOrderDetailList().size()>0) {
            orderMasterDto.getOrderDetailList().forEach(o-> {
                ProductInfo productInfo = productInfoService.getOne(o.getProductId());
                if (productInfo == null) {
                    throw new SellExcetion(ResultEnum.ORDER_NOT_EXIST);
                }
                // 计算订单的总价
                orderAmount[0] = productInfo.getProductPrice().multiply(
                        new BigDecimal(o.getProductQuantity())).add(orderAmount[0]);
                // 写入订单详情数据
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyUtil.getPrimaryKey());
                BeanUtils.copyProperties(productInfo, orderDetail);
                orderDetailRepository.save(orderDetail);
            });
        }
        // 写入订单数量
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount[0]);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMasterRepository.save(orderMaster);
//        扣钱
        return null;
    }

    @Override
    public OrderMasterDto selectByOrderId(String orderId) {
        return null;
    }

    @Override
    public List<OrderMasterDto> sleectAll(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto) {
        return null;
    }

    @Override
    public OrderMasterDto finshOrder(OrderMasterDto orderMasterDto) {
        return null;
    }

    @Override
    public OrderMasterDto payOrder(OrderMasterDto orderMasterDto) {
        return null;
    }
}
