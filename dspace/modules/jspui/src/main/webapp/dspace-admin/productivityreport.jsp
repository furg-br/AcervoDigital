<%--

    The contents of this file are subject to the license and copyright
    detailed in the LICENSE and NOTICE files at the root of the source
    tree and available online at

    http://www.dspace.org/license/

--%>
<%--
  - Form to upload a csv metadata file
--%>

<%@ page import="org.dspace.content.vo.ProductivityReportVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.dspace.org/dspace-tags.tld" prefix="dspace"%>

<%
	String message =  (String) request.getAttribute("errorMessage");
	List<ProductivityReportVO> productivityReport = (List<ProductivityReportVO>) request.getAttribute("productivityData");

%>


<link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/jquery-ui-1.10.3.custom/redmond/jquery-ui-1.10.3.custom.css" type="text/css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/static/js/date-picker-reader.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/js/jquery/datepicker-pt-BR.js"></script>


<dspace:layout style="submission"
	titlekey="jsp.dspace-admin.reportproductivity.header" navbar="admin"
	locbar="link" parenttitlekey="jsp.administer"
	parentlink="/dspace-admin" nocache="true">
	
	<h1>
		<fmt:message key="jsp.dspace-admin.reportproductivity.header" />
	</h1>


	<%
		if (message != null){
		%>
			<div class="alert alert-warning">
				<fmt:message key="<%= message %>"></fmt:message>
			</div>
		<%  
    	}
	%>
	
	<form method="post" action="<%= request.getContextPath() + "/dspace-admin/productivityreport" %>">
	
		<br/>		
		
	<!-- 
		Data inicial
	 -->
	
		<div class="row">
	
			<label for="init-date" class="col-md-2">
				<fmt:message key="jsp.dspace-admin.reportproductivity.form.startdate"></fmt:message>
			</label>
			
			<div class="col-md-6">
				<input type="text" class="form-control date-picker-pt_BR"  size="20" id="init-date" name="init-date" value="<%= request.getParameter("init-date") != null ? request.getParameter("init-date") : "" %>"/> 
			</div>
		
		</div>
	
		
		<br/>
	
		<!-- 
			Data final
		 -->
		 <div class="row">
			<label for="end-date" class="col-md-2">
				<fmt:message key="jsp.dspace-admin.reportproductivity.form.enddate"></fmt:message>
			</label>
			 <div class="col-md-6">
				<input type="text" class="form-control date-picker-pt_BR"  size="20" id="end-date" name="end-date" value="<%= request.getParameter("end-date") != null ? request.getParameter("end-date") : ""  %>"/> 
			 </div>
		 </div>
		
		<br/>
	
		<!-- 
			Nome do membro
		 -->
		<div class="row">
			<label for="member-name" class="col-md-2">
				<fmt:message key="jsp.dspace-admin.reportproductivity.form.membername"></fmt:message>
			</label>
			 
			 <div class="col-md-6">
				<input type="text" class="form-control "  size="50" id="member-name" name="member-name" value="<%= request.getParameter("member-name") != null ? request.getParameter("member-name") : ""  %>"/> 
			 </div>
		</div>
		
		<br/>
				
        <input class="btn btn-default" type="submit" name="submit-report" value="<fmt:message key="jsp.dspace-admin.reportproductivity.form.button.doreport" />" />
	
	</form>
	
	<br/>
	<br/>
	
	<!-- 
		Resultados
	 -->
	 <% if(productivityReport != null && !productivityReport.isEmpty()) { %>
		
		<table style="width: 100%" class="table">
			<tr>
				<th><fmt:message key="jsp.dspace-admin.productivityreport.table.name" /> </th>
				<th><fmt:message key="jsp.dspace-admin.productivityreport.table.yearmonth" /> </th>
				<th><fmt:message key="jsp.dspace-admin.productivityreport.table.totalsubmitted" /> </th>
				<th><fmt:message key="jsp.dspace-admin.productivityreport.table.totalapproved" /> </th>
				<th><fmt:message key="jsp.dspace-admin.productivityreport.table.totalremoved" /> </th>
				<th><fmt:message key="jsp.dspace-admin.productivityreport.table.totalrejected" /> </th>
			</tr>
			
			<%
				
				long totalSubmitted = 0;
				long totalApproved = 0;
				long totalRemoved = 0;
				long totalRejected = 0;
			
			%>
			
			<% for(ProductivityReportVO productivityReportVO : productivityReport) { %>
			
				<tr>
				
					<%
						totalSubmitted += productivityReportVO.getTotalSubmitted();
						totalApproved += productivityReportVO.getTotalApproved();
						totalRemoved += productivityReportVO.getTotalRemoved();
						totalRejected += productivityReportVO.getTotalRejected();
					%>
				
					<td><%= productivityReportVO.getName() %></td>
					<td><%= productivityReportVO.getYearAndMonth() %></td>
					<td><%= productivityReportVO.getTotalSubmitted() %></td>
					<td><%= productivityReportVO.getTotalApproved() %></td>
					<td><%= productivityReportVO.getTotalRemoved() %></td>
					<td><%= productivityReportVO.getTotalRejected() %></td>
				
				</tr>
			
			<% } %>
			
			<!-- Totais -->
			<tr>
				<td colspan="2" class="bold">Total</td>
				<td class="bold"><%= totalSubmitted %></td>
				<td class="bold"><%= totalApproved %></td>
				<td class="bold"><%= totalRemoved %></td>
				<td class="bold"><%= totalRejected %></td>
			</tr>
		</table>
	 
	 <% } %>
	
	
	

</dspace:layout>