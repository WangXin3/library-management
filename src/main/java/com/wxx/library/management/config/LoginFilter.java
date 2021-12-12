package com.wxx.library.management.config;

import com.alibaba.fastjson.JSON;
import com.wxx.library.management.util.JwtUtil;
import com.wxx.library.management.util.RequestUtil;
import com.wxx.library.management.util.ResponseUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangxin
 * @since 2021/12/12
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final StringRedisTemplate stringRedisTemplate;

    protected LoginFilter(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil, StringRedisTemplate stringRedisTemplate) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        com.wxx.library.management.entity.User user = RequestUtil.getUserFormRequestBody(request);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword(), new ArrayList<>());
        return authenticationManager.authenticate(authentication);
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        Set<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        // 将该用户的权限放入redis
        stringRedisTemplate.opsForValue().set(user.getUsername(), JSON.toJSONString(authorities));

        String token = jwtUtil.generateToken(user.getUsername());
        ResponseUtil.writerSuccess(response, "登录成功！", token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        ResponseUtil.writerError(response, "账号/密码错误或账号被冻结！");
    }
}
