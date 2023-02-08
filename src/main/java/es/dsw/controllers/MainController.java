package es.dsw.controllers;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import es.dsw.daos.Rol_filmDao;
import es.dsw.daos.User_filmDao;
import es.dsw.models.CookieData;
import es.dsw.models.Country;
import es.dsw.models.Genre;
import es.dsw.models.Producer;
import es.dsw.models.Rol_film;
import es.dsw.models.User_film;
import es.dsw.models.film;
import es.dsw.daos.film_dao;
import es.dsw.daos.Genre_Dao;
import es.dsw.daos.Country_Dao;
import es.dsw.daos.Producer_Dao;



@Controller
public class MainController {
	
	//Este mapeo solo es accesible a usuarios autenticados. (Ver configuración SecurityAppConfig).
	@GetMapping(value = {"/","/home"})
	public String home(Model objModel,  HttpServletResponse response) {
		
		
		User_filmDao objUsuarioDao = new User_filmDao();
		
		

		
		ArrayList<User_film> objTablaUsuarios = objUsuarioDao.getAll();
		Rol_filmDao objRolDao = new Rol_filmDao();
		
		ArrayList<Rol_film> objTablaRoles = objRolDao.getAll();

		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		for(int cont=0; cont<objTablaUsuarios.size(); cont++) {
			if (objTablaUsuarios.get(cont).getUSERNAME_USF().equals(currentPrincipalName)) {
				// Se obtienen datos del usuario, tales como el nombre y sus apellidos. 
				// Todos los datos se asignan al modelo de spring para mostrar la información en las vistas.
				String name= objTablaUsuarios.get(cont).getNAME_USF();
				String surname = objTablaUsuarios.get(cont).getFIRSTSURNAME_USF();
	    		objModel.addAttribute("Nombre", name );
	    		objModel.addAttribute("Apellidos", surname);
			}
		}
		
		objModel.addAttribute("Usuarios", objTablaUsuarios);
		objModel.addAttribute("TiposDeRoles", objTablaRoles);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.now();
		
        Cookie cookieFechHoraAcceso = new Cookie("CookieAccessTime", dtf.format(dateTime).toString());
        
        cookieFechHoraAcceso.setMaxAge(24 * 60 * 60); 

        response.addCookie(cookieFechHoraAcceso);

        
		objModel.addAttribute("CookieData", cookieFechHoraAcceso);

		
		
		
		Country_Dao objCountryDao = new Country_Dao();
		ArrayList<Country> objTableCountry = objCountryDao.getAll();
		
		Producer_Dao objProducerDao = new Producer_Dao();
		ArrayList<Producer> objTableProducer = objProducerDao.getAll();
		
		Genre_Dao objGenreDao = new Genre_Dao();
		ArrayList<Genre> objTableGenre = objGenreDao.getAll();
		
		objModel.addAttribute("TableGenre" , objTableGenre);
		
		objModel.addAttribute("TableProducer" , objTableProducer);
		
		objModel.addAttribute("TableCountry" , objTableCountry);
		
		return "home";
	}
	
	//Este mapeo es accesible para todos los clientes. (Ver configuración SecurityAppConfig).
	@GetMapping(value = {"/login"})
	public String login(@CookieValue(name="CookieAccessTime", required=false) String auxCookie, Model objModel) {
		
		if (auxCookie != null) {
			// Se crea un objeto a modo de modelo de negocio para alimentar la vista
			CookieData objCookieData = new CookieData();
			
			// Se inicializa el resultado o información que se mostrará en la vista. Por defecto, 
			// se asume que la cookie no existe.
			objCookieData.setCheckCookie(auxCookie);	
			
			//Se carga el modelo del negocio (CookieData) como atributo en el modelo de spring.
			objModel.addAttribute("CookieData", objCookieData);
		}
		
		
		
		return "login";
	}
	
	
	
	
	
	
	
