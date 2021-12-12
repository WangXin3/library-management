package com.wxx.library.management.util;


import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Wang
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtUtil {

    /**
     * 密匙KEY
     */
    private String secret;

    /**
     * 过期时间 单位分钟
     */
    private Integer expire;

    /**
     * 生成token
     *
     * @param username 载荷中的数据
     * @return /
     */
    public String generateToken(String username) {
        return "Bearer " + Jwts.builder()
                .setSubject(username) // 设置该token的主题
                .setExpiration(DateUtil.offsetMinute(new Date(), this.expire).toJdkDate()) // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 加密算法
                .compressWith(CompressionCodecs.GZIP) // 压缩算法
                .compact();
    }


    /**
     * 获取token中的用户信息
     *
     * @param token 用户请求中的令牌
     * @return 用户信息
     */
    public String getInfoFromToken(String token) {
        token = token.substring(7);
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
    }
}