package ssm.system.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


public class ControllerUtils {

    
    /**
     * 页面跳转
     * @param request
     * @param response
     * @param page
     */
    public static void locationHref(HttpServletRequest request,  
            HttpServletResponse response, String page){
        
        try {
            // 设置request和response的字符集，防止乱码  
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");  
            PrintWriter out = response.getWriter();  
            StringBuilder builder = new StringBuilder();  
            builder.append("<script type=\"text/javascript\">");  
            builder.append("window.top.location.href='");  
            builder.append(page);  
            builder.append("';");  
            builder.append("</script>");  
            out.print(builder.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
	/**
     * 
     * <br>
     * <b>功能：</b>输出json格式<br>
     * @param response
     * @param jsonStr
     * @throws Exception
     */
    public static void writeJsonStr(String jsonStr, HttpServletResponse response) {
            writer(jsonStr, response);
    }
    
    public static void writeJson(Object object, HttpServletResponse response){
            try {
                writer(JSON.toJSONString(object), response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    /**
     * 
     * <br>
     * <b>功能：</b>输出HTML代码<br>
     * @param response
     * @param htmlStr
     * @throws Exception
     */
    public static void writeHtml(String htmlStr, HttpServletResponse response) {
        writer(htmlStr, response);
    }
    
    private static void writer(String str, HttpServletResponse response){
        try {
            //设置页面不缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            // 设置类型和编码
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * 获取年份
     */
  //获取当前时间年月日组合的字符串  20161117
  	public static int getYear(){
  		Calendar now = Calendar.getInstance();  
  		int year = now.get(Calendar.YEAR);
  		return year;
  	}
}
