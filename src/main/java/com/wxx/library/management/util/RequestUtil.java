package com.wxx.library.management.util;

import com.alibaba.fastjson.JSON;
import com.wxx.library.management.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangxin
 * @since 2021/12/12
 */
public class RequestUtil {

    private RequestUtil() {
    }

    public static User getUserFormRequestBody(HttpServletRequest request) {
        User user = new User();
        InputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1; ) {
                sb.append(new String(b, 0, n));
            }

            user = JSON.parseObject(sb.toString(), com.wxx.library.management.entity.User.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }
}
