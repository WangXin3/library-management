package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxx.library.management.entity.Role;

import java.util.List;

/**
 * 角色(Role)表数据库访问层
 *
 * @author wangxin
 * @since 2021-12-11 21:15:40
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByUserId(String id);
}

