package com.nading.crm.workbench.service;

import com.nading.crm.VO.PaginationVO;
import com.nading.crm.workbench.domain.Activity;
import com.nading.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    boolean sava(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    boolean update(Activity a);

    Activity detail(String id);


    List<ActivityRemark> getRemarkListByAid(String id);

    boolean deleteRemark(String id);

    boolean saveRemark(ActivityRemark ar);
}
