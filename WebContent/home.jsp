<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Balão Superior -->
<div class="row">
    <div class="col-lg-3 col-xs-6">
        <!-- small box -->
        <div class="small-box bg-aqua">
            <div class="inner">
                <%String qtdClientes = (String) request.getAttribute("qtdClientes");%>
                <h3><% out.print(qtdClientes); %></h3>

                <p>Clientes</p>
            </div>
            <div class="icon">
                <i class="fa fa-shopping-cart"></i>
            </div>
            <a href="clienteControle?acao=listar" class="small-box-footer">
                Detalhes <i class="fa fa-arrow-circle-right"></i>
            </a>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-3 col-xs-6">
        <!-- small box -->
        <div class="small-box bg-green">
            <div class="inner">
                <%String qtdProdutos = (String) request.getAttribute("qtdProdutos");%>
                <h3><% out.print(qtdProdutos); %></h3>

                <p>Produtos</p>
            </div>
            <div class="icon">
                <i class="ion ion-stats-bars"></i>
            </div>
            <a href="produtoControle?acao=listar" class="small-box-footer">
                Detalhes <i class="fa fa-arrow-circle-right"></i>
            </a>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-3 col-xs-6">
        <!-- small box -->
        <div class="small-box bg-yellow">
        <%String qtdCategorias = (String) request.getAttribute("qtdCategorias");%>
            <div class="inner">
                <h3><% out.print(qtdCategorias); %></h3>

                <p>Categoria</p>
            </div>
            <div class="icon">
                <i class="ion ion-person-add"></i>
            </div>
            <a href="categoriaControle?acao=listar" class="small-box-footer">
                Detalhes <i class="fa fa-arrow-circle-right"></i>
            </a>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-3 col-xs-6">
        <!-- small box -->
        <div class="small-box bg-red">
            <div class="inner">
                <h3>000</h3>

                <p>Pedido</p>
            </div>
            <div class="icon">
                <i class="ion ion-pie-graph"></i>
            </div>
            <a href="#" class="small-box-footer">
                Detalhes <i class="fa fa-arrow-circle-right"></i>
            </a>
        </div>
    </div>
    <!-- ./col -->
</div>
<!-- /.row -->
<!-- Cabeçalho da Pagina -->
<section class="content-header container-fluid">
    <h1>
        
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> home</a></li>
        <li class="active">Principal</li>
    </ol>
</section>

<!-- Conteudo da Página -->
<section class="content ">
    <div class="row">
            Conteudo da Pagina ............
        </div>
    <!-- /.row -->

</section>
<!-- /.Conteudo da Página -->