package com.xgg.microservices.pojo.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: renchengwei
 * @Date: 2019-07-31
 * @Description: TODO
 */
@Data
public class ImageCaptchaVO implements Serializable {

    private static final long serialVersionUID = 6363869750967464479L;

    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCaptchaVO(BufferedImage image, String code, int expireAfterSeconds){
        this.image = image;
        this.code = code;
        //多少秒后
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public ImageCaptchaVO(BufferedImage image, String code, LocalDateTime expireTime){
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 是否失效
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
