package example.user.service.impl;

import example.user.entity.User;
import example.user.entity.UserExample;
import example.user.mapper.UserMapper;
import example.user.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/10 0010.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getList() {
        UserExample userExample=new UserExample();
        return userMapper.selectByExample(userExample);
    }


    @Autowired
    UserMapper userMapper;
}
