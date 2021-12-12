package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.User;
import com.wxx.library.management.service.UserService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户(User)表控制层
 *
 * @author wangxin
 * @since 2021-12-11 21:15:49
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('user:list')")
    public RespBean selectAll(Page<User> page, User user) {
        return RespBean.success(userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @PreAuthorize("@lm.check('user:list')")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.success(userService.getById(id));
    }

    @GetMapping("/infoByToken")
    public RespBean infoByToken() {
        return RespBean.success(userService.infoByToken());
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('user:add')")
    public RespBean insert(@RequestBody User user) {
        return RespBean.success(userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PreAuthorize("@lm.check('user:edit')")
    public RespBean update(@RequestBody User user) {
        return RespBean.success(userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PreAuthorize("@lm.check('user:del')")
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.success(userService.removeByIds(idList));
    }
}

