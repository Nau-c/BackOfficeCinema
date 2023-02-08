<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EL RINCÓN DEL CINE - Gestión</title>
    <link href="./styles/home.css" rel="stylesheet" id="bootstrap-css">
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
 
    <script src="./js/films.js"></script>
    
    
</head>

<table border="1" background-color="green" class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Género</th>
			<th>Director</th>
			<th>Reparto</th>
			<th>Fecha de estreno</th>
			<th></th>
		</tr>
	</thead>
		<tbody>
	<c:forEach begin="0" step="1" items="${ListFilm}" var="item">
			<tr>
				<td>${item.getTittle_RF()}</td>
				<td>${item.getGenero()}</td>
				<td>${item.getDirector_RF()}</td>
				<td>${item.getReparto_RF()}</td>
				<td>${item.getDatePremiere_RF()}</td>
				<td><form:form> <input type="button" class="deleted btn btn-danger" data="${item.getIdFilm()}" value="Eliminar"/></form:form> </td>
			</tr>
	</c:forEach>
		</tbody>
</table>
</html>