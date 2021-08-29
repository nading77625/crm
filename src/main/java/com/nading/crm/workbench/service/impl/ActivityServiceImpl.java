package com.nading.crm.workbench.service.impl;

import com.nading.crm.utils.SqlSessionUtil;
import com.nading.crm.workbench.dao.ActivityDao;
import com.nading.crm.workbench.domain.Activity;
import com.nading.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {


    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    @Override
    public boolean sava(Activity a) {
        boolean flag = true;
        int count = activityDao.save(a);
        if(count != 1){
            flag = false;
        }
        return flag;
    }
}
