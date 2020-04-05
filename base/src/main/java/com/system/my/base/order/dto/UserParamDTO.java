package com.system.my.base.order.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 根据用户参数查询订单入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/10/30 17:08
 */
public class UserParamDTO implements Serializable {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不得为空")
    private Long userId;

    /**
     * 订单状态（-1:已取消 1：待付款 2：待发货 3：待收货 4：待评价 5：已成交）
     */
    @Max(value = 5, message = "订单状态错误")
    @Min(value = -1, message = "订单状态错误")
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserParamDTO{" +
                "userId=" + userId +
                ", status=" + status +
                '}';
    }
}
