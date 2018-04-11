package com.example.getstarted.daos;

import com.example.getstarted.objects.User;

public interface UserDao {
    Long createUser(User user);

    boolean login(User user);

    //void logout(User user);

    User readUser(Long userId);
}
