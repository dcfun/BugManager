package com.dcfun.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcfun.service.BusinessService;

public class ErrorDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessService service = new BusinessService();
		List list = service.getList();
		Map map = new HashMap<String, String>();
		String[] phoneValueTemp = new String[100];
		
		String phoneValue = request.getParameter("phone");
		//���濪ʼ����list��֪�� �ҵ�valueֵ����phoneValueֵ��ȵ��Ǹ�map
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map maptemp = (Map) iterator.next();
			Set entrySet = maptemp.entrySet();			//�õ�maptemp��key/value����
			
			Iterator iterator2 = entrySet.iterator();	
			Map.Entry me = (Entry) iterator2.next();
			List<String> listtemp = (List<String>) me.getValue();		//�õ�maptemp��phoneValueֵ->��һ��list
			for (Iterator iterator3 = listtemp.iterator(); iterator3.hasNext();) {
				String str = (String) iterator3.next();
				phoneValueTemp = phoneValue.split(",");

				for (int i = 0; i<phoneValueTemp.length; i++) {
					if (str.equalsIgnoreCase(phoneValueTemp[i])) {
						map = maptemp;
					}	
				}
			}
		}
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/jsp/ErrorDetail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
