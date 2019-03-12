package com.sanjin.Vo;

import lombok.Data;

/**
 * @description: 封装 api 返回结果的格式
 * @author: sanjin
 * @date: 2019.3.12
 */
@Data
public class ResultVo {
    // 全局状态码
    private Integer code;
    // 消息
    private String msg;
    // 数据
    private Object data;
}
