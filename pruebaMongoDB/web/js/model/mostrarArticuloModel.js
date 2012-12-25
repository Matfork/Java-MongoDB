//Variables Globales

//Funciones

/*
function cargarUpdate(event){
     $("#txtCodArticulo").val("");   
    
     var data = "&enlace=" + $("#enlaceUpdate").attr("href");
     alert(data);     
     
     $("#WORK").load("ArticuloDaoUpdate").hide().fadeIn(200,function() {
         $("#btnInsertarArticulo").removeAttr("disabled");
     });        
     
    event.preventDefault();
}*/

function cargarUpdate(codigo){    
     $("#txtCodArticulo").val("");   
    
     var data = "&codigo=" + codigo;
    // alert(data);     
     $("#WORK").empty().html('<img src="imgs/loading.gif" />');
     
     $("#WORK").load("EnviarAUpdate",data).hide().fadeIn(200);
     
    //event.preventDefault();
    
}

function eliminarFila(codigo){    
    
    if(confirm('Esta seguro que desea eliminar la fila seleccionada?')){
        
     $("#txtCodArticulo").val("");       
     var data = "&codigoEliminar=" + codigo;
 
     $.ajax({     
            type: "GET",
            url: "ArticuloDaoDelete?"+data,                   
            dataType: "html",
            success: function(rspt){                     
              $("#MENSAJE").html(rspt).hide().fadeIn(500).delay(1500).fadeOut(500, function() {
                    cargarConsulta();
                }) ; 
              
            }
        });	
    }
    //event.preventDefault();
    
}



