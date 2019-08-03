package com.xgg.microservices.security.captcha;

import com.xgg.microservices.pojo.vo.ImageCaptchaVO;
import com.xgg.microservices.security.properties.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 图片验证码过滤器
 */
@Slf4j
@Getter
@Setter
public class CaptchaFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AuthenticationFailureHandler failureHandler;
    private SecurityProperties securityProperties;
    private Set<String> interceptUrlSet = new HashSet<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String configInterceptUrl = securityProperties.getImageCaptcha().getInterceptImageUrl();
        if (StringUtils.isNotBlank(configInterceptUrl)) {
            String[] configInterceptUrlArray = StringUtils.split(configInterceptUrl, ",");
            interceptUrlSet = Stream.of(configInterceptUrlArray).collect(Collectors.toSet());
        }
        interceptUrlSet.add(securityProperties.getBrowser().getLoginProcessingUrl());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("验证码验证请求路径:[{}]", request.getRequestURI());
        // 默认放行
        boolean action = false;

        for (String url : interceptUrlSet) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }

        if(action) {
            try {
                validate(request);
            }catch (CaptchaException e) {
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {

        ServletWebRequest swr = new ServletWebRequest(request);
        ImageCaptchaVO imageCodeInSession = (ImageCaptchaVO) sessionStrategy.getAttribute(swr, CaptchaController.IMAGE_CAPTCHA_SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new CaptchaException("验证码的值不能为空");
        }
        if (imageCodeInSession == null) {
            throw new CaptchaException("验证码不存在");
        }
        if (imageCodeInSession.isExpried()) {
            sessionStrategy.removeAttribute(swr, CaptchaController.IMAGE_CAPTCHA_SESSION_KEY);
            throw new CaptchaException("验证码已过期");
        }
        if (!StringUtils.equals(imageCodeInSession.getCode(), codeInRequest)) {
            throw new CaptchaException("验证码不匹配");
        }
        //验证通过 移除缓存
        sessionStrategy.removeAttribute(swr, CaptchaController.IMAGE_CAPTCHA_SESSION_KEY);
    }
}
