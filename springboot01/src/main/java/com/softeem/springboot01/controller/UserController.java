package com.softeem.springboot01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.softeem.springboot01.entity.User;
import com.softeem.springboot01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(){
        //todo 自行实现登录逻辑
        return "2330210635罗琰，登录成功！";
    }

    @RequestMapping("/reg")
    public String reg(String username,String password){
        //构建查询条件包装器  where username=?
        QueryWrapper<User> query = Wrappers.query(User.class);
        query.eq("username",username);

        //根据用户名查询该用户是否存在  select * from user where username=?
        User user = userMapper.selectOne(query);
        if(Objects.nonNull(user)){
            return "对不起，该用户已存在";
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int i = userMapper.insert(user);

        return i > 0 ? "注册成功" : "注册失败";
    }

}
