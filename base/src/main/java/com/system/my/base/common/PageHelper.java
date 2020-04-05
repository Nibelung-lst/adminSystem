package com.system.my.base.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 分页类
 * @Author 郑泽睿
 * @Date 2019/10/22 9:10
 * */
public class PageHelper<T> implements Serializable {
    /**
     * 记录总数
     */
    int count;
    /**
     * 分页栏页面数
     */
    int navigatePages;

    /**
     * 总页数
     */
    int totalPages;

    /**
     * 当前页
     */
    int num;

    /**
     * 页面内容
     */
    List<T> content;

    /**
     * 是否有内容
     */
    boolean isHasContent;

    /**
     * 是否是首页
     */
    boolean first;

    /**
     * 是否是末页
     */
    boolean last;

    /**
     * 是否有下一页
     */
    boolean isHasNext;

    /**
     * 是否有前一页
     */
    boolean isHasPrevious;

    /**
     * 导航栏页面列表
     */
    int[] navigatepageNums;

    /**
     * 搜索字段
     */
    String key;

    /**
     * 搜索值
     */
    String value;

    public PageHelper() {
        //这个空的分页是为了 Redis 从 json格式转换为 Page4Navigator 对象而专门提供的
    }

    /**
     *
     * @param start 当前页数
     * @param size 每页多少条数据
     * @param count 数据总数
     * @param content 页面数据
     * @param navigatePages 分页栏个数
     */
    public PageHelper(int start, int size, int count, List<T> content, int navigatePages){
        num = start;
        this.navigatePages = navigatePages;
        totalPages = count%size==0?count/size:count/size+1;
        if(count==0){
            totalPages = 1;
        }
        //新增结果总数
        this.count = count;
        first = num==0?true:false;
        last = num==totalPages-1?true:false;
        isHasNext = last?false:true;
        isHasPrevious = first?false:true;
        this.content = content;
        isHasContent = this.content.isEmpty()?false:true;

        calcNavigatepageNums();
    }

    public PageHelper(int start, int size, int count, List<T> content, int navigatePages, String key, String value){
        num = start;
        this.navigatePages = navigatePages;
        totalPages = count%size==0?count/size:count/size+1;
        if(count==0){
            totalPages = 1;
        }
        first = num==0?true:false;
        last = num==totalPages-1?true:false;
        isHasNext = last?false:true;
        isHasPrevious = first?false:true;
        this.content = content;
        isHasContent = this.content.isEmpty()?false:true;

        this.key = key;
        this.value = value;

        calcNavigatepageNums();
    }

    private void calcNavigatepageNums() {
        int[] navigatepageNums;
        int totalPages = getTotalPages();
        int num = getNum();
        //当总页数小于或等于导航页码数时
        if (totalPages <= navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = num - navigatePages / 2;
            int endNum = num + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > totalPages) {
                endNum = totalPages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        this.navigatepageNums = navigatepageNums;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "count=" + count +
                ", navigatePages=" + navigatePages +
                ", totalPages=" + totalPages +
                ", num=" + num +
                ", content=" + content +
                ", isHasContent=" + isHasContent +
                ", first=" + first +
                ", last=" + last +
                ", isHasNext=" + isHasNext +
                ", isHasPrevious=" + isHasPrevious +
                ", navigatepageNums=" + Arrays.toString(navigatepageNums) +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
