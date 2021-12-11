package com.wxx.library.management.constant;

/**
 * @author wangxin
 * @since 2021/12/11
 */
public enum Num {
    N(0),
    Y(1);

    private final Integer v;

    Num(Integer v) {
        this.v = v;
    }

    public Integer v() {
        return v;
    }
}
