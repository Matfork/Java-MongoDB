<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <script type="text/javascript" src="js/libs/jquery-1.8.1.min.js"></script> <!-- debemos de cargar de nuevo el jquery, de otra forma no detectaria las funciones de insertarControll e insertarModel -->
        <script type="text/javascript" src="js/controller/updateController.js"></script>
        <script type="text/javascript" src="js/model/updateModel.js"></script>
        
             <!-- Bootstrap -->
          <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
        
        <title>Insercion de Articulo</title>
    </head>
    <body>          
        <div class="offset3 span5" >
        <form name="formularioActualizarArticulo" id="formularioActualizarArticulo" method="post" action="">
            <table width="478">                   
                <tr>
                    <td>
                        <label for="codArticulo" >Codigo Articulo: </label>
                    </td>
                    <td>
                        <input name="txtCodArticulo"  class="span2" style="background-color: #CCC " value="${requestScope.articulo.idArticulo}" type="text"  id="txtCodArticulo" size="20" maxlength="20" readonly><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="txtNomArticulo" >Nombre de Articulo: </label>
                    </td>
                    <td>
                        <input name="txtNomArticulo" class="span2" style="background-color: #CCC " value="${requestScope.articulo.nomArticulo}" type="text"  id="txtNomArticulo" size="20" maxlength="20" readonly><br>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <label for="txtADescripcion" >Descripcion: </label>
                    </td>
                    <td>
                        <textarea id="txtADescripcion" name="txtADescripcion" cols="30"  style="resize:none">${requestScope.articulo.descripcion}</textarea><br>                        
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="txtPreCosto" >Precio</label>
                    </td>
                    <td>
                        <input name="txtPreCosto" value="${requestScope.articulo.precio}" type="text"  id="txtPreCosto" size="20" maxlength="20" class="span1"><br>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="txtStock" >Stock disponible: </label>
                    </td>
                    <td>
                        <input name="txtStock" value="${requestScope.articulo.stock}" type="text"  id="txtStock" size="20" maxlength="20" class="span1"><br>  
                    </td>
                </tr>

                <tr>
                    <td>
                        <input name="btnActualizarArticulo" type="button"  id="btnActualizarArticulo" value="Actualizar" class="btn btn-primary">
                    </td>                    
                </tr>
            </table>
        </form>

        <div id="respuestaInsercion" ></div>
        </div>
    </body>
</html>
