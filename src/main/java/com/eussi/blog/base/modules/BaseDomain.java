package com.eussi.blog.base.modules;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxueming on 2018/1/2.
 */
public class BaseDomain implements Serializable{
    private String orderBy;

    private String in;

    private String notIn;

    private String limit;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getNotIn() {
        return notIn;
    }

    public void setNotIn(String notIn) {
        this.notIn = notIn;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
        /*
        系统中一般都要打印日志的，因为所有实体的toString()方法 都用的是简单的"+"，
        因为每"＋" 一个就会 new 一个 String 对象，这样如果系统内存小的话会暴内存（前提系统实体比较多）。
        使用ToStringBuilder就可以避免暴内存这种问题的。
         */
    }
}
