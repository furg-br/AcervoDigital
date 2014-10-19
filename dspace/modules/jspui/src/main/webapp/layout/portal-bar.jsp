<%--

    The contents of this file are subject to the license and copyright
    detailed in the LICENSE and NOTICE files at the root of the source
    tree and available online at

    http://www.dspace.org/license/

--%>
<%--
  - Location bar component
  -
  - This component displays the "breadcrumb" style navigation aid at the top
  - of most screens.
  -
  - Uses request attributes set in org.dspace.app.webui.jsptag.Layout, and
  - hence must only be used as part of the execution of that tag.  Plus,
  - dspace.layout.locbar should be verified to be true before this is included.
  -
  -  dspace.layout.parenttitles - List of titles of parent pages
  -  dspace.layout.parentlinks  - List of URLs of parent pages, empty string
  -                               for non-links
  --%>

<%@ page contentType="text/html;charset=UTF-8" %>
  
<%@ page import="java.util.List" %>
<%@ page import="org.dspace.core.ConfigurationManager" %>


	<link href="<%= request.getContextPath() %>/static/css/bootply.css" type="text/css"	rel="stylesheet"/>
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet" />

<ol class="breadcrumb repositorios">
	<li>
		<h3><fmt:message key="portal.bar.header" /></h3>
			
	<a class="noDecoration" href="<%= request.getContextPath() %>/handle/<%= ConfigurationManager.getProperty("handle.ri")  %>">
		<i class="icon-beaker icon-4x"></i>
	</a>

	<a class="noDecoration" href="<%= request.getContextPath() %>/handle/<%= ConfigurationManager.getProperty("handle.sabercom")  %>">
		<i class="icon-cloud icon-4x"></i>
	</a>
		
	<a class="noDecoration" href="<%= request.getContextPath() %>/handle/<%= ConfigurationManager.getProperty("handle.bdtccs")  %>">
		<i class="icon-book icon-4x"></i>
	</a>
	</li>
</ol>