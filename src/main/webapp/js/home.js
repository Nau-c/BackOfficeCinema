//Se sobrecarga el botón toggle
$(function() {
    // Sidebar toggle behavior
    $('#sidebarCollapse').on('click', function() {
      $('#sidebar, #content').toggleClass('active');
    });

  });

  //Se sobrecarga botón de Guardar de ventana modal y carga de sección del menú
  $(document).ready(function(){
    $('body').on('click', '#GuardarPelicula', function(){
      //En esta zona debería ubicarse la llamada asíncrona al servidor enviando los datos de la nueva película
		
		var data_title = $('#tittle_RF').val();
		var data_sipnosis = $('#synopsis_RF').val();
		var data_genero = $('#genero').val();
		var data_director = $('#director_RF').val();
		var data_reparto = $('#reparto_RF').val();
		var data_anio = $('#anio_RF').val();
		var data_estreno = $('#estreno').val();
		var data_distribuidor = $('#distribuidor').val();
		var data_pais = $('#pais').val();
		
		$("fail").html("");
		
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		$.ajax({
			url:"./newFilm",
			method: "POST",
			data:{titulo: data_title, sinopsis: data_sipnosis, genero: data_genero, director: data_director, reparto: data_reparto, anyo: data_anio, estreno: data_estreno, distribuidor: data_distribuidor, pais: data_pais},
			beforeSend: request => request.setRequestHeader(header, token),
			success: function(resultText){
					$("#fail").html(resultText);
				if(data_title!="" && data_genero!="" && data_anio!="" && data_pais!=""){
	    			setTimeout(() => {
	    			$("#cerrarForm").trigger("click");
	    			$("#newFilmForm").trigger("reset");
					$("#seccion01").load("./films");	
					$("#fail").html("");
					}, "1000")
				}
			},
			fail: function(jqXHR, exception) {
			$('#error').html('<p>Ha ocurrido un error fatal.</p>');
		}
			
		})
  		
		
		

    })

    $('body').on('click', '#IdListarPeliculas', function(){

      $("#seccion01").load("./films");
    })
  })