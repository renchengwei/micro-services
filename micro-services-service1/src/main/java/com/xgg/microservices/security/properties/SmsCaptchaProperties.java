package com.xgg.microservices.security.properties;

/**
 * @Author: renchengwei
 * @Date: 2019-08-04
 * @Description: TODO
 */
public class SmsCaptchaProperties {
    /**
     * 长度
     */
    private int length=6;
    /**
     * 过期秒数 默认3分钟
     */
    private int expireSeconds=180;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
