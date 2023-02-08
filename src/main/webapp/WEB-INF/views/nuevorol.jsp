<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BackOfficeCinema - NUEVO ROL</title>
<link href="./styles/home.css" rel="stylesheet" id="bootstrap-css">
<link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
	<h1 class="mb-3 text-center">Resultado Inserción Rol:</h1> 
   	<c:choose>
	    <c:when test="${CamposCumplimentados}">
	    	<p class="font-weight-bold text-success text-center">Los campos se han cumplimentado correctamente.</p>	    	
	    </c:when>    
	    <c:otherwise>
	    	<p class="font-weight-bold text-warning text-center">Se ha producido un error. +Info:
  			<br/> - El campo «Código Rol» puede haberse dejado sin cumplimentar.
			<br/> - El campo «Nombre Rol» puede haberse dejado sin cumplimentar.</p>
	    </c:otherwise>
	</c:choose>
	<c:choose>
	    <c:when test="${ResultadoOperacion}">
	    	<p class="font-weight-bold text-success text-center">¡El rol «${rol}» se ha registrado con éxito en la base 
	    	de datos!.</p>	    		    	
	    </c:when>    
	    <c:otherwise>
	    	<p class="font-weight-bold text-warning text-center">Lo sentimos, se ha producido un error a la hora de registrar 
    		el rol «${rol}» en la base de datos.</p>	    	
	    </c:otherwise>
	</c:choose>	    
    <div><p style="text-align:center;"><a href="./" class="font-weight-bold link-primary">Volver</a></p></div>
</body>
</html>