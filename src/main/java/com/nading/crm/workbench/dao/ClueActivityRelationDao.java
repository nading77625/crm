package com.nading.crm.workbench.dao;

import com.nading.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {


    int unbund(String id);

    int bund(ClueActivityRelation car);

    List<ClueActivityRelation> getListByClueId(String clueId);

    int delete(ClueActivityRelation clueActivityRelation);
}
