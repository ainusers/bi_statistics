package com.read.data.bi_statistics.cms.data.export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.io.Serializable;

/**
 * @author: tianyong
 * @Time: 2019/2/27 13:53
 * @description:月度产品报表实体类
 */
public class ReportsProductMonth implements Serializable {

    @Excel(name = "日期" ,orderNum = "0")
    private Integer dt;
    @Excel(name = "平台", orderNum = "1")
    private String apptype;
    @Excel(name = "pv", orderNum = "2")
    private Integer pv;
    @Excel(name = "uv", orderNum = "3")
    private Integer uv;
    @Excel(name = "新用户", orderNum = "4")
    private Integer nuv;
    @Excel(name = "阅读uv", orderNum = "5")
    private Integer readuv;
    @Excel(name = "阅读章节", orderNum = "6")
    private Integer readpv;
    @Excel(name = "人均阅读章节", orderNum = "7")
    private Integer avg_readpv;
    @Excel(name = "消费金额(元)", orderNum = "8")
    private Double pay_money;
    @Excel(name = "消费用户", orderNum = "9")
    private Integer pay_uv;
    @Excel(name = "充值金额(元)", orderNum = "10")
    private Double recharge_money;
    @Excel(name = "充值用户", orderNum = "11")
    private Integer recharge_uv;

    public ReportsProductMonth() {}


    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getNuv() {
        return nuv;
    }

    public void setNuv(Integer nuv) {
        this.nuv = nuv;
    }

    public Integer getReaduv() {
        return readuv;
    }

    public void setReaduv(Integer readuv) {
        this.readuv = readuv;
    }

    public Integer getReadpv() {
        return readpv;
    }

    public void setReadpv(Integer readpv) {
        this.readpv = readpv;
    }

    public Integer getAvg_readpv() {
        return avg_readpv;
    }

    public void setAvg_readpv(Integer avg_readpv) {
        this.avg_readpv = avg_readpv;
    }

    public Double getPay_money() {
        return pay_money;
    }

    public void setPay_money(Double pay_money) {
        this.pay_money = pay_money;
    }

    public Integer getPay_uv() {
        return pay_uv;
    }

    public void setPay_uv(Integer pay_uv) {
        this.pay_uv = pay_uv;
    }

    public Double getRecharge_money() {
        return recharge_money;
    }

    public void setRecharge_money(Double recharge_money) {
        this.recharge_money = recharge_money;
    }

    public Integer getRecharge_uv() {
        return recharge_uv;
    }

    public void setRecharge_uv(Integer recharge_uv) {
        this.recharge_uv = recharge_uv;
    }

    @Override
    public String toString() {
        return "ReportsProductMonth{" +
                "dt=" + dt +
                ", apptype=" + apptype +
                ", pv=" + pv +
                ", uv=" + uv +
                ", nuv=" + nuv +
                ", readuv=" + readuv +
                ", readpv=" + readpv +
                ", avg_readpv=" + avg_readpv +
                ", pay_money=" + pay_money +
                ", pay_uv=" + pay_uv +
                ", recharge_money=" + recharge_money +
                ", recharge_uv=" + recharge_uv +
                '}';
    }
}
