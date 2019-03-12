package com.sanjin.exception;


import com.sanjin.enums.ResultEnum;

/**
 * 项目异常类
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
