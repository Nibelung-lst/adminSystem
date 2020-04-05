package com.system.my.mapper.orderMapper;


import com.system.my.base.order.dto.CancelOrderDTO;
import com.system.my.base.order.dto.OrderDO;
import com.system.my.base.order.dto.OrderMonthSalesVO;
import com.system.my.base.order.dto.SearchOrderDTO;
import com.system.my.base.order.dto.SpuMonthSalesVO;
import com.system.my.base.order.dto.StatisticalSpuSaleDTO;
import com.system.my.base.order.dto.UserParamDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: t_order表的DAO层接口
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/25 09:42
 */
public interface OrderMapper {

    /**
     * 根据搜索订单入参对象，分页获取所有订单列表
     *
     * @param searchOrderDTO 搜索订单入参对象
     * @return 所有订单列表
     */
    List<OrderDO> listOrders(SearchOrderDTO searchOrderDTO);

    /**
     * 根据搜索入参对象，返回订单数量
     *
     * @param searchOrderDTO  搜索入参对象
     * @return 订单数量
     */
    Integer countListOrders(SearchOrderDTO searchOrderDTO);

    /**
     * 返回所有年份
     *
     * @return 年份
     */
    List<Integer> listYears();

    /**
     * 根据年份，返回每月销量
     *
     * @param year 年份
     * @return 每月销量
     */
    List<OrderMonthSalesVO> listMonthSalesByYears(Integer year);

    /**
     * 根据统计商品销量入参，返回商品月销量
     *
     * @param statisticalSpuSaleDTO 统计商品销量入参
     * @return 商品月销量
     */
    List<SpuMonthSalesVO> listSpuMonthSales(StatisticalSpuSaleDTO statisticalSpuSaleDTO);

    /**
     * 根据用户入参对象，获取用户订单列表
     *
     * @param userParamDTO 用户入参对象
     * @return 用户订单列表
     */
    List<OrderDO> listOrdersByUser(UserParamDTO userParamDTO);

    /**
     * 根据spu编号，返回当前月销量
     *
     * @param spuCode spu编号
     * @return 当前月销量
     */
    Integer countSpuCodeByMonth(String spuCode);

    /**
     * 根据订单状态，返回订单总数
     *
     * @param status 订单状态
     * @return 订单总数
     */
    Integer countOrdersByStatus(Integer status);

    /**
     * 返回今日订单数
     *
     * @return 今日订单数
     */
    Integer countOrdersByToday();

    /**
     * 返回今日订单总额
     *
     * @return 今日订单总额
     */
    BigDecimal getOrdersPriceByToday();

    /**
     * 返回昨日订单总额
     *
     * @return 昨日订单总额
     */
    BigDecimal getOrdersPriceByYesterday();

    /**
     * 根据订单编号，返回订单对象
     *
     * @param orderCode 订单编号
     * @return 订单对象
     */
    OrderDO getOrderByCode(String orderCode);

    /**
     * 根据提交订单入参对象，提交订单
     *
     * @param orderDO 提交订单入参对象
     * @return 提交订单结果
     */
    void insertOrder(OrderDO orderDO);

    /**
     * 根据订单编号，修改订单状态为支付状态
     *
     * @param orderCode 订单编号
     */
    void updatePayOrder(String orderCode);

    /**
     * 根据订单编号，修改订单状态为发货状态
     *
     * @param orderCode 订单编号
     */
    void updateDeliverOrder(String orderCode);

    /**
     * 根据订单编号，修改订单状态为收货状态
     *
     * @param orderCode 订单编号
     */
    void updateReceiveOrder(String orderCode);

    /**
     * 根据订单编号，修改订单状态为评价(完成)状态
     *
     * @param orderCode 订单编号
     */
    void updateEvaluateOrder(String orderCode);

    /**
     * 根据订单取消订单入参对象，修改订单状态，并填写取消理由
     *
     * @param cancelOrderDTO 取消订单入参对象
     */
    void updateCancelOrder(CancelOrderDTO cancelOrderDTO);
}
