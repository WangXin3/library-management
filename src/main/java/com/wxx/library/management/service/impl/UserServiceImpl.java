package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.mapper.UserMapper;
import com.wxx.library.management.entity.User;
import com.wxx.library.management.service.UserService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

/**
 * 用户(User)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:49
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByUsername(String username) {
        return this.lambdaQuery().eq(User::getUsername, username).one();
    }
}

