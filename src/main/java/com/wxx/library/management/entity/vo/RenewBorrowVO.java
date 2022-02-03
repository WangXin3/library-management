package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.RenewBorrow;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangxin
 * @since 2022/2/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RenewBorrowVO extends RenewBorrow {

    private String username;
    private String bookName;
    private String image;
}
