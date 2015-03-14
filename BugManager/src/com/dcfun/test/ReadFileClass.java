package com.dcfun.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ReadFileClass {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(getInfoFromLog("E:\\crash_infos\\CrashLog-20150223_20-18-02.log"));
	}

	private static List<Map<String,String>> getInfoFromLog(String fileName){

		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		String tempRead = null;
		Map<String,String> map = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			map=new HashMap<String,String>();
			
			while((tempRead=br.readLine()) != null){
                
				String[] array = tempRead.split("=", 2);
				
				if(array.length==2){
                    map.put(array[0], array[1]);
                }
				if(array.length==1){
					map.put("Error", array[0]);
				}
			}
			list.add(map);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
