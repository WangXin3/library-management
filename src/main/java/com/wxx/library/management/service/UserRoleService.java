package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.UserRole;

import java.util.List;

/**
 * 用户和角色绑定关系(UserRole)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 21:53:35
 */
public interface UserRoleService extends IService<UserRole> {

    List<String> getPermissionByUserId(String userId);
}

