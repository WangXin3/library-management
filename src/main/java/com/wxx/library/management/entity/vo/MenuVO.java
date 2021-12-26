package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wangxin
 * @since 2021/12/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO extends Menu {

    private Boolean hasChildren;
}
