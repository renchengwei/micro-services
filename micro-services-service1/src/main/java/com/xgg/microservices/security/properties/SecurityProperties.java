package com.xgg.microservices.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 自定配置核心配置
 */
@ConfigurationProperties(value = "xgg.auth",ignoreInvalidFields = true)
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private CaptchaProperties captcha = new CaptchaProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public CaptchaProperties getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaProperties captcha) {
        this.captcha = captcha;
    }
}
