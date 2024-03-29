package com.xgg.microservices.security.handler;

import com.alibaba.fastjson.JSON;
import com.xgg.microservices.security.enums.LoginTypeEnum;
import com.xgg.microservices.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 自定义登陆成功处理器
 */
@Component("xggAuthenticationSuccessHandler")
@Slf4j
public class XggAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);

        if(LoginTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())){
            //返回json处理 默认也是json处理

            response.setContentType("application/json;charset=UTF-8");
            log.info("认证信息:"+ JSON.toJSONString(authentication));
            response.getWriter().write(JSON.toJSONString(authentication));
        } else {
            // 如果用户定义的是跳转，那么就使用父类方法进行跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
