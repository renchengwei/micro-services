package com.xgg.microservices.security.properties;

import com.xgg.microservices.security.enums.LoginTypeEnum;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
public class BrowserProperties {

    private String loginPage = "authentication/require";
    private String loginProcessingUrl = "/login";
    private String successForwardUrl = "/index";
    private String failureForwardUrl = "/login.html";
    private String requireUrl = "/login.html";

    private LoginTypeEnum loginType = LoginTypeEnum.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public String getFailureForwardUrl() {
        return failureForwardUrl;
    }

    public void setFailureForwardUrl(String failureForwardUrl) {
        this.failureForwardUrl = failureForwardUrl;
    }

    public String getRequireUrl() {
        return requireUrl;
    }

    public void setRequireUrl(String requireUrl) {
        this.requireUrl = requireUrl;
    }
}
