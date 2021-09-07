package com.nading.crm.workbench.service.impl;

import com.nading.crm.settings.dao.UserDao;
import com.nading.crm.settings.domain.DicValue;
import com.nading.crm.settings.domain.User;
import com.nading.crm.utils.SqlSessionUtil;
import com.nading.crm.workbench.dao.ClueDao;
import com.nading.crm.workbench.domain.Clue;
import com.nading.crm.workbench.service.ClueService;

import java.util.List;
import java.util.Map;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);

    @Override
    public boolean save(Clue c) {
        boolean flag = true;
        int count = clueDao.save(c);
        if(count != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public Clue detail(String id) {
        Clue c = clueDao.detail(id);

        return c;
    }
}
