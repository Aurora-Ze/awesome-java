package com.ze.aurora.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ze.aurora.entity.User;
import com.ze.aurora.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aurora
 * @date 2021/11/8 18:15
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectUsers() {
        List<User> userList = userMapper.selectList(null);

        for (User user : userList) {
            System.out.println(user.toString());
        }

        return userList;
    }

    public User selectUserById(long id) {
        return userMapper.selectById(id);
    }

    public List<User> batchSelectUsersById(List<Long> ids) {
        List<User> userList = userMapper.selectBatchIds(ids);
        return userList;
    }

    public List<User> selectPage(long pageSize, long offset) {
        Page<User> page = new Page<>(pageSize, offset);
        // 无条件分页查询
        userMapper.selectPage(page, null);

        List<User> userList = page.getRecords();
        return userList;
    }

    public void update(User user) {
        userMapper.updateById(user);
    }

    public void deleteById(long id) {
        userMapper.deleteById(id);
    }

    public void batchDeleteByIds(List<Long> ids) {
        userMapper.deleteBatchIds(ids);
    }

    public List<User> selectUserByCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.lt("age", 20)
                .or(i -> i.gt("age", 60).eq("deleted", 0));

//        userMapper.selectList(new QueryWrapper<>())

        List<User> userList = userMapper.selectList(wrapper);
        return userList;
    }
}
