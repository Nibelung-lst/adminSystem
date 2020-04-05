package com.system.my.mapper.orderMapper;

import com.system.my.base.order.dto.OrderDetailVO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/5 14:20
 * @describe
 **/
public interface OrderDetailMapper {
    /**
     * 根据订单编号，获取订单详情
     *
     * @param orderCode 订单编号
     * @return 订单详情
     */
    List<OrderDetailVO> listOrderDetails(String orderCode);
}
