package com.example.dao;

import com.example.entity.User;
import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> showUser();
    User findUserById(int id);
    User findUserByLogin(String login);
}