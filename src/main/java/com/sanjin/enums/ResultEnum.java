package com.sanjin.enums;

import lombok.Getter;

/**
 * @description: api返回结果中全局状态码枚举类
 * @author: sanjin
 * @date: 2019.3.12
 */
@Getter
public enum ResultEnum {
    /**
     * 正常
     */
    OK(1, "success"),

    /**
     * 错误码从 100 开始
     */
    USER_EXISTS(100, "用户已存在"),
    UPLOAD_ERROR(101, "文件上传失败，请重新上传")
            ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
