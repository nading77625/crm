package com.nading.crm.workbench.service.impl;

import com.nading.crm.VO.PaginationVO;
import com.nading.crm.settings.dao.UserDao;
import com.nading.crm.settings.domain.User;
import com.nading.crm.utils.SqlSessionUtil;
import com.nading.crm.workbench.dao.ActivityDao;
import com.nading.crm.workbench.dao.ActivityRemark;
import com.nading.crm.workbench.domain.Activity;
import com.nading.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {


    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemark activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemark.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    @Override
    public boolean sava(Activity a) {
        boolean flag = true;
        System.out.println("----------------------" + a.getName());
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

    @Override
    public boolean delete(String[] ids) {
        boolean flag = true;
        //查询出需要删除的备注数量
        int count1 = activityRemarkDao.getCountByAids(ids);
        //删除备注，返回受影响条数
        int count2 = activityRemarkDao.deleteByAids(ids);
        //删除市场活动
        int count3 = activityDao.delete(ids);

        if(count3 != ids.length){
            System.out.println("删除失败");
            flag = false;
        }



        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        //取list
        List<User> list= userDao.getUserList();
        //取市场活动对象
        Activity activity = activityDao.getUserListAndActivity(id);
        //装进map
        Map<String,Object> map = new HashMap();
        map.put("uList",list);
        map.put("a",activity);
        return map;
    }

    @Override
    public boolean update(Activity a) {
        boolean flag = true;
        System.out.println("----------------------" + a.getName());
        int count = activityDao.update(a);
        if(count != 1){
            flag = false;
        }

        return flag;
    }

    @Override
    public Activity detail(String id) {
        Activity a = activityDao.detail(id);
        return a;
    }
}
