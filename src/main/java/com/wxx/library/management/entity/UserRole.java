package com.wxx.library.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户和角色绑定关系(UserRole)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 21:53:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

}
