package com.registration.registeruser.dao;

import com.registration.registeruser.entity.UserAttempts;

public interface UserDetailsDao {

    void updateFailAttempts(String username);
    void resetFailAttempts(String username);
    UserAttempts getUserAttempts(String username);

}