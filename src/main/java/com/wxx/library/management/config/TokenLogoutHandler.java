package com.wxx.library.management.config;

import cn.hutool.core.text.CharSequenceUtil;
import com.wxx.library.management.util.JwtUtil;
import com.wxx.library.management.util.ResponseUtil;
import com.wxx.library.management.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangxin
 * @since 2021/12/12
 */
@AllArgsConstructor
public class TokenLogoutHandler implements LogoutHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("Authorization");
        if (CharSequenceUtil.isNotBlank(token)) {
            MyUserDetails myUserDetails = jwtUtil.getInfoFromToken(token);
            SecurityUtil.remove(myUserDetails.getUsername());
        }

        try {
            ResponseUtil.writerSuccess(response, "退出登录！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
