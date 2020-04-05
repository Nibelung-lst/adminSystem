package com.system.my.base.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 创建消息记录入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/18 14:19
 */
public class CreateBrokerMessageLogDTO implements Serializable {

    /**
     * 消息唯一id
     */
    private String messageId;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 消息投递状态（0：投递中，1：投递成功， 2：投递失败）
     */
    private Integer status;

    /**
     * 下一次重试时间
     */
    private Date nextRetry;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CreateBrokerMessageLogDTO{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", nextRetry=" + nextRetry +
                ", createTime=" + createTime +
                '}';
    }
}
