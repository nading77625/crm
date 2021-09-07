package com.nading.crm.workbench.service;

import com.nading.crm.settings.domain.DicValue;
import com.nading.crm.settings.domain.User;
import com.nading.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {

    boolean save(Clue c);

    Clue detail(String id);
}
