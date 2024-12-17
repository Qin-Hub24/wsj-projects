package com.app.raghu.util;

import lombok.Data;

/**
 * 统一响应结果封装类
 */

@Data
public class Result {
    private Integer code;//1 成功 , 0 失败
    private String msg; //提示信息
    private Object data; //数据 data

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result success() {
        return new Result(200, "success", null);
    }

    public static Result error(String msg) {
        return new Result(400, msg, null);
    }
}
