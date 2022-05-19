/**
 * @(#)CommonResult.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.config;

import com.ocean.sever.constant.CommonCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author back
 */
@Getter
@Setter
public class CommonResult {
    private String message;
    private int code;
    private Object data;

    public static CommonResult success(){
        CommonResult CommonResult =new CommonResult();
        CommonResult.setCode(CommonCode.SUCCESS.getCode());
        CommonResult.setMessage(CommonCode.SUCCESS.getMessage());
        return CommonResult;
    }

    public static CommonResult innerError(){
        CommonResult CommonResult =new CommonResult();
        CommonResult.setCode(CommonCode.INNER_ERROR.getCode());
        CommonResult.setMessage(CommonCode.INNER_ERROR.getMessage());
        return CommonResult;
    }

    public static CommonResult outError() {

        CommonResult CommonResult = new CommonResult();
        CommonResult.setCode(CommonCode.OUTTER_ERROR.getCode());
        CommonResult.setMessage(CommonCode.OUTTER_ERROR.getMessage());
        return CommonResult;
    }

    public static CommonResult successWithData(Object data){

        CommonResult CommonResult =new CommonResult();
        CommonResult.setCode(CommonCode.SUCCESS.getCode());
        CommonResult.setMessage(CommonCode.SUCCESS.getMessage());
        CommonResult.setData(data);
        return CommonResult;
    }

    public static CommonResult userNotExist(){

        CommonResult CommonResult =new CommonResult();
        CommonResult.setCode(CommonCode.NO_USER.getCode());
        CommonResult.setMessage(CommonCode.NO_USER.getMessage());
        CommonResult.setData(null);
        return CommonResult;
    }

    public static CommonResult result(int code,String message,Object data){

        CommonResult CommonResult =new CommonResult();
        CommonResult.setCode(code);
        CommonResult.setMessage(message);
        CommonResult.setData(data);
        return CommonResult;
    }
}