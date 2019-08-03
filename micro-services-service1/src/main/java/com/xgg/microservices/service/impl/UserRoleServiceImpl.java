package com.xgg.microservices.service.impl;

import com.xgg.microservices.pojo.po.UserRolePO;
import com.xgg.microservices.dao.UserRoleMapper;
import com.xgg.microservices.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRolePO> implements IUserRoleService {

}
