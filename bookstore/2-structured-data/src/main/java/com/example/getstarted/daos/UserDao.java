package com.example.getstarted.daos;

import com.example.getstarted.objects.User;

import java.util.List;

public interface UserDao {
    Long createUser(User user);

    boolean login(User user);

    User readUser(Long userId);

    void addBookToList(User user);

    List<User> listUsers();

    void deleteUser(Long userId);
}
