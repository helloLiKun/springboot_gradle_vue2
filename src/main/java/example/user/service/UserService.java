package example.user.service;

import example.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/10 0010.
 */
public interface UserService {
    List<User> getList();
}
