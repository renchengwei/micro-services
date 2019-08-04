package com.xgg.microservices.security.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
 * @Description: 验证码控制器
 */
@RestController
@Slf4j
public class CaptchaController {
    public static final String CAPTCHA_SESSION_KEY = "captcha_session_key";
    private static final String FORMAT_NAME="JPEG";

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private CaptchaGenerate imageCaptchaGenerate;
    @Autowired
    private CaptchaGenerate smsCaptchaGenerate;
    @Autowired
    private SmsCaptchaSend smsCaptchaSend;

    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha/image")
    public  void createKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("获取图片验证码");
        //1.接口生成验证码
        ImageCaptchaVO imageCaptcha = (ImageCaptchaVO) imageCaptchaGenerate.generate();
        //2.保存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), CAPTCHA_SESSION_KEY, imageCaptcha);
        //3.写到响应流中，没有缓存
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(imageCaptcha.getImage(),FORMAT_NAME,response.getOutputStream());
    }

    /**
     * 获取短信验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha/sms")
    public  void createSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        log.info("获取短信验证码");

        //1.接口生成验证码
        CaptchaVO captchaVO = smsCaptchaGenerate.generate();
        //2.保存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), CAPTCHA_SESSION_KEY + "sms", captchaVO);
        //3.发送短信
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCaptchaSend.sendSms(mobile, captchaVO.getCode());
    }

}
