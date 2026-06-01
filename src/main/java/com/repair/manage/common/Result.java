package com.repair.manage.common;

import lombok.Data;

/**
 * @author wuming
 * @description: 后端返回给前端统一的响应对象
 */
@Data
public class Result {

    private int code;    // 状态码
    private String msg;  // 返回消息
    private Object data;      // 返回数据

    // 成功静态方法
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    // 成功静态方法
    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(null);
        return result;
    }

    // 成功静态方法(自定义消息)
    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    // 错误静态方法
    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    // 错误静态方法
    public static Result error(String msg) {
        return error(500, msg);
    }

    // 错误静态方法
    public static Result error() {
        return error(500, "服务繁忙。。。");
    }

}
