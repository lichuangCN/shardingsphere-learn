package site.muzhi.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.muzhi.common.entity.User;
import site.muzhi.common.mapper.UserMapper;

import java.util.List;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void save(User user) {
        userMapper.insert(user);
    }

    public List<User> list() {
        return userMapper.list();
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public List<User> listByName(String name) {
        return userMapper.listByName(name);
    }
}
