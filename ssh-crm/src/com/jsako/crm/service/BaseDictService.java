package com.jsako.crm.service;

import java.util.List;

import com.jsako.crm.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
