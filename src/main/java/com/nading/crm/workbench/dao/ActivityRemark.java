package com.nading.crm.workbench.dao;

public interface ActivityRemark {
    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);
}
