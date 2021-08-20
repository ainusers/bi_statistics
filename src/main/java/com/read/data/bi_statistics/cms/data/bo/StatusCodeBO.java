package com.read.data.bi_statistics.cms.data.bo;

import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.entity.ReturnBone;

/**
 * @author: tianyong
 * @Time: 2019/7/16 11:01
 * @description:状态码对象(错误)
 */
public class StatusCodeBO extends RuntimeException{


    private CodeInfo codeInfo;


    public StatusCodeBO(){}
    public StatusCodeBO(CodeInfo codeInfo){
        this.codeInfo = codeInfo;
        makeInfo();
    }


    public ReturnBone makeInfo() {
        ReturnBone rb = new ReturnBone();
        rb.setCode(codeInfo.getCode());
        rb.setMsg(codeInfo.getMsg());
        return rb;
    }


}
