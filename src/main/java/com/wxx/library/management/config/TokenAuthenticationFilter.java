package com.wxx.library.management.config;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.fastjson.JSON;
import com.wxx.library.management.util.JwtUtil;
import com.wxx.library.management.util.ResponseUtil;
import com.wxx.library.management.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangxin
 * @since 2021/12/12
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (CharSequenceUtil.isNotBlank(token)) {
            MyUserDetails myUserDetails;
            try {
                myUserDetails = jwtUtil.getInfoFromToken(token);
            } catch (Exception e) {
                ResponseUtil.writerError(response, "篡改token!", HttpStatus.UNAUTHORIZED);
                return;
            }
            // 从缓存中获取权限列表
            String authorityList = SecurityUtil.get(myUserDetails.getUsername());

            Set<SimpleGrantedAuthority> authorities;
            if (CharSequenceUtil.isNotBlank(authorityList)) {
                authorities = Objects.requireNonNull(JSON.parseArray(authorityList, String.class)).stream()
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            } else {
                authorities = new HashSet<>();
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    myUserDetails.getUsername(), myUserDetails.getId(), authorities);
            // 将用户信息，设置到 SecurityContext 中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
