package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wangxin
 * @since 2022/1/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {

    private List<String> roles;

    /**
     * 权限集合
     */
    private List<String> permissions;
}
