package com.xgg.microservices.security.captcha;

import com.xgg.microservices.pojo.vo.ImageCaptchaVO;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 图片验证码生成接口
 */
public interface CaptchaGenerate {
    /**
     * 生成图片验证码
     *
     * @return
     */
    ImageCaptchaVO generate();
}
