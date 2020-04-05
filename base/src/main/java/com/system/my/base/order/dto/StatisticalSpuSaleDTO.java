package com.system.my.base.order.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 统计商品销量入参对象
 * @Author: XieLiuqin  liuqin.xie@luckincoffee.com
 * @Date: 2019/11/11 10:51
 */
public class StatisticalSpuSaleDTO implements Serializable {

    /**
     * 年份
     */
    @NotNull(message = "年份不得为空")
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不得为空")
    @Max(value = 12, message = "月份错误")
    @Min(value = 1, message = "月份错误")
    private Integer month;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "StatisticalSpuSaleDTO{" +
                "year=" + year +
                ", month=" + month +
                '}';
    }
}
