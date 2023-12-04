package com.example.demo1.service.serviceImpl;


import com.example.demo1.dao.UserDao;
import com.example.demo1.dao.daoImpl.UserDaoImpl;
import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateError;

import java.util.List;

@NoArgsConstructor
public class UserServiceImpl implements UserService {// реализация функций интерфейса
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean addUser(User user) {
        boolean isAdded = false;
        try {
            if (userDao.addUser(user))
                isAdded = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateUser(User user) {
        boolean isUpdated = false;
        try {
            if (userDao.updateUser(user))
                isUpdated = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean isDeleted = false;
        try {
            if (userDao.deleteUser(id))
                isDeleted = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

    @Override
    public List<User> showUser() {
        List<User> user = null;
        try {
            user = userDao.showUser();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findUserById(int id) {
        User user = null;
        try {
            user = userDao.findUserById(id);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.findUserByLogin(login);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
