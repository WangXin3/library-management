package com.wxx.library.management.config;

import com.wxx.library.management.util.JwtUtil;
import com.wxx.library.management.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author wangxin
 * @since 2021/12/12
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final StringRedisTemplate stringRedisTemplate;
    private final JwtUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LoginFilter loginFilter = new LoginFilter(authenticationManager(), jwtUtil, stringRedisTemplate);
        TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(authenticationManager(), jwtUtil, stringRedisTemplate);
        http.exceptionHandling()
                // 自定义 401 Unauthorized 未授权，就是没有登录，header中没有传Token
                .authenticationEntryPoint((request, response, authException)
                        -> ResponseUtil.writerError(response, "请先登录!", HttpStatus.UNAUTHORIZED))
                // 自定义 403 Forbidden 拒绝访问，就是你没有权限访问这个接口
                .accessDeniedHandler((request, response, accessDeniedException)
                        -> ResponseUtil.writerError(response, "无权限访问该接口！", HttpStatus.FORBIDDEN))
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/logout")
                .addLogoutHandler(new TokenLogoutHandler(jwtUtil, stringRedisTemplate))
                .and()
                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义userDetailService和密码加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        // 配置不需要登录就可以访问的资源
        web.ignoring().antMatchers("/swagger-ui.html/**", "/favicon.ico");
    }
}
