package com.read.data.bi_statistics.cms.data.enums;

/**
 * @author: tianyong
 * @Time: 2019/7/3 11:00
 * @description:错误码包装枚举类
 */
public enum CodeInfo {


    //状态码
    STATUS_CODE_400(400,"请求无效!"),
    STATUS_CODE_401(401,"用户认证失败!"),
    STATUS_CODE_403(403,"禁止访问!"),
    STATUS_CODE_404(404,"请求的网页不存在!"),
    STATUS_CODE_405(405,"资源被禁止!"),
    STATUS_CODE_500(500,"内部服务器错误,请联系管理员!"),

    //常规请求 自定义状态码
    CHART_INVALID_PARAM(100002,"图表请求参数错误!"),
    PAGE_INVALID_PARAM(100003,"页面请求参数错误!"),
    MENU_INVALID_PARAM(100004,"菜单请求参数错误!"),
    CHART_FIND_SUCCESS(100005,"查询结果为空!"),
    POST_INVALID_PARAM(100006,"POST请求参数转换错误!"),
    ADD_BASE_ATTRIBUTE_FAIL(100007,"添加基础属性值错误!"),
    ADD_EXTRA_ATTRIBUTE_FAIL(100008,"添加额外属性值错误!"),
    HANDLER_RETURN_DATA_FAIL(100009,"加工返回数据错误!"),
    TRANS_DATE_TYPE_FAIL(100010,"转换周期类别错误!"),
    TRANS_DATA_TYPE_FAIL(100011,"数据类型转换错误!"),
    REFLECT_INVOKE_METHOD_FAIL(100012,"反射调用方法错误!"),
    REFLECT_INVOKE_CONSTRUCTOR_FAIL(100013,"反射调用构造函数错误!"),
    GET_CURRENT_DATE_FAIL(100014,"获取当前日期错误!"),
    RETURN_TARGET_FAIL(100015,"返回当前接口类对象错误!"),
    USER_AUDIT_INTODB_FAIL(100016,"用户审计收集参数入库错误!"),
    EXPORT_EXCEL_FAIL(100017,"导出Excel-IO错误!"),

    //authority(权限) 自定义状态码
    USER_NOT_EXIST(100018,"用户不存在!"),
    ACCESS_USERINFO_ERROR(100019,"用户名或者密码错误!"),
    ACCESS_IS_USERINFO(100020,"原密码输入错误，可能非用户本人操作!");



    private String msg;
    private Integer code;

    CodeInfo(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
