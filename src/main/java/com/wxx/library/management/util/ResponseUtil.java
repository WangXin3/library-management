package com.wxx.library.management.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangxin
 * @since 2021/12/12
 */
public class ResponseUtil {

    private ResponseUtil() {
    }

    public static final String CONTENT_TYPE = "application/json;charset=utf-8";

    public static void writerError(HttpServletResponse response, String msg, HttpStatus status) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(RespBean.error(msg, status)));
        out.flush();
        out.close();
    }

    public static void writerError(HttpServletResponse response, String msg) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(RespBean.error(msg)));
        out.flush();
        out.close();
    }

    public static void writerSuccess(HttpServletResponse response, String msg, String data) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(RespBean.success(msg, data)));
        out.flush();
        out.close();
    }

    public static void writerSuccess(HttpServletResponse response, String msg) throws IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(RespBean.success(msg)));
        out.flush();
        out.close();
    }
}
