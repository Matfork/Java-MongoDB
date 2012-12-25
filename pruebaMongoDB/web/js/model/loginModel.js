//Variables Globales

//Funciones

function  verificarLoguin(){
  
    var queryString =  "&vNick=" + $('#txtNick').val() + 
    "&vPass=" + $('#txtPass').val();			

    $("#respuestaVerificacion").html("<p><img src='imgs/loading_bar.gif' /></p>");
						
   // alert(queryString);	
    $.ajax({      
        
        type: "POST",
        url: "UsuarioDaoVerificarLogin",
        data: queryString,
        error: function(xhr,msg,excep){
            alert("Status " + xhr.status + ": " + msg + "\n" + excep);
        },
        dataType: "html",
        success: function(msg){
           // alert(msg);
            var obj =  eval( "(" + msg + ")" ); // Crea un objeto
            if(obj.loginSatisfactorio == 1){
                $("#respuestaVerificacion").html(obj.mensaje).hide().fadeIn(500).delay(2000).fadeOut(500, function() {
                    window.location.replace("UsuarioIngresoArticulo");
                }) ; 
            }else if(obj.loginSatisfactorio == 0){                                    
                $("#respuestaVerificacion").html(obj.mensaje).hide().fadeIn(500).delay(2000).fadeOut(500); ; 
            }
          
            
        }
    });			
  
}