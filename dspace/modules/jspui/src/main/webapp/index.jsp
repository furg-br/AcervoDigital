<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.dspace.core.ConfigurationManager" %>

<html>
<head>

	<title>FURG - Repositórios unificados</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 	<link href="<%= request.getContextPath() %>/static/css/bootstrap-combined.min.css"	rel="stylesheet">
	
	<!--[if lt IE 9]>
     	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

	<link href="<%= request.getContextPath() %>/static/css/bootply.css" type="text/css"	rel="stylesheet"/>
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet" />
	<link href="<%= request.getContextPath() %>/static/css/portal.css" type="text/css"	rel="stylesheet"/>
	
	<link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/image/favicon.png" />

</head>

<body>

	<div class="container">
		<br> <br> <br>
		<div class="row-fluid">
			<h1 class="pull-center">
				<img alt="Logo FURG" src="<%= request.getContextPath() %>/image/logo-furg-branco.png">
				<strong>Acervo Digital da FURG</strong>
			</h1>
			<p class="lead pull-center">Portal unificado dos repositórios da Universidade Federal do Rio Grande</p>
		</div>
		<br> <br> <br> <br>
		<div class="row-fluid">

			<div class="span3 offsetHalf block">
				<div class="pull-center">
					<h1>RI</h1>
					Aqui breve texto descritivo sobre o RI FURG<br>
					<h1>
						<i class="icon-beaker icon-4x"></i>
					</h1>
					<a class="btn btn-primary btn-flat botaoRI" href="<%= request.getContextPath() %>/handle/<%= ConfigurationManager.getProperty("handle.ri")  %>">
						Acessar<i class="pull-right icon-chevron-right icon-large"></i>
					</a>
				</div>
			</div>
			<div class="span3 block">
				<div class="pull-center">
					<h1>SABER.com</h1>
					Aqui breve texto descritivo sobre o SABER.com<br>
					<h1>
						<i class="icon-cloud icon-4x"></i>
					</h1>
					<a  href="<%= request.getContextPath() %>/handle/<%= ConfigurationManager.getProperty("handle.sabercom")  %>" class="btn btn-primary btn-flat">
						Acessar<i class="pull-right icon-chevron-right icon-large"></i>
					</a>
				</div>
			</div>
			<div class="span3 block">
				<div class="pull-center">
					<h1>BDTCCs</h1>
					Aqui breve texto descritivo sobre o BDTCCs<br>
					<h1>
						<i class="icon-book icon-4x"></i>
					</h1>
					<a class="btn btn-primary btn-flat botaoBDTCCs"  href="<%= request.getContextPath() %>/handle/<%= ConfigurationManager.getProperty("handle.bdtccs")  %>">Acessar
						<i class="pull-right icon-chevron-right icon-large"></i>	
					</a>
				</div>
			</div>

		</div>
		<br> <br> <br>
		
	</div>
	<!---/cont---->
	<br>
	<br>
	<div class="container">
		<!-- Slider -->

		<br> <br>
		<div class="row-fluid">
			<div class="pull-right">
				<a href="https://www.facebook.com/furgrs" target="_blank">
					<i class="icon-facebook icon-3x"></i> 
				</a> &nbsp; 
				<a href="https://twitter.com/BibliotecaFURG" target="_blank">
					<i class="icon-twitter icon-3x"></i>
				</a>
			</div>

		</div>
		<br> <br>
	</div>

</body>
</html>