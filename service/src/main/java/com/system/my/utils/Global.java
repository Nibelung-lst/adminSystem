package com.system.my.utils;

import java.math.BigDecimal;

/**
 * @Description: 全局常量
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/4 09:02
 */
public final class Global {

    /**
     * 分页栏个数
     */
    public static final Integer NAVIGATE_PAGES = 5;

    /**
     * 月份
     */
    public static final int MONTH = 12;

    /**
     * 订单超时时间
     */
    public static final long DELAY_TIMES = 15 * 60 * 1000;

    /**
     * 积分与金钱比例
     */
    public static final BigDecimal POINT_PERCENT = new BigDecimal(0.01);

    /**
     * 积分比较结果
     */
    public static final int ZERO = 0;

}
