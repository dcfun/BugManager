package com.dcfun.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.dcfun.dao.BugListDao;

public class BusinessService {

	BugListDao dao = new BugListDao();
	
	public List<Map<String, List<String>>> getList(){

		return dao.getInfoFromLog();
	}
	
	public List<Map<String, String>> getMap(){
		return dao.getErrorDetailList();
	}
}
