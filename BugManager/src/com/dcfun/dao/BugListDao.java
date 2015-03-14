package com.dcfun.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

public class BugListDao {
	
	private int[] errorCount = new int[20];
	private String[] errorString = new String[20];
	private List<Map<String,String>> ErrorDetailList = new ArrayList<Map<String,String>>();
	
	
	public List<Map<String, String>> getErrorDetailList() {
		return ErrorDetailList;
	}

	public void setErrorDetailList(List<Map<String, String>> errorDetailList) {
		ErrorDetailList = errorDetailList;
	}

	public List<Map<String, List<String>>> getInfoFromLog() {

		List<Map<String, List<String>>> list = new ArrayList<Map<String, List<String>>>();
		String tempRead = null;
		String strPath = "e://crash_infos";
		String errorDetail = "";
		boolean mark = true;

		File dir = new File(strPath);
		File[] files = dir.listFiles();
		String error = "";
		String detail = "";
		
		try {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getAbsolutePath();
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				/*List<String> list2 = new ArrayList<String>();
				Map<String, List<String>> map = new HashMap<String, List<String>>();*/

				while ((tempRead = br.readLine()) != null) {
					String[] array = tempRead.split("=", 2);
					if (array.length == 2 & array[0].equals("versionName")) {
						detail = detail + "version:" + array[1] + "/";
					}
					if (array.length == 2 & array[0].equals("FINGERPRINT")) {
						detail = detail + array[1] + "/";
					}
					if (array.length == 1) {
						if (mark) {
							error = array[0];
							mark = false;
						}
						errorDetail = errorDetail + array[0] + "<br/>";
					}
				}
				list = errorCnt(list, error, detail, errorDetail);
				mark = true;
				errorDetail = "";  
				detail = "";
				
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<Map<String, List<String>>> errorCnt(List<Map<String, List<String>>> list, String error, String detail, String errorDetail) {
		List<String> list2 = new ArrayList<String>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, String> ErrorDetailMap = new HashMap<String, String>();
		boolean cntMark = false;
		String Compdetail = "";
		
		if (errorString[0]==null) {
			errorString[0] = error;
			errorCount[0]++;
			detail = detail +"*******"+ errorCount[0] + "<br/>";
			
			list2.add(detail);
			map.put(error, list2);
			list.add(map);
			ErrorDetailMap.put("phone", detail);
			ErrorDetailMap.put("detail", errorDetail);
			setErrorDetailList(ErrorDetailList);
			return list;
		}
		
		// 先对比、没有则放、
		//有则++、
		int i;
		for (i = 0; errorString[i]!=null; i++) {
			if (error.equalsIgnoreCase(errorString[i])) {
			//有、则 迭代出那个map、 取出value值、在末尾加上detail、然后 放回去
				
				Compdetail = detail +"*****"+ errorCount[i] + "<br/>";
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					map =  (Map<String, List<String>>) iterator.next();			
					Set entrySet = map.entrySet();
					Iterator iterator2 = entrySet.iterator();
					Map.Entry me = (Entry) iterator2.next();
					String str = (String) me.getKey();
					if (str.equalsIgnoreCase(error)) {
						list2 = (List<String>) me.getValue();
						cntMark = true;
					}
				}
				for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
					String str = (String) iterator.next();
					if (str.equalsIgnoreCase(Compdetail)) {
						return list;
					}
				}
				errorCount[i]++;
				if (cntMark) {
					detail = detail +"*****"+ errorCount[i] + "<br/>";
				}
				
				list2.add(detail);
				map.put(error, list2);
				ErrorDetailMap.put("phone", detail);
				ErrorDetailMap.put("detail", errorDetail);
				setErrorDetailList(ErrorDetailList);
				return list;
			}
		}
		//没有则放、
		errorString[i] = error;
		errorCount[i]++;
		detail = detail +"******"+ errorCount[i] + "<br/>";
		
		list2.add(detail);
		map.put(error, list2);
		list.add(map);
		ErrorDetailMap.put("phone", detail);
		ErrorDetailMap.put("detail", errorDetail);
		setErrorDetailList(ErrorDetailList);
		return list;
	}

}
