<%@page import="java.util.List"%>
<%@page import="modelo.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Controle de Estoque | Categorias</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
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
            function confirmaExclusao(id) {
                if (window.confirm("Deseja realmente Excluir o registro ?")) {
                    location.href = "categoriaControle?acao=excluir&id=" + id;
                }
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
                            Clientes
                            <small></small>
                        </h1>
                        <ol class="breadcrumb">
                            <li><a href="#"><i class="fa fa-dashboard"></i> Cadastro</a></li>
                            <li class="active">Categorias</li>
                        </ol>
                    </section>
                    <!-- Main content -->

                    <div class="modal-dialog">

                        <div class="modal-content">
                            <div class="modal-header">

                                <h4 class="modal-title">Cadastro</h4>
                            </div>
                            <div class="modal-body">

                                <div class="box box-warning">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Dados da Categoria</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <form action="categoriaControle" method="post">
                                            <%Categoria cat = (Categoria) request.getAttribute("categoria");%>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label>Id</label>
                                                        <!-- text input : Id Categoria -->
                                                        <input type="text" class="form-control" placeholder="" name="id" value="<%=cat.getId()%>">
                                                    </div>
                                                    <div class="col-lg-10">						
                                                        <label>Nome</label>
                                                        <!-- text input : Nome Categoria -->
                                                        <input type="text" class="form-control" placeholder="Nome ..." name="nome" value="<%=cat.getNome()%>">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancelar</button>
                                                <input class="btn btn-primary" type="submit" value="Salvar"/>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <!-- /.box -->
                            </div>

                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->


                    <!-- /.content -->
                    <!-- Pagina Dinamica -->
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
                    $(function () {
                        $("#tb_padrao").DataTable();
                    });
        </script>

    </body>
</html>