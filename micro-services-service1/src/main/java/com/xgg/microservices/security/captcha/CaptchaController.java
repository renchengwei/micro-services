package com.xgg.microservices.security.captcha;

import com.xgg.microservices.pojo.vo.ImageCaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 图片验证码控制器
 */
@RestController
public class CaptchaController {
    public static final String IMAGE_CAPTCHA_SESSION_KEY="image_captcha_session_key";
    private static final String FORMAT_NAME="JPEG";

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private CaptchaGenerate captchaGenerate;

    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha/image")
    public  void createKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接口生成验证码
        ImageCaptchaVO imageCaptcha = captchaGenerate.generate();
        //2.保存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), IMAGE_CAPTCHA_SESSION_KEY, imageCaptcha);
        //3.写到响应流中，没有缓存
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(imageCaptcha.getImage(),FORMAT_NAME,response.getOutputStream());
    }

}
