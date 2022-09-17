package org.coding.service;

import org.coding.mapper.keywordMapper;
import org.coding.model.keywordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class keywordServicempl implements keywordService {

	@Autowired
	keywordMapper kwm;
	
	public int keyword(keywordVO keyword) {
		return kwm.keyword(keyword);
	}
}
