package com.nading.crm.workbench.dao;

import java.util.List;

public interface ActivityRemark {
    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);

    List<com.nading.crm.workbench.domain.ActivityRemark> getRemarkListByAid(String id);

    int deleteRemark(String id);

    int saveRemark(com.nading.crm.workbench.domain.ActivityRemark ar);
}
