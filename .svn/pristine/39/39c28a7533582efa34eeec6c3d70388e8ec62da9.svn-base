<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.baofeng.commons.entity.RoleDetailsAtts"%>
<%
	Integer platId = Integer.valueOf(0);
	if(request.getAttribute("platId") != null && request.getAttribute("platId").toString().trim().length()>0){
		platId = Integer.valueOf(request.getAttribute("platId").toString());
	}
	List<RoleDetailsAtts> attsList = (List<RoleDetailsAtts>)request.getAttribute("platList");
	if(attsList != null && attsList.size() > 0){
		int ftnIndex = Integer.MAX_VALUE;
		for(RoleDetailsAtts plat : attsList){
			if(platId == Integer.valueOf(0)){
				if(ftnIndex == Integer.MAX_VALUE){
					ftnIndex--;
					%><span class="regs spanchange" value="<%=plat.getOpkey() %>"><%=plat.getOpName() %></span>
<%
				}else{
					%><span class="regs" value="<%=plat.getOpkey() %>"><%=plat.getOpName() %></span>
<%
				}
			}else{
				if(plat.getOpkey().equals(platId.toString())){
					%><span class="regs spanchange" value="<%=plat.getOpkey() %>"><%=plat.getOpName() %></span>
<%
				}else{
					%><span class="regs" value="<%=plat.getOpkey() %>"><%=plat.getOpName() %></span>
<%
				}
			}
			
			
		}
		
		
	}
 %>