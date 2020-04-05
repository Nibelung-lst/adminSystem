package com.systm.my.controller.order.ao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 搜索订单controller层入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/1 09:45
 */
public class SearchOrderAO implements Serializable {

    /**
     * 页面大小
     */
    @NotNull(message = "页面大小不能为空")
    private Integer pageSize;

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    /**
     * 订单类型(1:普通订单，2:积分商城订单，3:秒杀订单)
     */
    @NotNull(message = "订单类型不得为空")
    @Max(value = 2, message = "订单类型错误")
    @Min(value = 1, message = "订单类型错误")
    private Integer type;

    /**
     * 订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     */
    @Max(value = 5, message = "订单状态错误")
    @Min(value = -1, message = "订单状态错误")
    private Integer status;

    /**
     * 关键字
     */
    private String keyword;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchOrderAO{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", type=" + type +
                ", status=" + status +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
