package com.lv.sell.service.impl;

import com.lv.sell.converter.OrderMaeterTOOrderMastrrDtoConverter;
import com.lv.sell.dataobject.OrderDetail;
import com.lv.sell.dataobject.OrderMaster;
import com.lv.sell.dataobject.ProductInfo;
import com.lv.sell.dto.CartDto;
import com.lv.sell.dto.OrderMasterDto;
import com.lv.sell.enums.OrderStatusEnum;
import com.lv.sell.enums.PayStatusEnum;
import com.lv.sell.enums.ResultEnum;
import com.lv.sell.excetion.SellExcetion;
import com.lv.sell.repository.OrderDetailRepository;
import com.lv.sell.repository.OrderMasterRepository;
import com.lv.sell.service.OrderService;
import com.lv.sell.service.ProductInfoService;
import com.lv.sell.until.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/24 0024  20:34
 * @modified By：
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    ProductInfoService productInfoService;
    @Resource
    OrderDetailRepository orderDetailRepository;
    @Resource
    OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     *
     * @param orderMasterDto
     * @return
     */
    @Override
    public OrderMasterDto insertOrder(OrderMasterDto orderMasterDto) {
        BigDecimal[] orderAmount = {new BigDecimal(BigInteger.ZERO)};
        String orderId = KeyUtil.getPrimaryKey();
        //   查询商品，数量，单价
        if (orderMasterDto.getOrderDetailList().size() > 0) {
            orderMasterDto.getOrderDetailList().forEach(o -> {
                ProductInfo productInfo = productInfoService.getOne(o.getProductId());
                if (productInfo == null) {
                    throw new SellExcetion(ResultEnum.PRODUCT_NOT_EXIST);
                }
                // 计算订单的总价
                orderAmount[0] = productInfo.getProductPrice().multiply(
                        new BigDecimal(o.getProductQuantity())).add(orderAmount[0]);
                // 写入订单详情数据
                OrderDetail orderDetail = new OrderDetail();
                // 属性值为null的时候也会被复制，所以，要把这句代码放第一句。
                BeanUtils.copyProperties(productInfo, orderDetail);
                orderDetail.setOrderId(orderId);
                orderDetail.setDetailId(KeyUtil.getPrimaryKey());
                orderDetail.setProductQuantity(o.getProductQuantity());
                orderDetailRepository.save(orderDetail);
            });
        }
        // 写入订单数量
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount[0]);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        // 扣库存
        List<CartDto> cartDtoList = orderMasterDto.getOrderDetailList().
                stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.reduceStock(cartDtoList);
        return orderMasterDto;
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderMasterDto selectByOrderId(String orderId) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        OrderMaster orderMaster = orderMasterRepository.getOne(orderId);
        if (orderMaster == null) {
            throw new SellExcetion(ResultEnum.ORDER_NOT_EXIST);
        } else {
            BeanUtils.copyProperties(orderMaster, orderMasterDto);
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
            if (orderDetailList.size() <= 0) {
                throw new SellExcetion(ResultEnum.ORDER_DETAIL_NOT_EXIST);
            } else {
                orderMasterDto.setOrderDetailList(orderDetailList);
            }
        }
        return orderMasterDto;
    }

    /**
     * 查询所有订单
     *
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderMasterDto> sleectAll(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> byBuyerOpenidList = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDto> list = OrderMaeterTOOrderMastrrDtoConverter.converterList(byBuyerOpenidList.getContent());
        Page<OrderMasterDto> pageList = new PageImpl<OrderMasterDto>(list, pageable, byBuyerOpenidList.getTotalElements());
        return pageList;
    }

    /**
     * 取消订单
     *
     * @param orderMasterDto
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto) {
        // 判断订单状态
        if (!orderMasterDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单-订单状态不正确】", orderMasterDto.getOrderStatus());
            throw new SellExcetion(ResultEnum.ORDER_STATUS_ERROR);
        } else {
            // 改变订单状态
            orderMasterDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderMasterDto, orderMaster);
            OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
            if (orderMaster1 == null) {
                log.error("【取消订单-修改订单状态失败】", orderMaster);
                throw new SellExcetion(ResultEnum.UPDATA_ORDER_STATUS_FAIL);
            } else {
                // 返回库存
                List<CartDto> list = orderMasterDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
                productInfoService.addinStock(list);
                // 如果已经支付，退款
                // TODO
            }
        }
        return orderMasterDto;
    }

    /**
     * 完结订单
     *
     * @param orderMasterDto
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDto finshOrder(OrderMasterDto orderMasterDto) {
        // 判断订单状态
        if (!orderMasterDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单-订单状态不正确】", orderMasterDto.getOrderStatus());
            throw new SellExcetion(ResultEnum.ORDER_STATUS_ERROR);
        } else {
            // 改变订单状态
            orderMasterDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderMasterDto, orderMaster);
            OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
            if (orderMaster1 == null) {
                log.error("【完结订单-修改订单状态失败】", orderMaster);
                throw new SellExcetion(ResultEnum.UPDATA_ORDER_STATUS_FAIL);
            }
        }
        return orderMasterDto;
    }

    /**
     * 订单支付
     *
     * @param orderMasterDto
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDto payOrder(OrderMasterDto orderMasterDto) {
        // 判断订单状态
        if (!orderMasterDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付-订单状态不正确】", orderMasterDto.getOrderStatus());
            throw new SellExcetion(ResultEnum.ORDER_STATUS_ERROR);
        } else {
            // 判断支付状态
            if (!orderMasterDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
                log.error("【订单支付-支付状态不正确】", orderMasterDto.getPayStatus());
                throw new SellExcetion(ResultEnum.ORDER_PAY_STATUS_ERROR);
            } else {
                // 修改支付状态
                orderMasterDto.setOrderStatus(PayStatusEnum.SUCCESS.getCode());
                OrderMaster orderMaster = new OrderMaster();
                BeanUtils.copyProperties(orderMasterDto, orderMaster);
                OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
                if (orderMaster1 == null) {
                    log.error("【订单支付-修改支付状态失败】", orderMaster);
                    throw new SellExcetion(ResultEnum.ORDER_PAY__FAIL);
                }
            }
        }
        return orderMasterDto;
    }
}
