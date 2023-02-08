package es.dsw.configs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

import es.dsw.daos.User_filmDao;
import es.dsw.models.User_film;


/* CONFIGURACIÓN DE SPRING SECURITY (Obligatorio)
 * 
 * A continuación se procede a configurar el comportamiento de Spring Security en nuestro proyecto. Para ello, al igual que ocurre con cualquier
 * configuración de spring que se implementa en una clase, debe precedir de la java annotation @Configuration (recordar que también se podría configurar en
 * un .xml). A continuación se debe indicar que esta clase configurará Spring Security añadiendo  @EnableWebSecurity.
 * */

@Configuration
@EnableWebSecurity
public class SecurityAppConfig {

	
	//CONFIGURACIÓN DE LOS USUARIOS DE ACCESO A LA APLICACIÓN (Obligatorio)
    //Se implementa el bean userDetailsService que será ejecutado al arrancar la aplicación. Dado que esto es un proyecto demo, dentro se construyen los usuarios
	//que en este caso se han fijado de forma estática. En este trozo de código, se podría conectar a base de datos y obtener la lista de usuarios y roles y construirlos
	//de forma dinámica.
	//Sin embargo, debes conocer que Spring Security tiene varias opciones a la hora de poder construir el pool de usuarios y en la documentación tiene pistas de como configurar
	//este módulo para que los extraiga de una tabla en bbdd o incluso conectar a un servicio LDAP.
	//Recuerda que @Bean indica a Spring que es un componente que devolverá un bean InMemoryUserDetailsManager cuyo identificador es el nombre del método.
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
      
    	//Esta java annotation únicamente está deshabilitando los warning producto de usar User.withDefaultPasswordEncoder. Realmente dicho método no está deprecate, sino que por seguridad
    	//se recuerda al desarrollador, que esta forma de crear usuarios no garantiza el cifrado de contraseñas. 
    	//Aquí se podría iterar cargando los usuarios que se obtengan desde base de datos.
    	
		User_filmDao Usuario = new User_filmDao();
		
        ArrayList<User_film> objListUsuario = Usuario.getAll();
        
        
        InMemoryUserDetailsManager InMemory = new InMemoryUserDetailsManager();
        
        for(User_film user : objListUsuario ) {
        	StringBuilder roles = new StringBuilder("");
        	for(String eachstring: user.getROL()) {
        		roles.append(eachstring).append(",");
        	}
        	
        	@SuppressWarnings("deprecation")
        	UserDetails User_film = User.withDefaultPasswordEncoder()
        	.username(user.getUSERNAME_USF())
        	.password(user.getPASSWORD_USF())
        	.roles(roles.toString().split(","))
        	.build();

        	 InMemory.createUser(User_film);
        }
       
        //Se devuelve a el modulo de Spring Security el descriptor del objeto InMemoryUserDetailsManager para que surta efecto las modificaciones.
       return InMemory;
    }


    //CONFIGURACIÓN DE LOS FILTROS REQUEST DE HTTP (Opcional)
    //A continuación, se utiliza el siguiente bean para configurar los filtros de seguridad de httpSecurity.
    //Sin este método (sin este beans configurado) spring aplica una configuración por defecto creando por el mismo
    //una vista de login. Por ello, te invito a que pruebes a ejecutar este proyecto, comentando este método (filterChain).
    //
    //La filosofía de Spring Security es por defecto, denegar TODA petición, salvo el login por defecto que el mismo genera. Por ello, al configurar
    //los filtros de las peticiones, se recomienda, primero indicar, que rutas o recursos deben estar siempre accesibles (ej: .css, .js, etc) o
    //url pública que deba acceder cualquier cliente. Y a continuación, indicar los mapeos de controladora que solo son accesibles a usuarios autenticados con
    //determinados roles (autentificaciones). Si un usuario es autenticado, spring deja el acceso a todo, salvo a los filtros que indiquemos o discriminemos
    //según los roles de usuario.
    
    //Por lo tanto, si defines este beans, entonces spring security aplica los filtros que indiquemos.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        

    	//Se indica a continuación que se aplicarán filtros a los request, es decir, a todas las peticiones al servidor.
    	http.authorizeRequests()
    		    //1º. INDICAR RECURSOS QUE DEBEN ESTAR ACCESIBLES PARA TODOS LOS CLIENTES (Ej: .css, .js, etc).
    	 		.regexMatchers("/styles/*.*") //Se permite a todos los clientes el acceso a recursos de estilos
    	 			.permitAll()
    	 		.regexMatchers("/img/*.*")    //Se permite a todos los clientes el acceso a recursos de imagenes
    	 			.permitAll() 
        	 	.regexMatchers("/ayuda")      //Se permite a todos los clientes el acceso a la url "ayuda" (mapeo en controladora)
    	 			.permitAll()
    	 		.regexMatchers("/bootstrap/4.3.1/css/*.*")      //Se permite a todos los clientes el acceso a la url "ayuda" (mapeo en controladora)
    	 			.permitAll() 
    	 		.regexMatchers("/bootstrap/4.3.1/js/*.*")      //Se permite a todos los clientes el acceso a la url "ayuda" (mapeo en controladora)
    	 			.permitAll()
    	 		.regexMatchers("/js/*.*")      //Se permite a todos los clientes el acceso a la url "ayuda" (mapeo en controladora)
    	 			.permitAll() 
    	 			
    	 		//2º. INDICAR LAS RUTAS ESPECIFICAS QUE SOLO PODRÍAN ACCEDER O REALIZAR PETICIONES DETERMINADOS ROLES
    	 			//En este caso, se indica que para todas las rutas url bajo /admin/ (de forma recursiva), solo podrán
    	 			//acceder los usuarios con rol administrador. Si existieran más especifidades, solo debe copiar la línea de
    	 			//código siguente añadiendo tantas como existan. El doble ** indica que afecta a todos los directorios bajo dicha ruta
    	 			//incluido el directorio raíz indicado.
    	 		.antMatchers("/admin/**").hasRole("administrador") //Solo los usuarios con rol administrador, accederán a los mapeos bajo /admin/
    	 		//3º. INDICAR LA CONFIGURACIÓN DEL LOGIN y EL LOGOUT
    	 		.anyRequest()
    	 			.authenticated() //Configuración para el proceso de autenticación de usuario
    	 				.and()
    	 					.formLogin() //Configuración para logeo basado en formulario de login.
    	 						.loginPage("/login") //Se indica la controladora que devuelve la vista de logeo. Debe estar implementada en una controladora.
    	 						.loginProcessingUrl("/logginprocess") //Se indica la controladora que procesará los datos del logeo. Este mapeo no es necesario implementarlo en ninguna controladora.
    	 						.permitAll() //Se indica que la controladora /loggin estará accesibles a todos los clientes (para que todo cliente tenga la oportunidad de logearse).
    	 				.and()
    	 					.logout().permitAll(); //Se habilita el logout. No es necesario implementar este mapeo en ninguna controladora, al invocar /logout, spring security anula la autenticación y además reinicia las variables de sesión.
    	
    	//Se devuelve el beans para que spring lo procese.
        return http.build();
    }
    
    //HABILITACIÓN DE LOS EVENTOS onSuccess onFailure (Opcional)
    //Configuración necesaria para la captura de los eventos de login exitoso y login fallido, que se implementan en la clase
    //AuthenticationEvents.java. Para más información: https://docs.spring.io/spring-security/reference/servlet/authentication/events.html
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher
            (ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
}
