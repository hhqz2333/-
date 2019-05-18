package com.lv.sell.vo;

import lombok.Data;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/24 0024  10:47
 * @modified By：
 */
@Data
public class ResultVo<T> {

    /**
     * 错误代码
     */
    private Integer code;

    /**
     *具体提示
     */
    private String msg;

    /**
     * 数据
     */
    private T data;
}
