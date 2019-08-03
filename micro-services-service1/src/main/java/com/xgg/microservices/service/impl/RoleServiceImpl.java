package com.xgg.microservices.service.impl;

import com.xgg.microservices.pojo.po.RolePO;
import com.xgg.microservices.dao.RoleMapper;
import com.xgg.microservices.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePO> implements IRoleService {

}
