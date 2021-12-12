package com.wxx.library.management.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Wang
 */
@Data
@AllArgsConstructor
public class RespBean {

    private Integer code;
    private String msg;
    private Object data;

    public static RespBean success(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean success(String msg, Object data) {
        return new RespBean(200, msg, data);
    }

    public static RespBean success(Object data) {
        return new RespBean(200, "成功", data);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, HttpStatus code) {
        return new RespBean(code.value(), msg, null);
    }
}
