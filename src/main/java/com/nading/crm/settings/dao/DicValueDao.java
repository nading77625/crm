package com.nading.crm.settings.dao;

import com.nading.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getValue(String typeCode);
}
