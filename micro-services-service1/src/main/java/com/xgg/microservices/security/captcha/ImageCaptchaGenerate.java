package com.xgg.microservices.security.captcha;

import com.google.code.kaptcha.Producer;
import com.xgg.microservices.pojo.vo.ImageCaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 默认的图片验证码生成器
 */
public class ImageCaptchaGenerate implements CaptchaGenerate {

    @Autowired
    private Producer producer;

    @Override
    public ImageCaptchaVO generate() {
        String code = producer.createText();
        BufferedImage bufferedImage = producer.createImage(code);
        return new ImageCaptchaVO(bufferedImage, code, 60 * 5);
    }
}
