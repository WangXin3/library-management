package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxx.library.management.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色绑定关系(UserRole)表数据库访问层
 *
 * @author wangxin
 * @since 2021-12-11 21:53:35
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> getPermissionByUserId(@Param("userId") String userId);
}

