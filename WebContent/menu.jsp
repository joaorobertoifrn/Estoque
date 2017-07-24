<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><c:out value="${sessionScope.usuarioLogado}"/></p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Pesquisar...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">Menu</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="treeview">
                <a href="#"><i class="fa fa-edit"></i> <span>Cadastros</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="clienteControle?acao=listar"><i class="fa fa-circle-o"></i>Clientes</a></li>
                    <li><a href="categoriaControle?acao=listar"><i class="fa fa-circle-o"></i>Categoria</a></li>
                    <li><a href="produtoControle?acao=listar"><i class="fa fa-circle-o"></i>Produtos</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>Serviços</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-th"></i> <span>Pedidos</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i>Emitir Pedido</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>Consultar Pedido</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>Relatórios</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="clienteControle?acao=listar"><i class="fa fa-circle-o"></i>Clientes</a></li>
                    <li><a href="categoriaControle?acao=listar"><i class="fa fa-circle-o"></i>Categoria</a></li>
                    <li><a href="produtoControle?acao=listar"><i class="fa fa-circle-o"></i>Produtos</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>Pedidos</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>Financeiro</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>Configurações</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i>Empresa</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i>Usuário</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>