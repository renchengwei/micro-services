package com.xgg.microservices.security.captcha;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 验证码生成接口
 */
public interface CaptchaGenerate {
    /**
     * 生成图片验证码
     *
     * @return
     */
    CaptchaVO generate();
}
