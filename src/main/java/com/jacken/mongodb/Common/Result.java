package com.jacken.mongodb.Common;

import java.util.Collections;
import java.util.List;

public class Result<T> {
    private  String code;
    private  String msg;
    private T data;

    public Result(T data) {
        this.code="0";
        this.msg="操作成功";
        this.data = data;
    }

    public Result() {
        this.code="0";
        this.msg="操作成功";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
