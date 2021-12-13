package com.wxx.library.management.util;


import cn.hutool.core.date.DateUtil;
import com.wxx.library.management.config.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * @param userDetail 载荷中的数据
     * @return /
     */
    public String generateToken(MyUserDetails userDetail) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", userDetail.getUsername());
        map.put("id", userDetail.getId());

        return "Bearer " + Jwts.builder()
                .setClaims(map) // 设置token数据
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
    public MyUserDetails getInfoFromToken(String token) {
        token = token.substring(7);
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        String username = (String) body.get("username");
        String id = (String) body.get("id");
        return new MyUserDetails(username, id);
    }
}