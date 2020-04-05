package com.systm.my.controller.order;

import com.system.my.base.order.dto.SearchOrderDTO;
import com.system.my.base.order.dto.StatisticalSpuSaleDTO;
import com.system.my.base.utils.Result;
import com.system.my.base.utils.ResultUtil;
import com.system.my.service.order.OrderService;
import com.systm.my.controller.order.ao.SearchOrderAO;
import com.systm.my.controller.order.ao.StatisticalSpuSaleAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description: 订单api控制层
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/31 10:14
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 订单RPC服务接口
     */
    @Resource
    private OrderService orderRemoteService;

    /**
     * 根据搜索订单入参对象，返回订单列表
     *
     * @param searchOrderAO 搜索订单入参对象
     * @return 订单列表
     * @throws Exception
     */
    @PostMapping("/listOrders")
    public Result listOrders(@RequestBody @Valid SearchOrderAO searchOrderAO) throws Exception {
        SearchOrderDTO searchOrderDTO = new SearchOrderDTO();
        searchOrderDTO.setStart((searchOrderAO.getPageNum()-1)*searchOrderAO.getPageSize());
        searchOrderDTO.setSize(searchOrderAO.getPageSize());
        searchOrderDTO.setPageNum(searchOrderAO.getPageNum());
        searchOrderDTO.setType(searchOrderAO.getType());
        searchOrderDTO.setStatus(searchOrderAO.getStatus());
        searchOrderDTO.setKeyword(searchOrderAO.getKeyword());
        return ResultUtil.success(orderRemoteService.listOrders(searchOrderDTO));
    }

    /**
     * 根据订单编号，返回订单发货结果
     *
     * @param orderCode 订单编号
     * @return 订单发货结果
     * @throws Exception
     */
    @PostMapping("/deliverOrder")
    public Result deliverOrder(@RequestParam("orderCode") @NotBlank String orderCode) throws Exception {
        orderRemoteService.deliverOrder(orderCode);
        return ResultUtil.success(null);
    }


    /**
     * 返回所有订单年份
     *
     * @return 年份
     * @throws Exception
     */
    @GetMapping("/listYears")
    public Result listYears() throws Exception {
        return ResultUtil.success(orderRemoteService.listYears());
    }

    /**
     * 根据年份，返回每月销量
     *
     * @param year 年份
     * @return 返回每月销量
     * @throws Exception
     */
    @PostMapping("/listMonthSalesByYears")
    public Result listMonthSalesByYears (@RequestParam("year") @NotNull Integer year) throws Exception {
        return ResultUtil.success(orderRemoteService.listMonthSalesByYears(year));
    }

    /**
     * 根据统计商品销量入参，返回商品月销量
     *
     * @param statisticalSpuSaleAO  统计商品销量入参
     * @return 商品月销量
     * @throws Exception
     */
    @PostMapping("/listSpuMonthSales")
    public Result listSpuMonthSales(@RequestBody @Valid StatisticalSpuSaleAO statisticalSpuSaleAO) throws Exception {
        StatisticalSpuSaleDTO statisticalSpuSaleDTO = new StatisticalSpuSaleDTO();
        statisticalSpuSaleDTO.setYear(statisticalSpuSaleAO.getYear());
        statisticalSpuSaleDTO.setMonth(statisticalSpuSaleAO.getMonth());
        return ResultUtil.success(orderRemoteService.listSpuMonthSales(statisticalSpuSaleDTO));
    }

    /**
     * 按日期统计订单数量、金额
     *
     * @return 订单数量、金额
     * @throws Exception
     */
    @GetMapping("/countOrdersByDay")
    public Result countOrdersByDay() throws Exception {
        return ResultUtil.success(orderRemoteService.countOrdersByDay());
    }

    /**
     * 返回每个订单状态的数量
     *
     * @return 每个订单状态的数量
     * @throws Exception
     */
    @GetMapping("/countOrdersByStatus")
    public Result countOrdersByStatus() throws Exception {
        return ResultUtil.success(orderRemoteService.countOrdersByStatus());
    }
}
