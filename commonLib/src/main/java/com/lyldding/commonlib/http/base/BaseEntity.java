package com.lyldding.commonlib.http.base;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public class BaseEntity<T> {
    private int code;
    private String msg = "";
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
