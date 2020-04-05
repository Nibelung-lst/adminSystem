package com.system.my.base.product;

import java.io.Serializable;

/**
 * @Author :Nibelung
 * @Date ：Created in 10:27 2019/11/4
 * @Description :
 * @Modified By :
 * @Version : $
 */
public class Page implements Serializable {

    private Integer start;
    private Integer size;
    private String condition;
    private Integer showNumber;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Integer showNumber) {
        this.showNumber = showNumber;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Page(Integer start, Integer size, String condition) {
        this.start = start;
        this.size = size;
        this.condition = condition;
    }

    public Page(Integer start, Integer size) {
        this.start = start;
        this.size = size;
    }

    public Page(Integer start, Integer size, Integer showNumber) {
        this.start = start;
        this.size = size;
        this.showNumber = showNumber;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", size=" + size +
                ", condition='" + condition + '\'' +
                ", showNumber=" + showNumber +
                '}';
    }
}
