<%@page import="com.baofeng.commons.entity.RoleDetailsAtts.Enabled"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%
List<RoleDetailsAtts> attsList = (List<RoleDetailsAtts>)request.getAttribute("attsList");
if(attsList != null && attsList.size() > 0){
	for(RoleDetailsAtts atts : attsList){
		try{
			Integer.parseInt(atts.getOpkey(),0);
		}catch(Exception e){
			if(atts.getViews() == Enabled.YES){
				%><input class="tabBtn <%=atts.getOpkey() %>" type="button" value="<%=atts.getOpName() %>"/><%
			}
		}
	}
}
%>
