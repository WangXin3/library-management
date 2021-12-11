package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.UserRole;
import com.wxx.library.management.mapper.UserRoleMapper;
import com.wxx.library.management.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户和角色绑定关系(UserRole)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:53:35
 */
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<String> getPermissionByUserId(String userId) {
        return this.baseMapper.getPermissionByUserId(userId);
    }

}

