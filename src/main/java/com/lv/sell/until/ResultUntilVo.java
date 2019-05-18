package com.lv.sell.until;

import com.lv.sell.vo.ResultVo;

/**
 * @author ：LV hang
 * @date ：Created in 2019/3/24 0024  14:26
 * @modified By：
 */
public class ResultUntilVo {

    /**
     * 方法执行成功后的返回(带参数)
     *
     * @param o
     * @return
     */
    public static ResultVo success(Object o) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功！");
        resultVo.setData(o);
        return resultVo;
    }

    /**
     * 方法执行成功之后，没有参数
     * @return
     */
    public static  ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }


}
