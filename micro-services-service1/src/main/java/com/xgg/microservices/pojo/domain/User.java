package com.xgg.microservices.pojo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
/**
* @Description
* @Author  renchengwei
* @Date   2019-08-01
* @Param  
* @Return      
* @Exception   
* 
*/
public class User implements Serializable {

    private Long id;

    private String loginName;

    private String password;

    private String userName;

    private String mobile;

    private String passwordSalt;

    private String locked;

    private Date gmtCreate;

    private Date gmtModify;

    private String isDeleted;

    private static final long serialVersionUID = 1L;

}