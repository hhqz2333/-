package com.lv.sell.controller;

import com.lv.sell.converter.OrderForm2OrderMasterDtoConverter;
import com.lv.sell.dto.OrderMasterDto;
import com.lv.sell.enums.ResultEnum;
import com.lv.sell.excetion.SellExcetion;
import com.lv.sell.form.OrderForm;
import com.lv.sell.service.BuyerServer;
import com.lv.sell.service.OrderService;
import com.lv.sell.until.ResultUntilVo;
import com.lv.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/29 0029  15:45
 * @modified By：
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerServer buyerServer;

    // 创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> insertDoder(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】--", orderForm);
            throw new SellExcetion(ResultEnum.PARAMETER_CHECKING_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMasterDto orderMasterDto = OrderForm2OrderMasterDtoConverter.converter(orderForm);
//        校验购物车不能为空
        if (CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())) {
            log.error("【创建订单】--", orderMasterDto.getOrderDetailList());
            throw new SellExcetion(ResultEnum.SHOPPING_CART_CANNOT_BE_EMPTY);
        }
        OrderMasterDto orderMasterDtoResule = orderService.insertOrder(orderMasterDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderMasterDtoResule.getOrderId());
        return ResultUntilVo.success(map);
    }

    // 订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderMasterDto>> list(@RequestParam(value = "openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】--", openid);
            throw new SellExcetion(ResultEnum.OPENID_NOT_NULL);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderMasterDto> orderMasterDtos = orderService.sleectAll(openid, pageRequest);
        return ResultUntilVo.success(orderMasterDtos);
    }

    // 订单详情
    @GetMapping("/detail")
    public ResultVo<OrderMasterDto> detail(@RequestParam(value = "openid") String openid,
                                           @RequestParam(value = "orderId") String orderId) {
        OrderMasterDto orderMasterDto = buyerServer.chenkDetail(openid, orderId);
        return ResultUntilVo.success(orderMasterDto);
    }

    // 取消订单
    @PostMapping("/cancelOrder")
    public ResultVo cancelOrder(@RequestParam(value = "openid") String openid,
                                @RequestParam(value = "orderId") String orderId) {
        OrderMasterDto orderMasterDto = buyerServer.chenkCancelOrder(openid, orderId);
        return ResultUntilVo.success();
    }
}
