<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        <!-- Bootstrap CSS-->
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
        <!-- Font Awesome and Pixeden Icon Stroke icon fonts-->
        <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/pe-icon-7-stroke.css"/>">
        <!-- Google fonts - Roboto-->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,700">
        <!-- lightbox-->
        <link rel="stylesheet" href="<c:url value="/resources/css/lightbox.min.css"/>">
        <!-- theme stylesheet-->
        <link rel="stylesheet" href="<c:url value="/resources/css/style.default.css"/>" id="theme-stylesheet">
        <!-- Custom stylesheet - for your changes-->
        <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">
        <!-- Favicon-->
        <link rel="shortcut icon" href="<c:url value="/resources/favicon.png"/>">
        <!-- Tweaks for older IEs--><!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        <title>Reclamo Ciudadano</title>
    </head>

    <body>
    <!-- navbar-->
    <header class="header">
      <div role="navigation" class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header"><a href="index.html" class="navbar-brand">ReclamoCiudadano</a>
            <div class="navbar-buttons">
              <button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
            </div>
          </div>
          <div id="navigation" class="collapse navbar-collapse navbar-right">
            <ul class="nav navbar-nav">
              <li class="active"><a href="index.html">INICIO</a></li>
              <li><a href="text.html">MAPA</a></li>
              <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Dropdown item 1</a></li>
                  <li><a href="#">Dropdown item 2</a></li>
                  <li><a href="#">Dropdown item 3</a></li>
                  <li><a href="#">Dropdown item 4</a></li>
                </ul>
              </li>
              <li><a href="contact.html">CONTACTO</a></li>
              
            </ul>
              <%
                    if(session.getAttribute("usuario") == null){
                %>
              <a href="#" data-toggle="modal" data-target="#login-modal" class="btn navbar-btn btn-ghost"><i class="fa fa-sign-in"></i>Iniciar Sesión</a>
              <% }else{ %>
              <a href="#" class="btn navbar-btn btn-ghost" onclick="document.getElementById('LogoutForm').submit()"><i class="fa fa-sign-in"></i>Cerrar Sesión</a>
              <% } %>
          </div>
        </div>
      </div>
    </header>
    <!-- *** LOGIN MODAL ***_________________________________________________________
    -->
    <form action="usuario.htm" method="POST" id="LogoutForm">
                  <input type="hidden" name="action" value="logout">
              </form>
    <div id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true" class="modal fade">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
            <h4 id="Login" class="modal-title">Ingresar Sistema</h4>
          </div>
          <div class="modal-body">
            <form action="usuario.htm" method="POST">
                <input type="hidden" name="action" value="login">
              <div class="form-group">
                <input id="email_modal" type="email" name="email" placeholder="correo" class="form-control">
              </div>
              <div class="form-group">
                <input id="password_modal" type="password" name="password" placeholder="password" class="form-control">
              </div>
              <p class="text-center">
                <button type="submit" class="btn btn-primary"><i class="fa fa-sign-in"></i> Login</button>
              </p>
            </form>
            <p class="text-center text-muted">¿Sin Cuenta?</p>
            <p class="text-center text-muted"><a href="#"><strong>Cree su cuenta ahora</strong></a>! Es fácil, en menos de 1 minuto podrá completar el formulario!</p>
          </div>
        </div>
      </div>
    </div>
    <!-- *** LOGIN MODAL END ***-->