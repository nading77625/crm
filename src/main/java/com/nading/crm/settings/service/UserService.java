package com.nading.crm.settings.service;

import com.nading.crm.exception.LoginException;
import com.nading.crm.settings.domain.User;

import java.util.List;

public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
