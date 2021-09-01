package com.nading.crm.workbench.dao;

import com.nading.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int save(Activity a);

    int getTotalCondition(Map<String, Object> map);

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    int delete(String[] ids);

    Activity getUserListAndActivity(String id);

    int update(Activity a);

    Activity detail(String id);
}
