<%--

    The contents of this file are subject to the license and copyright
    detailed in the LICENSE and NOTICE files at the root of the source
    tree and available online at

    http://www.dspace.org/license/

--%>
<%--
  - Footer for home page
  --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.dspace.app.webui.util.UIUtil"%>

<%
    String sidebar = (String) request.getAttribute("dspace.layout.sidebar");
%>

<%-- Right-hand side bar if appropriate --%>
<%
    if (sidebar != null)
    {
%>
</div>
<div class="col-md-3">
	<%= sidebar %>
</div>
</div>
<%
    }
%>
</div>
</main>
<%-- Page footer --%>
<footer class="navbar navbar-bottom">
	<div id="ds-footer">
		
		<div id="footer-logos">
			<a target="_blank" href="http://creativecommons.org/">
				<img src="<%= request.getContextPath() %>/image/creative_commons_logo.jpg">
			</a> 
			<a target="_blank" href="http://www.ibict.br/"> 
				<img src="<%= request.getContextPath() %>/image/ibict_logo.jpg" class="padding"/>
			</a> 
			
			<a target="_blank" href="http://furg.br"> 
				<img src="<%= request.getContextPath() %>/image/logo-furg-31x.png" class="padding"/>
			</a>
		</div>
		
		<div id="ds-footer-links">
			<a href="<%= request.getContextPath() %>/contact">Entre em contato</a> | 
			<a href="<%= request.getContextPath() %>/feedback">Deixe sua opinião</a> | 
			<a href="<%= request.getContextPath() %>/static/pages/ri/equipe.jsp">Equipe</a> | 
			<a href="<%= request.getContextPath() %>/static/pages/ri/documentos.jsp">Documentos</a> | 
			<a href="<%= request.getContextPath() %>/static/pages/ri/politica.jsp">Política do RI</a>
		</div>
	</div>
</footer>
</body>
</html>