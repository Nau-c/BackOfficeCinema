<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BackOfficeCinema - AYUDA</title>
<link href="./styles/home.css" rel="stylesheet" id="bootstrap-css">
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
	<div id="tabla-usuarios">
		<h1 class="mb-3 text-center">Los usuarios y contraseñas con los que puede probar este proyecto son los 
		siguientes:</h1>
		<!-- Creamos la tabla de usuarios con formato Bootstrap -->
		<table class="table table-striped table-dark">
			<thead>
				<tr>
			    	<th scope="col">Username</th>
			      	<th scope="col">Contraseña</th>
			      	<th scope="col">Roles</th>
			      	<th scope="col">Privilegios de acceso a:</th>
			    </tr>
			</thead>
			<tbody>			  	
				<tr>
			      	<td>userweb</td>
			      	<td>1234</td>
			      	<td>Usuario de la web (userweb)</td>
			      	<td>Mi Perfil y Salir (USUARIO)</td>
			    </tr>			
				<tr>
			      	<td>elonmusk</td>
			      	<td>Starship</td>
			      	<td>Administrador (admin) y Usuario básico autenticado (basicuser)</td>
			    	<td>Mi Perfil, Nuevo Usuario - Rol, Listar Usuarios - Roles y Salir (USUARIO) - Nueva Película y 
			    	Listar Películas (REPOSITORIO)</td>
			    </tr>
			    <tr>
			      	<td>billgates</td>
			      	<td>Windows11</td>
			      	<td>Comercial responsable de ventas (commercial) y Usuario básico autenticado (basicuser)</td>
			      	<td>Mi Perfil y Salir (USUARIO) - Nueva Película y Listar Películas (REPOSITORIO)</td>
			    </tr>
			    <tr>
			      	<td>jeffbezos</td>
			      	<td>AmaZon2023</td>
			      	<td>Usuario básico autenticado (basicuser)</td>
			      	<td>Mi Perfil y Salir (USUARIO) - Listar Películas (REPOSITORIO)</td>
			    </tr>
			  </tbody>
			</table>
	</div>  
	<div><p style="text-align:center;"><a href="./" class="font-weight-bold link-primary">Volver</a></p></div>  
    <div style="background-color: #FABADA; text-align:center;">
    	<p><span>©</span> 2022 - Proyecto realizado  por Nauzet López Mendoza</p>
    </div>
    
</body>
</html>
