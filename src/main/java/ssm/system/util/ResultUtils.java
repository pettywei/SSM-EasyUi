package ssm.system.util;

import javax.servlet.http.HttpServletResponse;

import ssm.system.entity.Result;

public class ResultUtils {

    /**
     * 成功消息
     * @param msg
     * @param response
     */
    public static void renderSuccess(String msg, HttpServletResponse response){
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        ControllerUtils.writeJson(result, response);
    }
    /**
     * 成功消息
     * @param obj
     * @param response
     */
    public static void renderSuccess(Object obj,  HttpServletResponse response){
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        ControllerUtils.writeJson(result, response);
    }
    
    /**
     * 失败消息
     * @param msg
     * @param response
     */
    public static void renderFail(String msg, HttpServletResponse response){
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(msg);
        ControllerUtils.writeJson(result, response);
    }
    /**
     * 成功消息
     * @param obj
     * @param response
     */
    public static void renderFail(Object obj,  HttpServletResponse response){
        Result result = new Result();
        result.setSuccess(false);
        result.setObj(obj);
        ControllerUtils.writeJson(result, response);
    }
}
