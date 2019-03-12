package com.sanjin.util;


import com.sanjin.Vo.ResultVo;
import com.sanjin.enums.ResultEnum;

/**
 * @description: ResultVoUtils
 * @author: sanjin
 * @date: 2019.3.12
 */
public class ResultVoUtils {
    public static ResultVo success(Object data) {
        ResultVo resultVo = success(ResultEnum.OK);
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo success(ResultEnum resultEnum) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(resultEnum.getCode());
        resultEnum.getMsg();
        return resultVo;
    }
}
