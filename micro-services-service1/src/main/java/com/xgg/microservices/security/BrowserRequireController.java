package com.xgg.microservices.security;

import com.xgg.microservices.pojo.vo.ResponseVO;
import com.xgg.microservices.security.enums.LoginTypeEnum;
import com.xgg.microservices.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
@RestController
@Slf4j
public class BrowserRequireController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseVO<Object> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("BrowserRequireController进来了 啦啦啦");
        // 从session缓存中获取引发跳转的请求
        if(LoginTypeEnum.REDIRECT.equals(securityProperties.getBrowser().getLoginType())) {
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getRequireUrl());
        }
        return new ResponseVO<>(0,"访问的服务需要身份认证，请引导用户到登录页面");
    }


}
