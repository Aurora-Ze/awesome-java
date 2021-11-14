package com.ze.aurora.service;

import com.ze.aurora.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @author Aurora
 * @date 2021/11/8 19:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void selectPage() {
        List<User> userList = userService.selectPage(2, 5);
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testOptimisticLock() {
        User user = userService.selectUserById(5);
//        user.setVersion(user.getVersion() - 1); 版本号变化，则更新失败
        userService.update(user);
    }

    @Test
    public void testLogicDelete() {
        userService.deleteById(10);
    }

    @Test
    public void testWrapper() {
        List<User> userList = userService.selectUserByCondition();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
