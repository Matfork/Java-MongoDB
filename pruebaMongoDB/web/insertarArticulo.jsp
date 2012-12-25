<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <script type="text/javascript" src="js/libs/jquery-1.8.1.min.js"></script> <!-- debemos de cargar de nuevo el jquery, de otra forma no detectaria las funciones de insertarControll e insertarModel -->
        <script type="text/javascript" src="js/controller/insertarController.js"></script>
        <script type="text/javascript" src="js/model/insertarModel.js"></script>
     
            <!-- Bootstrap -->
          <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">    
          
        <title>Insercion de Articulo</title>
    </head>
    <body>           
        <div class="offset3 span5" >
        <form name="fomularioInsertArticulo" id="fomularioInsertArticulo" method="post" action="">
            <table width="478">                   
                <tr>
                    <td>
                        <label for="txtNomArticulo" >Nombre de Articulo: </label>
                    </td>
                    <td>
                        <input name="txtNomArticulo" type="text"  id="txtNomArticulo" size="20" maxlength="20" class="span2"><br>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="txtADescripcion" >Descripcion: </label>
                    </td>
                    <td>
                        <textarea id="txtADescripcion" name="txtADescripcion" cols="30" placeholder="Escriba una descripcion corta del producto" style="resize:none"></textarea><br>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="txtPreCosto" >Precio</label>
                    </td>
                    <td>
                        <input name="txtPreCosto" type="text"  id="txtPreCosto" size="20" maxlength="20" class="span1"><br>
                    </td>
                </tr>

               

                <tr>
                    <td>
                        <label for="txtStock" >Stock disponible: </label>
                    </td>
                    <td>
                        <input name="txtStock" type="text"  id="txtStock"  size="5" maxlength="5" class="span1"><br>  
                    </td>
                </tr>

                <tr>
                    <td>
                        <input name="btnInsertarArticulo" type="button"  id="btnInsertarArticulo" value="Insertar" class="btn btn-primary">
                    </td>                  
                </tr>
            </table>
        </form>

        <div id="respuestaInsercion" ></div>
        
        </div>
    </body>
</html>
