
$(document).ready(function(){
	
$(".deleted").click(function(){
	console.log("id " + $(this).attr("data"));
		
	
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");
		
		var idFilm=$(this).attr("data");




//Se invoca a la función ajax de jquery.
	$.ajax({ 
		url     : './deleteFilm',
        method  : 'POST',
        data    : "delete="+idFilm,
        dataType: "json",
	    beforeSend: request => request.setRequestHeader(header, token),
    });
    setTimeout(() => {
			$("#seccion01").load("./films");			
		}, "1000")
	})
	
})