package com.wxx.library.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户(User)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    /**
     * 登录账号
     */
    private String username;

    /**
     * 昵称/姓名
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别 0-女 1-男
     */
    private Integer gender;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * admin账号 0-否 1-是
     */
    private Integer admin;

    /**
     * 帐号状态 0-禁用 1-启用
     */
    private Integer enabled;

}
