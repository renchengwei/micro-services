package com.xgg.microservices.service.impl;

import com.xgg.microservices.pojo.po.UserPO;
import com.xgg.microservices.dao.UserMapper;
import com.xgg.microservices.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renchengwei
 * @since 2019-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

}
