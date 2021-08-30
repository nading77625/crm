package com.nading.crm.workbench.service.impl;

import com.nading.crm.VO.PaginationVO;
import com.nading.crm.utils.SqlSessionUtil;
import com.nading.crm.workbench.dao.ActivityDao;
import com.nading.crm.workbench.domain.Activity;
import com.nading.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

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

    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        List<Activity> dataList = activityDao.getActivityListByCondition(map);
        int total = activityDao.getTotalCondition(map);
        PaginationVO<Activity> vo = new PaginationVO();
        vo.setDataList(dataList);
        vo.setTotal(total);


        return vo;
    }
}
