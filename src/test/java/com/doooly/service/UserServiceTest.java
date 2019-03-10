package com.doooly.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doooly.entity.UserEntity;
import com.doooly.mapper.UserMapper;
import com.doooly.server.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void testSelectList(){
        List<UserEntity> list = userService.list();
        Assert.assertEquals(5, list.size());
        list.forEach(System.out::println);
    }
}
