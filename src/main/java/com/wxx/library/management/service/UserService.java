package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.User;

/**
 * 用户(User)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 21:15:49
 */
public interface UserService extends IService<User> {

    User getUserByUsername(String username);

    User infoByToken();
}

