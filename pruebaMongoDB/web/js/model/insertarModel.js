//Variables Globales

//Funciones

function insertarArticulo(){       
  
     var data =  $("#fomularioInsertArticulo").serialize();   
// alert(data);
     $.ajax({     
            type: "POST",
            url: "ArticuloDaoInsert",
            data: data,
            dataType: "html",
            success: function(rspt){       
                var obj =  eval( "(" + rspt + ")" ); // Crea un objeto
                if(obj.insercionSatisfactoria == 1){
                    $("#MENSAJE").html(obj.mensaje).hide().fadeIn(500).delay(2000).fadeOut(500, function() {
                         cargarConsulta();
                    }); 
                }else if(obj.insercionSatisfactoria == 0){                                    
                   $("#MENSAJE").html(obj.mensaje).hide().fadeIn(500).delay(4000).fadeOut(500);
                }                
            }
        });    
}