	//Listar Peliculas
	@GetMapping(value = {"/films"})
	public String film(Model objModel) {
		
		film_dao objFilm = new film_dao();
		ArrayList<film> objListFilm = objFilm.getAll();
		
		objModel.addAttribute("ListFilm", objListFilm);

		
		return "films";
	}
	
	
	//Eliminar Pelicula
		@RequestMapping(value= {"/","/deleteFilm"},method = RequestMethod.POST, produces="application/json")
		private void procesarDatos(HttpServletRequest request, @RequestParam("delete") String delete) {
			
				
			
			int id = Integer.parseInt(delete);
			
			film_dao objFilm = new film_dao();
			objFilm.deleteById(id);
			
					
			}
		




	@RequestMapping(value= {"/","/newFilm"},method = RequestMethod.POST)
		public String newFilm(Authentication authentication, @RequestParam("titulo") String titulo, @RequestParam(name="sinopsis", required=false) String sinopsis, @RequestParam(name="genero", defaultValue="-1") int genero, @RequestParam(name="director", required=false) String director, @RequestParam(name="reparto", required=false) String reparto, @RequestParam(name="anyo", defaultValue="-1") int anyo, @RequestParam(name="estreno", required=false, defaultValue="0000-00-00") String estreno, @RequestParam(name="distribuidor", required=false, defaultValue="-1") int distribuidor, @RequestParam(name="pais", defaultValue="-1") int pais, Model objModel) throws ParseException {
			System.out.println(titulo);
					java.sql.Date sqlDate;
					if(estreno == "0000-00-00") {
						sqlDate = null;
					}else {
						java.util.Date utilDate =  new SimpleDateFormat("yyyy-MM-dd").parse(estreno);
						 sqlDate = new java.sql.Date(utilDate.getTime());
					}
			
			String mensaje = "";
			boolean error = false;
			
			User_filmDao objUser = new User_filmDao();
			
			Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication1.getName();
			int idUsers = 0;
			ArrayList<User_film> objTablaUsuarios = objUser.getAll();
			for(int cont=0; cont<objTablaUsuarios.size(); cont++) {
				if (objTablaUsuarios.get(cont).getUSERNAME_USF().equals(currentPrincipalName)) {
					// Se obtienen datos del usuario, tales como el nombre y sus apellidos. 
					// Todos los datos se asignan al modelo de spring para mostrar la información en las vistas.
					
					 idUsers = objTablaUsuarios.get(cont).getIDUSER_USF();
			}
			
			}

		
			
			if(titulo !="" && genero!=-1 && anyo!=-1 && pais!=-1) {
				film film = new film();
				film.setTittle_RF(titulo);
				film.setSynopsis_RF(sinopsis);
				film.setIdGenre_RF(genero);
				film.setDirector_RF(director);
				film.setReparto_RF(reparto);
				film.setAnio_RF(anyo);
				
				film.setDatePremiere_RF(sqlDate);
				
				film.setIdProducer_RF(distribuidor);
				film.setIdCountry_RF(pais);
				film.setS_iduser_CF(idUsers);
				
				film_dao objPeliculas = new film_dao();
				objPeliculas.setPelicula(film);
				
				
				
				
				mensaje= mensaje + "La película se ha creado correctamente";
				error = false;
			}
			if(titulo == "") {
				mensaje = mensaje + "Debe introducir el título de la película. ";
				error = true;
			}
			if(genero == -1) {
				mensaje = mensaje + "Debe introducir el género de la película. ";
				error = true;
			}
			if(anyo == -1) {
				mensaje = mensaje + "Debe introducir el año de la película. ";
				error = true;
			}
			if(pais == -1) {
				mensaje = mensaje + "Debe introducir el país de la película. ";
				error = true;
			}
			
			
			
			objModel.addAttribute("mensaje",mensaje);
			objModel.addAttribute("error",error);

			
			return "newFilm";
		
		}

	
	
	}
	
		

