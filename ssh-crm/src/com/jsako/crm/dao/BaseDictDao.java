package com.jsako.crm.dao;

import java.util.List;

import com.jsako.crm.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict>{

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
