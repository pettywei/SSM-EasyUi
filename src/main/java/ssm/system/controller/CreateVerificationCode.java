/* 																				
* @(#)CreateVerificationCode.java     																				
* create by weixin		2016/11/20																		2016/10/8
* update by weixin		2016/11/20																		2016/10/9
*/
package ssm.system.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/** 																				
* 类 <code>CreateVerificationCode </code>生成验证码																				
* 																				
* @author   魏昕 																				
* @version 2016/11/20 																																																												
*/
@Controller
@RequestMapping("/code")
public class CreateVerificationCode {
	/**kaptcha产生验证码所需的类*/
	@Autowired  
	private Producer captchaProducer = null;  
	/** 																				
	 * 获取验证码																				
	 * 																				
	 * @param   request			请求对象
	 *			response			返回对象																				
	 * @return	ModelAndView			Model与View的对象																																								
	 */  
	@RequestMapping("/captcha-image")  
	public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {    
	    /*设置返回头信息，内容类型为图片*/      
		response.setDateHeader("Expires", 0);  
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
		response.setHeader("Pragma", "no-cache");  
		response.setContentType("image/jpeg");  
	    /*产生验证码，并设置在session中*/    
		String capText = captchaProducer.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
		/*产生验证码图片，并返回给前端*/   
		BufferedImage bi = captchaProducer.createImage(capText);  
		ServletOutputStream out = response.getOutputStream();  
		ImageIO.write(bi, "jpg", out);  
		try {  
			out.flush();  
		} finally {  
			out.close();  
		}  
		return null;  
	}  
}
