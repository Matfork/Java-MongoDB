//Variables Globales

//Funciones

function  cargarConsulta(){
  
    $("#btnConsultar").attr("disabled", "disabled");    
    var data = $("#form1").serialize();   
    $("#WORK").load("ArticuloDaoSelect", data).hide().fadeIn(200,function() {
            $("#btnConsultar").removeAttr("disabled");
        });
    
}

function  cargarInsertar(){  
     
     $("#btnIrAInsertarArticulo").attr("disabled", "disabled");
     $("#txtCodArticulo").val("");
       // Put an animated GIF image insight of content
     $("#WORK").empty().html('<img src="imgs/loading.gif" />');
     
     $("#WORK").load("EnviarAInsert").hide().fadeIn(200,function() {
         $("#btnIrAInsertarArticulo").removeAttr("disabled");
     });     
}



function  cargarNombreUsuario(){   
    $.ajax({     
        
        type: "GET",
        url: "GetUsuarioInfo",       
        error: function(xhr,msg,excep){
            alert("Status " + xhr.status + ": " + msg + "\n" + excep);
        },
        dataType: "html",
        success: function(rspt){      
             //alert(rspt);
             if(rspt =="") //en casp de que haya un sabido que quiera entrar directamente a la pagina de articulos sin pasar por el login
                 window.location.replace("error.jsp");
             else
                $("#userLoggedIn").html("<b>Usuario Logeado:</b> "+rspt);            
        }
    });		
  
}


//Redireccion a la pagina de menuAuditoriaArticulo.jsp o cualquier otra pagina, debemos de pasar por el controlador previamente para ver si la sesion sigue existiendo

function redireccionOtraPagina(e,obj){
    
   var $this= $(obj);
     pagina = $this.attr("href");    
    /*  Forma General
     
        $.ajax({             
        type: "GET",
        url: "RedireccionPagina", 
        dataType: "html",
        success: function(rspt){              
             if(rspt==1) //si la sesion sigue existiendo, procedemos a ir a la otra pagina
                 window.location.replace(pagina);
             else if(rspt ==0)
                 window.location.replace("error.jsp");           
        }
    });	    
    */
   
    // Con el metodo Abreviado Get
    $.get("RedireccionPagina", function(rspt) {       
         if(rspt==1) //si la sesion sigue existiendo, procedemos a ir a la otra pagina
                 window.location.replace(pagina);
         else if(rspt ==0)
                 window.location.replace("error.jsp");  
    });
    
    
     e.preventDefault();    
}