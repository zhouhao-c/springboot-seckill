package com.zz.seckill.common;

import com.zz.seckill.bean.AJAXResult;

public abstract class BaseController {
    private ThreadLocal<AJAXResult> ajaxResult = new ThreadLocal<AJAXResult>();

    protected void start(){
        ajaxResult.set(new AJAXResult());
    }
    protected Object end(){
        Object obj = ajaxResult.get();
        ajaxResult.remove();
        return obj;
    }
    protected void data(Object data){
        AJAXResult r = ajaxResult.get();
        r.setData(data);
    }
    protected void success(){
        success(true);
    }
    protected void success(boolean flg){
        AJAXResult r = ajaxResult.get();
        r.setSuccess(flg);
    }
    protected void fail(){
        success(false);
    }
}
