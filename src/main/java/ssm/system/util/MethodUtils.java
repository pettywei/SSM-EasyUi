package ssm.system.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MethodUtils {
    
    public static final String salt = "javacoder";
	
    public static String md5(String str){
        return md5(str, salt);
    }
    
	public static String md5(String str,String salt)
	{
		return new Md5Hash(str, salt).toString();
	}
	

    /**
     * 取某对象中某字段的值
     * @param object
     * @param fieldName
     * @return
     */
    
    public static Object getFieldValue(Object object,String fieldName) {
        Object value =null;
        if(object!=null){
            Class clszz=object.getClass();
            Method getMdthod=null;
            try {
                getMdthod = clszz.getMethod("get"+StringUtils.capitalize(fieldName));
                value=(Object) getMdthod.invoke(object);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                getMdthod=null;
                clszz=null;
            }
        }
        return value;
    }
    

    /**
     * 给某个字段赋值
     * @param object
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object object,String fieldName,Object value) {
        if(object!=null){
            Class clszz=object.getClass();
            String setMethodName="";
            Method setMdthod=null;
            Field field =null;
            try {
                field = object.getClass().getDeclaredField(fieldName);
                setMethodName="set"+StringUtils.capitalize(fieldName);
                setMdthod=clszz.getDeclaredMethod(setMethodName, field.getType()); 
                setMdthod.invoke(object, value);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                setMdthod=null;
                setMethodName=null;
                clszz=null;
            }
        }
    }
    	
	
    /**
     * 设置Map的值
     * @param map
     * @param key
     * @param value
     */
    public static void setMapValue(Map<String, Float> map, String key, Float value){
        if(map.containsKey(key)){
            Float f = map.get(key);
            map.put(key, f+value);
        } else {
            map.put(key, value);
        }
    }
	
}
