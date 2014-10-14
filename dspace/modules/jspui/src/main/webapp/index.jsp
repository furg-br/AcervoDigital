<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>

	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 	<link href="<%= request.getContextPath() %>/static/css/bootstrap-combined.min.css"	rel="stylesheet">
	
	<!--[if lt IE 9]>
	          <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
	        <![endif]-->
<!-- 	<link rel="shortcut icon" href="/bootstrap/img/favicon.ico">
	<link rel="apple-touch-icon" href="/bootstrap/img/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/bootstrap/img/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/bootstrap/img/apple-touch-icon-114x114.png"> -->
	<link href="<%= request.getContextPath() %>/static/css/bootply.css" type="text/css"	rel="stylesheet"/>
	<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet" />
	
	<link href="<%= request.getContextPath() %>/static/css/portal.css" type="text/css"	rel="stylesheet"/>


<!-- CSS code from Bootply.com editor -->

<style type="text/css">

</style>
<style class="firebugResetStyles" type="text/css" charset="utf-8">/* See license.txt for terms of usage */
</style>


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
					<button class="btn btn-primary btn-flat botaoRI">
						Acessar<i class="pull-right icon-chevron-right icon-large"></i>
					</button>
				</div>
			</div>
			<div class="span3 block">
				<div class="pull-center">
					<h1>SABER.com</h1>
					Aqui breve texto descritivo sobre o SABER.com<br>
					<h1>
						<i class="icon-cloud icon-4x"></i>
					</h1>
					<button class="btn btn-primary btn-flat">
						Acessar<i class="pull-right icon-chevron-right icon-large"></i>
					</button>
				</div>
			</div>
			<div class="span3 block">
				<div class="pull-center">
					<h1>BDTCCs</h1>
					Aqui breve texto descritivo sobre o BDTCCs<br>
					<h1>
						<i class="icon-book icon-4x"></i>
					</h1>
					<button class="btn btn-primary btn-flat botaoBDTCCs">Acessar<i class="pull-right icon-chevron-right icon-large"></i>
					</button>
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
				<a href="http://www.bootply.com"><i
					class="icon-facebook icon-3x"></i> </a> &nbsp; <a
					href="http://twitter.com/bootply"><i
					class="icon-twitter icon-3x"></i></a>
			</div>

		</div>
		<br> <br>
	</div>


</body>
</html>