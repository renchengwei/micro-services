package com.xgg.microservices.security.captcha;

import com.xgg.microservices.security.properties.SecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
@Component("smsCaptchaGenerate")
public class SmsCaptchaGenerate implements CaptchaGenerate {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public CaptchaVO generate() {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCaptcha().getSms().getLength());
        return new CaptchaVO(code, securityProperties.getCaptcha().getSms().getExpireSeconds());
    }
}
