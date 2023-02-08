<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EL RINCÓN DEL CINE - Acceso</title>
    <link href="./styles/login.css" rel="stylesheet" id="bootstrap-css">
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="sidenav">
        <div class="login-main-text">
           <h2>EL RINCÓN DEL CINE</h2>
           <p>Administración y gestión</p>
        </div>
     </div>
     <div class="main">
        <div class="col-md-6 col-sm-12">
           <div class="login-form">
              <form:form action="./logginprocess">
                 <div class="form-group">
                    <label>Nombre de usuario</label>
                    <input type="text" name="username" class="form-control" placeholder="User Name">
                 </div>
                 <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" name="password" class="form-control" placeholder="Password">
                 </div>
                 <button type="submit" class="btn btn-dark text-white">Entrar</button>
                 <a href="#" class="btn btn-secondary">Ayuda</a>
                 <a href="#" class="btn btn-secondary">Salir</a>
                 
                 
                  <!-- f)  -->
					<c:if test="${param.error != null}"><div><p class="error">Validación incorrecta</p></div></c:if>
					<c:if test="${param.logout != null}"><div><p class="logout">Sesión de usuario cerrada</p></div></c:if>
              </form:form>
              <!-- b)  -->
				<c:if test="${CookieData != null}"><div><p>Fecha del último acceso: ${CookieData.checkCookie}</p></div></c:if>
              
           </div>
        </div>
     </div>
</body>
</html>