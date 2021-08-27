package com.nading.crm.settings.dao;

import com.nading.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, String> map);
}
