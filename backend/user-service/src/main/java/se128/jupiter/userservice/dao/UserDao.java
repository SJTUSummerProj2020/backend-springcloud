package se128.jupiter.userservice.dao;

import entity.Order;
import entity.User;

import java.util.List;

public interface UserDao {
    User getUserByUserId(Integer userId);

    User getUserByUsernameAndPassword(String username, String password);

    User addUser(User user);

    List<User> getAllUsers();

    User changeUserStatusByUserId(Integer userId);

    User saveUser(User user);

    User editUser(User user);

    User getUserByUsername(String username);
}
