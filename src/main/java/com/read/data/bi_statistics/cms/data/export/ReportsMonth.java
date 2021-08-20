package com.read.data.bi_statistics.cms.data.export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.io.Serializable;

/**
 * @author: tianyong
 * @Time: 2019/2/27 13:53
 * @description:月度报表实体类
 */
public class ReportsMonth implements Serializable {

    @Excel(name = "日期" ,orderNum = "0")
    private Integer dt;
    @Excel(name = "日期类别", orderNum = "1", isColumnHidden=true)
    private Integer date_type;
    @Excel(name = "插入时间", orderNum = "2", isColumnHidden=true)
    private Integer insert_time;
    @Excel(name = "更新时间", orderNum = "3", isColumnHidden=true)
    private Integer update_time;
    @Excel(name = "月活跃", orderNum = "4")
    private Integer muv;
    @Excel(name = "月平均DAU", orderNum = "5")
    private Integer dau_sum;
    @Excel(name = "月消费人数", orderNum = "6")
    private Integer pay_uv;
    @Excel(name = "月消费金额(元)", orderNum = "7")
    private Double pay_money;
    @Excel(name = "月人均消费(元)", orderNum = "8")
    private Double pay_money_uv;
    @Excel(name = "月充值人数", orderNum = "9")
    private Integer recharge_uv;
    @Excel(name = "月充值金额(元)", orderNum = "10")
    private Double recharge_money;
    @Excel(name = "月人均充值(元)", orderNum = "11")
    private Double recharge_money_uv;
    @Excel(name = "个人活跃作者数", orderNum = "12")
    private Integer author_uv;

    public ReportsMonth() {}

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getDate_type() {
        return date_type;
    }

    public void setDate_type(Integer date_type) {
        this.date_type = date_type;
    }

    public Integer getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Integer insert_time) {
        this.insert_time = insert_time;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
    }

    public Integer getMuv() {
        return muv;
    }

    public void setMuv(Integer muv) {
        this.muv = muv;
    }

    public Integer getDau_sum() {
        return dau_sum;
    }

    public void setDau_sum(Integer dau_sum) {
        this.dau_sum = dau_sum;
    }

    public Integer getRecharge_uv() {
        return recharge_uv;
    }

    public void setRecharge_uv(Integer recharge_uv) {
        this.recharge_uv = recharge_uv;
    }

    public Double getRecharge_money() {
        return recharge_money;
    }

    public void setRecharge_money(Double recharge_money) {
        this.recharge_money = recharge_money;
    }

    public Integer getPay_uv() {
        return pay_uv;
    }

    public void setPay_uv(Integer pay_uv) {
        this.pay_uv = pay_uv;
    }

    public Double getPay_money() {
        return pay_money;
    }

    public void setPay_money(Double pay_money) {
        this.pay_money = pay_money;
    }

    public Integer getAuthor_uv() {
        return author_uv;
    }

    public void setAuthor_uv(Integer author_uv) {
        this.author_uv = author_uv;
    }

    public Double getRecharge_money_uv() {
        return recharge_money_uv;
    }

    public void setRecharge_money_uv(Double recharge_money_uv) {
        this.recharge_money_uv = recharge_money_uv;
    }

    public Double getPay_money_uv() {
        return pay_money_uv;
    }

    public void setPay_money_uv(Double pay_money_uv) {
        this.pay_money_uv = pay_money_uv;
    }

    @Override
    public String toString() {
        return "ReportsMonth{" +
                "dt=" + dt +
                ", date_type=" + date_type +
                ", insert_time=" + insert_time +
                ", update_time=" + update_time +
                ", muv=" + muv +
                ", dau_sum=" + dau_sum +
                ", recharge_uv=" + recharge_uv +
                ", recharge_money=" + recharge_money +
                ", recharge_money_uv=" + recharge_money_uv +
                ", pay_uv=" + pay_uv +
                ", pay_money=" + pay_money +
                ", pay_money_uv=" + pay_money_uv +
                ", author_uv=" + author_uv +
                '}';
    }
}
