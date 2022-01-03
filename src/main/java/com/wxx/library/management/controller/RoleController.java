package com.wxx.library.management.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Role;
import com.wxx.library.management.service.RoleMenuService;
import com.wxx.library.management.service.RoleService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final RoleMenuService roleMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param role 查询实体
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('role:list')")
    public RespBean selectAll(Page<Role> page, Role role) {
        return RespBean.success(roleService.page(page,
                new LambdaQueryWrapper<Role>().like(StrUtil.isNotBlank(role.getName()), Role::getName, role.getName())));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @PreAuthorize("@lm.check('role:list')")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.success(roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('role:add')")
    public RespBean insert(@RequestBody Role role) {
        return RespBean.success(roleService.save(role));
    }

    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PreAuthorize("@lm.check('role:edit')")
    public RespBean update(@RequestBody Role role) {
        return RespBean.success(roleService.updateById(role));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PreAuthorize("@lm.check('role:del')")
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.success(roleService.removeByIdsAndOther(idList));
    }

    @PostMapping("/saveOrUpdateMenuByRoleId/{roleId}")
    @PreAuthorize("@lm.check('role:edit')")
    public RespBean saveOrUpdateMenuByRoleId(@PathVariable("roleId") String roleId,
                                             @RequestBody List<String> menuIds) {
        return RespBean.success(roleMenuService.saveOrUpdateMenuByRoleId(roleId, menuIds));
    }
}

