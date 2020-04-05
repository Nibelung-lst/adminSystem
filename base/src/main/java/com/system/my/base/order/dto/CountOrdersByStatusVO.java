package com.system.my.base.order.dto;

import java.io.Serializable;

/**
 * @Description: 返回RPC的按状态统计订单结果
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/5 17:14
 */
public class CountOrdersByStatusVO implements Serializable {

    /**
     * 待付款状态
     */
    private Integer notPayStatus;

    /**
     * 待发货状态
     */
    private Integer notDeliverStatus;

    /**
     * 待收货状态
     */
    private Integer notReceiveStatus;

    /**
     * 待评价状态
     */
    private Integer notEvaluateStatus;

    /**
     * 已完成状态
     */
    private Integer finishStatus;

    public Integer getNotPayStatus() {
        return notPayStatus;
    }

    public void setNotPayStatus(Integer notPayStatus) {
        this.notPayStatus = notPayStatus;
    }

    public Integer getNotDeliverStatus() {
        return notDeliverStatus;
    }

    public void setNotDeliverStatus(Integer notDeliverStatus) {
        this.notDeliverStatus = notDeliverStatus;
    }

    public Integer getNotReceiveStatus() {
        return notReceiveStatus;
    }

    public void setNotReceiveStatus(Integer notReceiveStatus) {
        this.notReceiveStatus = notReceiveStatus;
    }

    public Integer getNotEvaluateStatus() {
        return notEvaluateStatus;
    }

    public void setNotEvaluateStatus(Integer notEvaluateStatus) {
        this.notEvaluateStatus = notEvaluateStatus;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    @Override
    public String toString() {
        return "CountOrdersByStatusVO{" +
                "notPayStatus=" + notPayStatus +
                ", notDeliverStatus=" + notDeliverStatus +
                ", notReceiveStatus=" + notReceiveStatus +
                ", notEvaluateStatus=" + notEvaluateStatus +
                ", finishStatus=" + finishStatus +
                '}';
    }
}
