package com.doooly.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doooly.entity.UserEntity;
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
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        List<UserEntity> list = userMapper.selectList(null);
        Assert.assertEquals(5, list.size());
        list.forEach(System.out::println);
//        UserEntity user = new UserEntity();
//        user.setId(1L);
//        userMapper.selectList(user);
    }
    @Test
    public void testSelectBylambda(){
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(UserEntity::getId,1);
        List<UserEntity> list = userMapper.selectList(qw);
        Assert.assertEquals(1, list.size());
        list.forEach(System.out::println);
    }
    @Test
    public void testSelectList(){
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.select("id","name","email");
        qw.eq("id",1);
        List<UserEntity> list = userMapper.selectList(qw);
        Assert.assertEquals(1, list.size());
        list.forEach(System.out::println);
    }
    @Test
    public void testSelectOne(){
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.select("id","name","email");
        qw.eq("id",1);
        UserEntity userEntity = userMapper.selectOne(qw);
        Assert.assertNotNull(userEntity);
        System.out.println(userEntity);
    }
    @Test
    public void testSelectById(){
        UserEntity userEntity = userMapper.selectById(2L);
        Assert.assertNotNull(userEntity);
        System.out.println(userEntity);
    }
    @Test
    public void testSelectByPage(){
        IPage<UserEntity> userList = userMapper.selectPage(new Page<>(1,3),null);
        Assert.assertEquals(5,userList.getTotal());
        Assert.assertEquals(3,userList.getSize());
        userList.getRecords().forEach(System.out::println);
    }
}
