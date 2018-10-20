package com.imooc.sellv1.utils;

import com.imooc.sellv1.vo.ResultVO;

public class ResultVOUtils {

    public  static ResultVO success(Object object){
        ResultVO obj=new ResultVO<>();
        obj.setCode(0);
        obj.setMsg("成功");
        obj.setData(object);
        return  obj;
    }

    public  static  ResultVO success(){
        return success(null);
    }


    public static  ResultVO error(Integer code,String msg){
        ResultVO  vo=new ResultVO();
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }
}
