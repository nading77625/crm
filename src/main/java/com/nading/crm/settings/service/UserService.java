package com.nading.crm.settings.service;

import com.nading.crm.exception.LoginException;
import com.nading.crm.settings.domain.User;

public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
