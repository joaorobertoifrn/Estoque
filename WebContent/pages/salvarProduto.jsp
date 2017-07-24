<%@page import="java.util.List"%>
<%@page import="modelo.Produto"%>
<%@page import="modelo.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<title>Ordem de Serviço | Produtos</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<style>
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}
</style>
<script type="text/javascript">

	function confirmaExclusao(idProduto,idCategoria) {
		if (window.confirm("Deseja realmente Excluir o registro ?")) {
			location.href = "produtoControle?acao=excluirCategoria&idProduto="+idProduto+"&idCategoria="+idCategoria;
		}
	}

	function incluirCategoria(id) {
		   var e = document.getElementById("cbCategoria");	
		return location.href = "produtoControle?acao=incluirProdutoCategoria&idCategoria="+e.options[e.selectedIndex].value+"&idProduto="+id;			
	}
</script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../menuSuperior.jsp" />

		<jsp:include page="../menu.jsp" />
		<div class="content-wrapper">
			<section class="content container-fluid">
				<!-- Pagina Dinamica -->

				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
						Produtos <small></small>
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i> Cadastro</a></li>
						<li class="active">Produtos</li>
					</ol>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="row">
						<div class="col-lg-6">
							<div class="box box-warning">
								<div class="box-header with-border">
									<h3 class="box-title">Dados do Produto</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<form action="produtoControle" method="post">
										<%
											Produto pro = (Produto) request.getAttribute("produto");
										%>

										<div class="form-group">
											<div class="row">
												<div class="col-lg-2">
													<label>Id</label>
													<!-- text input : Id Produto -->
													<input type="text" class="form-control" placeholder=""
														name="id" value="<%=pro.getId()%>">
												</div>
												<div class="col-lg-10">
													<label>Nome</label>
													<!-- text input : Nome Produto -->
													<input type="text" class="form-control"
														placeholder="Nome ..." name="nome"
														value="<%=pro.getNome()%>">
												</div>
											</div>
										</div>

										<div class="form-group">
											<label>Descrição</label>
											<!-- text input : Descrição Produto -->
											<input type="text" class="form-control"
												placeholder="Descrição ..." name="descricao"
												value="<%=pro.getDescricao()%>">
										</div>

										<div class="form-group">
											<div class="row">
												<div class="col-lg-6">
													<label>Estoque</label>
													<!-- text input : Estoque Produto -->
													<input type="number" class="form-control"
														placeholder="Estoque" name="estoque"
														value="<%=pro.getEstoque()%>">
												</div>
												<div class="col-lg-6">
													<label>Preço</label>
													<!-- text input : Valor Produto -->
													<input type="text" class="form-control" placeholder="Valor"
														name="preco" value="<%=pro.getPreco()%>">
												</div>
											</div>
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-danger pull-left">Cancelar</button>
											<input class="btn btn-primary" type="submit" value="Salvar" />
										</div>
									</form>
									<!-- /.box-body -->
								</div>


							</div>
							<!-- /.box -->
						</div>

						<div class="col-lg-6">
							<div class="box box-default">
								<div class="box-header with-border">
									<h3 class="box-title">Dados do Produto</h3>
								</div>
								<div class="box-body">
									<div class="box-header with-border">
										<h3 class="box-title"></h3>
										<label>Selecione a Categoria</label>
										<div class="row">
											<div class="col-lg-9">
												<div class="form-group">
													<select id="cbCategoria" class="form-control">
													<c:forEach var="cat" items="${listarCategoria}">
														<option name="idCategoria" value="${cat.id}">${cat.nome}</option>
													</c:forEach>
													</select>
												</div>
											</div>
											<div class="col-lg-2">
												<a class="btn btn-primary btn-sm"
													href="javascript:incluirCategoria(<%=pro.getId()%>)"> <span
													class="glyphicon glyphicon-plus"></span> Incluir
												</a>
											</div>
										</div>
										<div class="box-body">
											<!-- Main content -->
											<div class="box">
												<div class="box-body">
													<table class="table table-hover">
														<thead>
															<tr>
																<th>Id</th>
																<th>Nome</th>
																<th>Ação</th>
															</tr>
														</thead>
														<c:forEach var="proCat" items="${listarProdutoCategoria}">
														<tbody>
															<tr>
																<td>${proCat.id}</td>
																<td>${proCat.nome}</td>
																<td><a class="btn btn-danger btn-sm" href="javascript:confirmaExclusao(<%=pro.getId()%>,${proCat.id})"> <span
																		class="glyphicon glyphicon-trash"></span> Excluir
																</a></td>
															</tr>
														</tbody>
														</c:forEach>
													</table>
												</div>
												<!-- /.box-body -->
											</div>
											<!-- /.box -->
										</div>
									</div>
								</div>
							</div>
						</div>
				</section>
			</section>
		</div>

		<!-- Pagina Dinamica -->
		<jsp:include page="../rodape.jsp" />
	</div>

	<!-- jQuery 3.1.1 -->
	<script src="plugins/jQuery/jquery-3.1.1.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- DataTables -->
	<script src="plugins/datatables/jquery.dataTables.js"></script>
	<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>
	<!-- page script -->
	<script>
		$(function() {
			$("#tb_padrao").DataTable();
		}
		);
	</script>
</body>
</html>