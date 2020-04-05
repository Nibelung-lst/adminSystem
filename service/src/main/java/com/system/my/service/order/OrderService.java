package com.system.my.service.order;

import com.system.my.base.common.PageHelper;
import com.system.my.base.order.dto.CancelOrderDTO;
import com.system.my.base.order.dto.CountOrdersByDayVO;
import com.system.my.base.order.dto.CountOrdersByStatusVO;
import com.system.my.base.order.dto.CreatedOrderDTO;
import com.system.my.base.order.dto.OrderMonthSalesVO;
import com.system.my.base.order.dto.OrderVO;
import com.system.my.base.order.dto.SearchOrderDTO;
import com.system.my.base.order.dto.SpuMonthSalesVO;
import com.system.my.base.order.dto.StatisticalSpuSaleDTO;
import com.system.my.base.order.dto.UserParamDTO;

import java.util.List;

/**
 * @Description: 订单服务接口
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/25 09:39
 */
public interface OrderService {

    /**
     * 根据搜索订单入参对象，分页获取所有订单列表
     *
     * @param searchOrderDTO 搜索订单入参对象
     * @return 所有订单列表
     * @throws Exception
     */
    PageHelper<OrderVO> listOrders(SearchOrderDTO searchOrderDTO) throws Exception;

    /**
     * 根据用户入参对象，获取用户订单列表
     *
     * @param userParamDTO 用户入参对象
     * @return 用户订单列表
     * @throws Exception
     */
    List<OrderVO> listOrdersByUser(UserParamDTO userParamDTO) throws Exception;

    /**
     * 返回所有年份
     *
     * @return 年份
     * @throws Exception
     */
    List<Integer> listYears() throws Exception;

    /**
     * 根据年份，返回每月销量
     *
     * @param year 年份
     * @return 返回每月销量
     * @throws Exception
     */
    List<OrderMonthSalesVO> listMonthSalesByYears(Integer year) throws Exception;

    /**
     * 根据统计商品销量入参，返回商品月销量
     *
     * @param statisticalSpuSaleDTO 统计商品销量入参
     * @return 商品月销量
     * @throws Exception
     */
    List<SpuMonthSalesVO> listSpuMonthSales(StatisticalSpuSaleDTO statisticalSpuSaleDTO) throws Exception;

    /**
     * 根据spu编号，返回当前月销量
     *
     * @param spuCode spu编号
     * @return 当前月销量
     * @throws Exception
     */
    Integer countSpuCodeByMonth(String spuCode) throws Exception;

    /**
     * 返回每个订单状态的数量
     *
     * @return 每个订单状态的数量
     * @throws Exception
     */
    CountOrdersByStatusVO countOrdersByStatus() throws Exception;

    /**
     * 按日期统计订单数量、金额
     *
     * @return 订单数量、金额
     * @throws Exception
     */
    CountOrdersByDayVO countOrdersByDay() throws Exception;

    /**
     * 根据订单编号，返回订单
     *
     * @param orderCode 订单编号
     * @return 订单
     * @throws Exception
     */
    OrderVO getOrderByCode(String orderCode) throws Exception;

    /**
     * 根据订单编号，修改订单状态为支付状态
     *
     * @param orderCode 订单编号
     * @throws Exception
     */
    void payOrder(String orderCode) throws Exception;

    /**
     * 根据订单编号，修改订单状态为发货状态
     *
     * @param orderCode 订单编号
     * @throws Exception
     */
    void deliverOrder(String orderCode) throws Exception;

    /**
     * 根据订单编号，修改订单状态为收货状态
     *
     * @param orderCode 订单编号
     * @throws Exception
     */
    void receiveOrder(String orderCode) throws Exception;

    /**
     * 根据订单编号，修改订单状态为评价（完成）状态
     *
     * @param orderCode 订单编号
     * @throws Exception
     */
    void evaluateOrder(String orderCode) throws Exception;

}
