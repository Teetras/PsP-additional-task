package com.example.demo1.dao;

import com.example.demo1.entity.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> showUser();
    User findUserById(int id);
    User findUserByLogin(String login);
}