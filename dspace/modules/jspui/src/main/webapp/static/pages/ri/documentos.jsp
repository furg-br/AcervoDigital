<%--

    The contents of this file are subject to the license and copyright
    detailed in the LICENSE and NOTICE files at the root of the source
    tree and available online at

    http://www.dspace.org/license/

--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.dspace.org/dspace-tags.tld" prefix="dspace"%>


<dspace:layout title="Documentos do RI">
	<div id="pageContents">
		<h2>Documentos</h2>

		<div id="documentos">


			<div id="div_container_equipe" class="ds-static-div primary">
				<div id="div_container_documentos">
					<div class="ds-static-div">
						<a
							href="<%= request.getContextPath() %>/static/files/ri/Tutorial_Basico_para_depositos_no_RI-FURG.pdf" target="_blank">
							Tutorial de submissão </a>
					</div>

					<a
						href="http://projecto.rcaap.pt/formar/mod3/contents/vdeo_demonstrativo_de_autoarquivo.html"
						target="_blank"> Vídeo da Universidade do Minho sobre
						autoarquivamento </a>

				</div>
			</div>

		</div>

	</div>
</dspace:layout>