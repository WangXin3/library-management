package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wangxin
 * @since 2022/1/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends Role {

    private List<String> menuIds;
}
