package com.xgg.microservices.dao;

import com.xgg.microservices.pojo.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
/**
 * @Author renchengwei
 * @Date 2019-08-03
 * @Description
 */
@Repository
public interface UserMapper extends BaseMapper<UserPO> {

}
