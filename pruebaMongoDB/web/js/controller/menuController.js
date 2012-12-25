$(document).ready(function(){		
	init();		
	
	function init(){	
           
            cargarNombreUsuario();
	}	
		
	 $("#btnConsultar").click(cargarConsulta);		
         $("#btnIrAInsertarArticulo").click(cargarInsertar);
         
                 
      //   $("#enlaceUpdate").click(cargarUpdate); 
      
      
})