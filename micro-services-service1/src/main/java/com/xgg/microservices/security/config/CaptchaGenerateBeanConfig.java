package com.xgg.microservices.security.config;

import com.xgg.microservices.security.captcha.CaptchaGenerate;
import com.xgg.microservices.security.captcha.ImageCaptchaGenerate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 图片验证码生成器配置，默认使用ImageCaptchaGenerate
 */
@Configuration
public class CaptchaGenerateBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCaptchaGenerate")
    public CaptchaGenerate imageCaptchaGenerate() {
        ImageCaptchaGenerate imageCaptchaGenerate = new ImageCaptchaGenerate();
        return imageCaptchaGenerate;
    }
}
