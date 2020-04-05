package com.system.my.serviceimpl.order;

import com.system.my.base.common.PageHelper;
import com.system.my.base.order.dto.CountOrdersByDayVO;
import com.system.my.base.order.dto.CountOrdersByStatusVO;
import com.system.my.base.order.dto.OrderDO;
import com.system.my.base.order.dto.OrderDetailDTO;
import com.system.my.base.order.dto.OrderMonthSalesVO;
import com.system.my.base.order.dto.OrderVO;
import com.system.my.base.order.dto.SearchOrderDTO;
import com.system.my.base.order.dto.SpuMonthSalesVO;
import com.system.my.base.order.dto.StatisticalSpuSaleDTO;
import com.system.my.base.order.dto.UserParamDTO;
import com.system.my.enums.order.OrderStatusEnum;
import com.system.my.mapper.orderMapper.OrderDetailMapper;
import com.system.my.mapper.orderMapper.OrderMapper;
import com.system.my.service.order.OrderService;
import com.system.my.utils.Global;
import com.system.my.utils.exception.ProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 订单服务接口实现
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/25 09:40
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * Logger日志打印
     */
    private static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);



    /**
     * 格式化时间
     */
    SimpleDateFormat ptf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 订单表的DAO
     */
    @Resource
    private OrderMapper orderDAO;

    /**
     * 订单详情表的DAO
     */
    @Resource
    private OrderDetailMapper orderDetailDAO;


    /**
     * 根据搜索订单入参对象，分页获取所有订单列表
     *
     * @param searchOrderDTO 搜索订单入参对象
     * @return 所有订单列表
     */
    @Override
    public PageHelper<OrderVO> listOrders(@Valid SearchOrderDTO searchOrderDTO) {
        Integer count = orderDAO.countListOrders(searchOrderDTO);
        List<OrderDO> orderListDO = orderDAO.listOrders(searchOrderDTO);
        PageHelper<OrderVO> orderListVO = new PageHelper<>(searchOrderDTO.getPageNum(),searchOrderDTO.getSize(),count, listOrdersVO(orderListDO), Global.NAVIGATE_PAGES);
        return orderListVO;
    }

    /**
     * 根据用户入参对象，获取用户订单列表
     *
     * @param userParamDTO 用户入参对象
     * @return 用户订单列表
     */
    @Override
    public List<OrderVO> listOrdersByUser(@Valid UserParamDTO userParamDTO) {
        List<OrderDO> orderListDO = orderDAO.listOrdersByUser(userParamDTO);
        return listOrdersVO(orderListDO);
    }

    /**
     * 返回所有年份
     *
     * @return 年份
     */
    @Override
    public List<Integer> listYears() {
        return orderDAO.listYears();
    }

    /**
     * 根据年份，返回每月销量
     *
     * @param year 年份
     * @return 返回每月销量
     */
    @Override
    public List<OrderMonthSalesVO> listMonthSalesByYears(@NotNull Integer year)  {
        List<OrderMonthSalesVO> monthSalesVOList = orderDAO.listMonthSalesByYears(year);
        List<OrderMonthSalesVO> monthSalesList = new ArrayList<>();
        for (int i = 0; i < Global.MONTH; i++) {
            OrderMonthSalesVO monthSales = new OrderMonthSalesVO();
            monthSales.setMonth(i+1);
            monthSales.setCount(0);
            monthSales.setSale(BigDecimal.valueOf(0));
            for (OrderMonthSalesVO monthSalesVO:monthSalesVOList) {
                if (monthSalesVO.getMonth() == i+1) {
                    monthSales.setCount(monthSalesVO.getCount());
                    monthSales.setSale(monthSalesVO.getSale());
                }
            }
            monthSalesList.add(monthSales);
        }
        return monthSalesList;
    }

    /**
     * 根据统计商品销量入参，返回商品月销量
     *
     * @param statisticalSpuSaleDTO 统计商品销量入参
     * @return 商品月销量
     * @throws Exception
     */
    @Override
    public List<SpuMonthSalesVO> listSpuMonthSales(@Valid StatisticalSpuSaleDTO statisticalSpuSaleDTO) throws Exception {
        return orderDAO.listSpuMonthSales(statisticalSpuSaleDTO);
    }

    /**
     * 根据spu编号，返回当前月销量
     *
     * @param spuCode spu编号
     * @return 当前月销量
     * @throws Exception
     */
    @Override
    public Integer countSpuCodeByMonth(@NotBlank String spuCode) throws Exception {
        return orderDAO.countSpuCodeByMonth(spuCode);
    }

    /**
     * 返回每个订单状态的数量
     *
     * @return 每个订单状态的数量
     */
    @Override
    public CountOrdersByStatusVO countOrdersByStatus() {
        CountOrdersByStatusVO countOrdersByStatusVO = new CountOrdersByStatusVO();
        countOrdersByStatusVO.setNotPayStatus(orderDAO.countOrdersByStatus(OrderStatusEnum.NOT_PAY_STATUS.getStatus()));
        countOrdersByStatusVO.setNotDeliverStatus(orderDAO.countOrdersByStatus(OrderStatusEnum.NOT_DELIVER_STATUS.getStatus()));
        countOrdersByStatusVO.setNotReceiveStatus(orderDAO.countOrdersByStatus(OrderStatusEnum.NOT_RECEIVE_STATUS.getStatus()));
        countOrdersByStatusVO.setNotEvaluateStatus(orderDAO.countOrdersByStatus(OrderStatusEnum.NOT_EVALUATE_STATUS.getStatus()));
        countOrdersByStatusVO.setFinishStatus(orderDAO.countOrdersByStatus(OrderStatusEnum.FINISH_STATUS.getStatus()));
        return countOrdersByStatusVO;
    }

    /**
     * 按日期统计订单数量、金额
     *
     * @return 订单数量、金额
     */
    @Override
    public CountOrdersByDayVO countOrdersByDay() {
        Integer todayOrderNum = orderDAO.countOrdersByToday();
        BigDecimal todayOrderAmount = orderDAO.getOrdersPriceByToday();
        BigDecimal yesterdayOrderAmount = orderDAO.getOrdersPriceByYesterday();
        CountOrdersByDayVO countOrdersByDayVO = new CountOrdersByDayVO();
        countOrdersByDayVO.setTodayOrderNum(todayOrderNum);
        countOrdersByDayVO.setTodayOrderAmount(todayOrderAmount);
        countOrdersByDayVO.setYesterdayOrderAmount(yesterdayOrderAmount);
        return countOrdersByDayVO;
    }

    /**
     * 根据订单编号，返回订单
     *
     * @param orderCode 订单编号
     * @return 订单
     */
    @Override
    public OrderVO getOrderByCode(@NotBlank String orderCode) {
        OrderVO orderVO = new OrderVO();
        OrderDO orderDO = orderDAO.getOrderByCode(orderCode);
        if (orderDO == null) {
            throw new ProductException("查不到该订单");
        }
        BeanUtils.copyProperties(orderDO,orderVO);
        orderVO.setCreateTime(ptf.format(orderDO.getCreateTime()));
        if (orderVO.getCancelTime() != null) {
            orderVO.setCancelTime(ptf.format(orderDO.getCancelTime()));
        }
        if (orderVO.getPayTime() != null) {
            orderVO.setPayTime(ptf.format(orderDO.getPayTime()));
        }
        if (orderVO.getDeliverTime() != null) {
            orderVO.setDeliverTime(ptf.format(orderDO.getDeliverTime()));
        }
        if (orderVO.getFinishTime() != null) {
            orderVO.setFinishTime(ptf.format(orderDO.getFinishTime()));
        }
        orderVO.setOrderDetailVOList(orderDetailDAO.listOrderDetails(orderCode));
        return orderVO;
    }



    /**
     * 根据订单详情数组，计算订单原价
     *
     * @param orderDetailDTOList 订单详情数组
     * @return 订单原价
     */
    public BigDecimal getOriginalPrice(List<OrderDetailDTO> orderDetailDTOList) {
        BigDecimal originalPrice = new BigDecimal(0);
        for (OrderDetailDTO orderDetailDTO:orderDetailDTOList) {
            originalPrice = originalPrice.add(orderDetailDTO.getCurrentPrice().multiply(BigDecimal.valueOf(orderDetailDTO.getSkuNum())));
        }
        return originalPrice;
    }

    /**
     * 根据订单编号，修改订单状态为支付状态
     *
     * @param orderCode 订单编号
     */
    @Override
    public void payOrder(@NotBlank String orderCode) {
        orderDAO.updatePayOrder(orderCode);
    }

    /**
     * 根据订单编号，修改订单状态为发货状态
     *
     * @param orderCode 订单编号
     */
    @Override
    public void deliverOrder(@NotBlank String orderCode) {
        orderDAO.updateDeliverOrder(orderCode);
    }

    /**
     * 根据订单编号，修改订单状态为收货状态
     *
     * @param orderCode 订单编号
     */
    @Override
    public void receiveOrder(@NotBlank String orderCode) {
        orderDAO.updateReceiveOrder(orderCode);
    }

    /**
     * 根据订单编号，修改订单状态为评价(完成)状态
     *
     * @param orderCode 订单编号
     */
    @Override
    public void evaluateOrder(@NotBlank String orderCode) {
        orderDAO.updateEvaluateOrder(orderCode);
    }

    /**
     * 根据订单对象数组，返回封装完整的订单对象数组
     *
     * @param orderDOList 订单对象数组
     * @return 封装完整的订单对象数组
     */
    private List<OrderVO> listOrdersVO(List<OrderDO> orderDOList) {
        List<OrderVO> orderListVO = new ArrayList<OrderVO>();
        for (int i = 0; i < orderDOList.size(); i++) {
            OrderVO orderVO = new OrderVO();
            orderVO.setCreateTime(ptf.format(orderDOList.get(i).getCreateTime()));
            if (orderDOList.get(i).getCancelTime() != null) {
                orderVO.setCancelTime(ptf.format(orderDOList.get(i).getCancelTime()));
            }
            if (orderDOList.get(i).getPayTime() != null) {
                orderVO.setPayTime(ptf.format(orderDOList.get(i).getPayTime()));
            }
            if (orderDOList.get(i).getDeliverTime() != null) {
                orderVO.setDeliverTime(ptf.format(orderDOList.get(i).getDeliverTime()));
            }
            if (orderDOList.get(i).getFinishTime() != null) {
                orderVO.setFinishTime(ptf.format(orderDOList.get(i).getFinishTime()));
            }
            BeanUtils.copyProperties(orderDOList.get(i),orderVO);
            orderVO.setOrderDetailVOList(orderDetailDAO.listOrderDetails(orderDOList.get(i).getOrderCode()));
            orderListVO.add(orderVO);
        }
        return orderListVO;
    }
}
