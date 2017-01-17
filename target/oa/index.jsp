<%@page import="com.baofeng.utils.Constants"%>
<%
	if (!Constants.DEFAULT_ISRELEASE) {
		request.getRequestDispatcher("/login.do").forward(request, response);
	}
%>
