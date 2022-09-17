package org.coding.mapper;

import org.coding.model.keywordVO;

public interface keywordMapper {
	
	//최근 검색어 DB 저장 
	public int keyword(keywordVO keyword);
}
