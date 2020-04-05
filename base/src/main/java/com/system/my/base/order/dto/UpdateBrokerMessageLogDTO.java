package com.system.my.base.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 修改消息记录入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/18 14:41
 */
public class UpdateBrokerMessageLogDTO implements Serializable {

    /**
     * 消息唯一id
     */
    private String messageId;

    /**
     * 重试次数
     */
    private Integer tryCount;

    /**
     * 消息投递状态（0：投递中，1：投递成功， 2：投递失败）
     */
    private Integer status;

    /**
     * 下一次重试时间
     */
    private Date nextRetry;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UpdateBrokerMessageLogDTO{" +
                "messageId='" + messageId + '\'' +
                ", tryCount=" + tryCount +
                ", status=" + status +
                ", nextRetry=" + nextRetry +
                ", updateTime=" + updateTime +
                '}';
    }
}
