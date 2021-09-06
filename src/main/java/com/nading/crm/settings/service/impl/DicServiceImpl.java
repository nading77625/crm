package com.nading.crm.settings.service.impl;

import com.nading.crm.settings.dao.DicTypeDao;
import com.nading.crm.settings.dao.DicValueDao;
import com.nading.crm.settings.domain.DicType;
import com.nading.crm.settings.domain.DicValue;
import com.nading.crm.settings.service.DicService;
import com.nading.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> getAll() {
        List<DicType> list = dicTypeDao.selectAllType();
        Map<String, List<DicValue>> map = new HashMap<>();
        for(DicType dicType:list){
            List<DicValue> listValue =  dicValueDao.getValue(dicType.getCode());
            map.put(dicType.getCode(),listValue);
        }

        return map;
    }
}
