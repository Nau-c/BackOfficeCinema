<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EL RINCÓN DEL CINE - Gestión</title>
    <link href="./styles/home.css" rel="stylesheet" id="bootstrap-css">
    <link href="./bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="./js/jquery-3.6.1.min.js"></script>
    <script src="./bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
 	
    <script src="./js/home.js"></script>
</head>
<body>
    <!-- La maquetación base de esta página ha sido obtenida desde: https://bootstrapious.com/p/bootstrap-vertical-navbar-->
    <!-- Vertical navbar -->
<div class="vertical-nav bg-white" id="sidebar">
    <div class="py-4 px-3 mb-4 bg-light">
      <div class="media d-flex align-items-center"><img src="./img/usuario.gif" alt="..." width="65" class="mr-3 rounded-circle img-thumbnail shadow-sm">
        <div class="media-body">
          <h4 class="m-0">${Nombre} ${Apellidos}</h4>
          <p class="font-weight-light text-muted mb-0"><security:authentication property="principal.username"/></p>
        </div>
      </div>
    </div>
  
    <!--Area del menú -->
    <p class="text-gray font-weight-bold text-uppercase px-3 small pb-4 mb-0">Usuario</p>
  
    <ul class="nav flex-column bg-white mb-0">
    	<security:authorize access="hasRole('admin') || hasRole('basicuser') || hasRole('userweb') || hasRole('commercial')">
      <li class="nav-item">
      
      <!-- B) -->
            <a href="#" class="nav-link text-dark font-italic bg-light">
                      <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                      Mi Perfil
                  </a>
      </li>
      </security:authorize>
      <security:authorize access="hasRole('admin')">
      
      <!-- C) -->
      <li class="nav-item">
        <a href="#" class="nav-link text-dark font-italic">
                  <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                  Nuevo usuario
              </a>
      </li>
      </security:authorize>
      <security:authorize access="hasRole('admin')">
      <li class="nav-item">
        <a href="#" class="nav-link text-dark font-italic">
                  <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                  Nuevo rol
              </a>
      </li>
      </security:authorize>
      <security:authorize access="hasRole('admin')">
      <li class="nav-item">
        <a href="#" class="nav-link text-dark font-italic">
                  <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                  Listar usuarios
              </a>
      </li>
      </security:authorize>
      <security:authorize access="hasRole('admin')">
      <li class="nav-item">
        <a href="#" class="nav-link text-dark font-italic">
                  <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                  Listar roles
              </a>
      </li>
      </security:authorize>
      
      <security:authorize access="hasRole('admin') || hasRole('basicuser') || hasRole('userweb') || hasRole('commercial')">
       <li class="nav-item">
      	<form id="logout" action="./logout" method="post" >
  			<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"  />
		</form>
     
        <a href="javascript:document.getElementById('logout').submit()" class="nav-link text-dark font-italic">
                  <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                  Salir
              </a>
        
      </li>
      </security:authorize>
    </ul>
    <br/>
    <security:authorize access="hasRole('admin') || hasRole('basicuser') || hasRole('commercial')">
    <p class="text-gray font-weight-bold text-uppercase px-3 small pb-4 mb-0">Repositorio</p>
  	</security:authorize>
    <ul class="nav flex-column bg-white mb-0">
    <security:authorize access="hasRole('admin') || hasRole('commercial')">
      <li class="nav-item">
        <a href="#" class="nav-link text-dark font-italic" data-toggle="modal" data-target="#NuevaPeliculaCenter">
                  <i class="fa fa-th-large mr-3 text-primary fa-fw"></i>
                  Nueva película
              </a>
      </li>
      </security:authorize>
      	<security:authorize access="hasRole('admin') || hasRole('commercial') || hasRole('basicuser')">
      <li class="nav-item">
        <a id="IdListarPeliculas" href="#" class="nav-link text-dark font-italic">
                  <i class="fa fa-address-card mr-3 text-primary fa-fw"></i>
                  Listar películas
              </a>
      </li>
      </security:authorize>
    </ul>

  

  </div>
  
  		<div id="tabla-peliculas" style="display: none;">
			<h2 class="display-4 text-white"><b>Listado de peliculas</b></h2>
			<!-- Creamos la tabla de roles con formato Bootstrap -->
			<table class="table table-striped table-dark">
			  <thead>
			    <tr>
			      <th scope="col">IDFILM_RF</th>
			      <th scope="col">TITLE_RF</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach begin="0" step="1" items="${ListaPeliculas}" var="item">
			    	<!-- Se obtienen datos del rol actual, tales como su identificador, su código y nombre completo. 
						Todos los datos se asignan al modelo de spring para mostrar la información en las vistas. -->
					<tr>
				      	<td>${item.idFilm}</td>
						<td>${item.tittle_RF}</td>
				    </tr>			
				</c:forEach>
			  </tbody>
			</table>		
		</div>  
  
  		
 
  
  <!--Area del home page -->

  <!-- Page content holder -->
  <div class="page-content p-5" id="content">
    <!-- Toggle button -->
    <button id="sidebarCollapse" type="button" class="btn btn-light bg-white rounded-pill shadow-sm px-4 mb-4"><i class="fa fa-bars mr-2"></i><small class="text-uppercase font-weight-bold"></small></button>
  
    <!-- Capa seccion01 donde se cargarán las secciones-->
    <div id="seccion01">
      <h2 class="display-4 text-white"><b>Mi Perfil</b></h2>
      <p class="lead text-white mb-0"><b>Nombre:</b> ${Nombre}</p>
      <p class="lead text-white mb-0"><b>Apellidos:</b> ${Apellidos}</p>
      <p class="lead text-white mb-0"><b>Nombre de usuario:</b> <security:authentication property="principal.username"/></p>
      <p class="lead text-white mb-0"><b>Roles:</b> <security:authentication property="principal.authorities"/></p>
      <br/>
      <p class="lead text-white"><a href="javascript:document.getElementById('logout').submit()" class="text-white">
            Salir</a>
      </p>   
      <!-- <div class="separator"></div> -->
	</div>
  </div>
  <!-- End demo content -->
  
  <!-- Modal -->
  <div class="modal fade" id="NuevaPeliculaCenter" tabindex="-1" role="dialog" aria-labelledby="NuevaPeliculaCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="NuevaPeliculaLongTitle">Nueva película</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
			<form:form id="newFilmForm" method="POST" action="#">
				<div class="form-group">
					<label>Título</label>
					<input type="text" id="tittle_RF">
				</div>
				<div class="form-group">
					<label>Sinopsis</label>
					<input type="text" id="synopsis_RF">
				</div>
					<div class="form-group">
					<label>Género</label>
					<select name="genero" id="genero">
						<c:forEach begin="0" step="1" items="${TableGenre}" var="item">
							<option value=${item.getIdgenre_GF() }>${item.getGenre_GF()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>Director</label>
					<input type="text" id="director_RF">
				</div>
				<div class="form-group">
					<label>Reparto</label>
					<input type="text" id="reparto_RF">
				</div>
				<div class="form-group">
					<label>Año</label>
					<input type="number" id="anio_RF">
				</div>
				<div class="form-group">
					<label>Fecha del estreno</label>
					<input type="date" id="estreno">
				</div>
				<div class="form-group">
					<label>Distribuidor</label>
					<select name="distribuidor" id="distribuidor">
						<c:forEach begin="0" step="1" items="${TableProducer}" var="item">
							<option value=${item.getIdproducer_PF() }>${item.getProducer_PF()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label>País</label>
					<select name="pais" id="pais">
						<c:forEach begin="0" step="1" items="${TableCountry}" var="item">
							<option value=${item.getIdcountry_CF() }>${item.getCountry_CF()}</option>
						</c:forEach>
					</select>
				   
				</div>
			</form:form>
        </div>
        <div id="error"></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" id="cerrarForm">Cerrar</button>
          <button id ="GuardarPelicula" type="button" class="btn btn-dark">Guardar</button>
        </div>
      </div>
    </div>
  </div>
  
  
  
  <!-- Lo de abajo son pruebas -->
  
<div id="tabla-usuarios" style="display: none;">
			<h2 class="display-4 text-white"><b>Listado de Usuarios</b></h2>
			<!-- Creamos la tabla de usuarios con formato Bootstrap -->
			<table class="table table-striped table-dark">
			  <thead>
			    <tr>
			      <th scope="col">ID Usuario</th>
			      <th scope="col">Username</th>
			      <th scope="col">Nombre Usuario</th>
			      <th scope="col">Apellido</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach begin="0" step="1" items="${Usuarios}" var="item">
			    	<!-- Se obtienen datos del usuario actual, tales como su identificador, username, nombre y apellido. 
						Todos los datos se asignan al modelo de spring para mostrar la información en las vistas. -->
					<tr>
				      	<td>${item.IDUSER_USF}</td>
				      	<td>${item.USERNAME_USF}</td>
						<td>${item.NAME_USF}</td>
						<td>${item.FIRSTSURNAME_USF}</td>
				    </tr>			
				</c:forEach>
			  </tbody>
			</table>		
		</div> 
		
		
		<div id="tabla-roles" style="display: none;">
			<h2 class="display-4 text-white"><b>Listado de Roles</b></h2>
			<!-- Creamos la tabla de roles con formato Bootstrap -->
			<table class="table table-striped table-dark">
			  <thead>
			    <tr>
			      <th scope="col">ID Rol</th>
			      <th scope="col">Código Rol</th>
			      <th scope="col">Nombre Rol</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach begin="0" step="1" items="${TiposDeRoles}" var="item">
			    	<!-- Se obtienen datos del rol actual, tales como su identificador, su código y nombre completo. 
						Todos los datos se asignan al modelo de spring para mostrar la información en las vistas. -->
					<tr>
				      	<td>${item.idRol}</td>
						<td>${item.rolcode}</td>
						<td>${item.rolname}</td>
				    </tr>			
				</c:forEach>
			  </tbody>
			</table>		
		</div>  
		

<div id="nuevo-usuario" style="display: none;">
	  		<h1 class="mb-3">Formulario inserción Usuario</h1>    
			<form:form action="./nuevousuario" method="POST">
				<div class="form-group">
				    <label for="inputUserName">Seudónimo (username)</label>
				    <input type="text" name="username" class="form-control" id="inputUserName" aria-describedby="usernameHelp" 
				    placeholder="Introduzca su seudónimo">
				    <small id="usernameHelp" class="form-text text-muted">Ejemplos: elonmusk, alumno3DAW</small>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword">Contraseña</label>
				    <input type="password" name="password" class="form-control" id="inputPassword" 
				    placeholder="Introduzca su contraseña">
				  </div>
				  <div class="form-group">
				    <label for="inputName">Nombre</label>
				    <input type="text" name="nombre" class="form-control" id="inputName" aria-describedby="nameHelp" 
				    placeholder="Introduzca su nombre">
				    <small id="nameHelp" class="form-text text-muted">Ejemplos: Mapi, Elon</small>
				  </div>
				  <div class="form-group">
				    <label for="inputFirstSurname">Primer apellido</label>
				    <input type="text" name="primerapellido" class="form-control" id="inputFirstSurname" 
				    aria-describedby="firstSurnameHelp" placeholder="Introduzca su primer apellido">
				    <small id="firstSurnameHelp" class="form-text text-muted">Ejemplos: Fernández, Musk</small>
				  </div>
				  <div class="form-group">
				    <label for="inputEmail">Correo electrónico</label>
				    <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" 
				    placeholder="Introduzca su dirección de correo electrónico">
				    <small id="emailHelp" class="form-text text-muted">Nota: Su dirección de correo no será compartida con nadie.</small>
				  </div>	
				  <!-- IMPORTANTE: Si en el action ponemos una página a la que enviar el formulario una vez enviemos el formulario, 
				  hemos de tener en cuenta poner el atributo type del button como «submit» para que se ejecute el comportamiento 
				  por defecto de este evento. -->	  
				<button type="submit" class="btn btn-primary">Insertar</button>
			</form:form>
		</div> 
        
        <div id="nuevo-rol" style="display: none;">
            <h1 class="mb-3">Formulario inserción Rol</h1>        
			<form:form action="./nuevorol" method="POST">
				<div class="form-group">
				    <label for="inputCodRol">Código Rol</label>
				    <input type="text" name="codrol" class="form-control" id="inputCodRol" aria-describedby="codRolHelp" 
				    placeholder="Introduzca la abreviatura del rol">
				    <small id="codRolHelp" class="form-text text-muted">Ejemplos: admin, basicuser</small>
				  </div>
				  <div class="form-group">
				    <label for="inputNameRol">Nombre Rol</label>
				    <input type="text" name="nombrerol" class="form-control" id="inputNameRol" aria-describedby="nameRolHelp" 
				    placeholder="Introduzca el nombre del rol">
				    <small id="nameRolHelp" class="form-text text-muted">Ejemplos: Administrador, Usuario básico autenticado</small>
				  </div>				  	  
				<button type="submit" class="btn btn-primary">Insertar</button>
			</form:form>
		</div> 
    </div>
  
  </div>



<!-- Ventana Modal en bootstrap para Nueva Pelicula -->
<div class="modal fade" id="NuevaPeliculaCenter" tabindex="-1" role="dialog" aria-labelledby="NuevaPeliculaCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="NuevaPeliculaLongTitle">Nueva película</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          [Formulario de recogida de datos]
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
          <button id ="GuardarPelicula" type="button" class="btn btn-dark">Guardar</button>
        </div>
      </div>
    </div>
  </div>

</body>
</html>