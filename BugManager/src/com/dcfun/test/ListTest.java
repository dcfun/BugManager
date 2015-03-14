package com.dcfun.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < 10; i++) {
			list.add("aa"+i);
		}

		/*for (String string : list) {
			System.out.println(list);
		}*/
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}
	
}
