package com.nading.crm.workbench.service.impl;

import com.nading.crm.settings.domain.DicValue;
import com.nading.crm.utils.SqlSessionUtil;
import com.nading.crm.workbench.dao.ClueDao;
import com.nading.crm.workbench.service.ClueService;

import java.util.List;
import java.util.Map;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);


}
