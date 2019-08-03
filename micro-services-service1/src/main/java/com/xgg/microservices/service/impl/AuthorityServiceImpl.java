package com.xgg.microservices.service.impl;

import com.xgg.microservices.pojo.po.AuthorityPO;
import com.xgg.microservices.dao.AuthorityMapper;
import com.xgg.microservices.service.IAuthorityService;
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
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AuthorityPO> implements IAuthorityService {

}
