package com.tqmall.zeus.service;

import java.io.Serializable;

/**
 * 需要抽公共
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 7401743853528330366L;

    private T                 data;
    private Boolean           success;
    private String            message;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
