<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
    <title>My JSP 'addcustomer.jsp' starting page</title>
  </head>
  <body>
  
  <table align="center" border="1">
  		
 				<c:forEach var="map" items="${ map}">
						<td>
							${map.key }
							${map.value }
						</td>
 				</c:forEach>
  		</table>

  </body>
</html>
