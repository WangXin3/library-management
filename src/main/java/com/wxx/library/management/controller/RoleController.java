package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Role;
import com.wxx.library.management.service.RoleService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色(Role)表控制层
 *
 * @author wangxin
 * @since 2021-12-11 21:15:40
 */
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param role 查询实体
     * @return 所有数据
     */
    @GetMapping
    public RespBean selectAll(Page<Role> page, Role role) {
        return RespBean.successData(roleService.page(page, new QueryWrapper<>(role)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.successData(roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping
    public RespBean insert(@RequestBody Role role) {
        return RespBean.successData(roleService.save(role));
    }

    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PutMapping
    public RespBean update(@RequestBody Role role) {
        return RespBean.successData(roleService.updateById(role));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.successData(roleService.removeByIds(idList));
    }
}

