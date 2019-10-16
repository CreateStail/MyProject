package com.synway.exception;

/**
 * 自定义异常类
 */
public class YthException extends RuntimeException {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    public YthException(){
        super();
    }

    public YthException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
