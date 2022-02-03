package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.Borrow;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangxin
 * @since 2022/2/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BorrowVO extends Borrow {

    private String bookName;
    private String username;
    private String image;
}
