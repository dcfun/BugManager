<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>BugList</title>
  </head>
  <body style="text-align: center;">
  
  		<table align="center" border="1">
  			<tr>
  				<td align="center" width="30%">Error</td>  
				<td align="center" width="70%">detail</td>
  			</tr>
  		
 			<c:forEach var="list" items="${list }">			<!-- 此：List<Map<String, List<String>>> -->
 			
 				<c:forEach var="map" items="${list }">
	 				<tr>										<!-- Map<String, List<String>> -->
	 					<td width="30%">	${map.key}</td>  
	 					<td width="75%"><a href="${pageContext.request.contextPath }/servlet/ErrorDetailServlet?phone=${map.value }">	${map.value }</a></td>
	 				</tr>
 				</c:forEach>
 				
 			</c:forEach>
 			    
  		</table>
  </body>
</html>
