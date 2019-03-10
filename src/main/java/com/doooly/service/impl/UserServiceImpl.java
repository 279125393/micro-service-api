package com.doooly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doooly.entity.UserEntity;
import com.doooly.mapper.UserMapper;
import com.doooly.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hutao
 * @since 2019-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
