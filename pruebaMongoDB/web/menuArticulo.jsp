<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">    
        <script type="text/javascript" src="js/libs/jquery-1.8.1.min.js"></script>
        <script type="text/javascript" src="js/controller/menuController.js"></script>
        <script type="text/javascript" src="js/model/menuModel.js"></script>

        <title>JSP Page</title>
    </head>
    <body>       

        <!-- para pasar por el controlador usamos el metodo redireccionOtraPagina, este evento servira para cualquier enlace y ademas chequeara si la session sigue vigente, la pagina debe estar colocalada en el href -->

        <!--
        <a href="menuArticulo.jsp" onclick="redireccionOtraPagina(event,this)" >Articulo</a>
        <a href="menuAuditoriaArticulo.jsp" onclick="redireccionOtraPagina(event,this)" >Auditoria Articulo</a>
        -->

        <!-- Otra forma de redirigir, de igual forma que el anterior primero se revisa la session mediante el uso del controlador RedireccionController -->
        <div class="navbar navbar-inverse">
            <div class="navbar-inner">       			
                <ul class="nav">                   
                    <li class="active">  <a href="IrMenuArticulo" >Menu Articulo</a></li>
                    <li> <a href="IrLogOff" >Log Off</a></li>
                </ul>
            </div>
        </div>
    

        <div class="container">
                <div class="offset8 span4" id="userLoggedIn"></div>  

            <h2> Mantenimiento de Articulos</h2>

            <form name="form1" id="form1" method="post"  class="form-search">
                <table width="478">             
                    <tr>
                        <td> <input name="btnIrAInsertarArticulo" type="button"  id="btnIrAInsertarArticulo" value="Nuevo" class="btn btn-primary"></td>
                        <td>
                            <div class="input-append">
                                <input name="txtCodArticulo" type="text"  id="txtCodArticulo"  class="span3 search-query" placeholder="Ingrese Codigo de Articulo...">
                                <button  class="btn" name="btnConsultar" type="button"  id="btnConsultar" ><i class="icon-search"></i></button>
                            </div>
                        </td>

                    </tr>
                </table>
            </form>
            <hr> 

            <div id="contenedorPrincipal" >

                <div id="MENSAJE" style="display: none"></div>
                <div id="WORK" style="display: none"></div>

            </div>

        </div>       



    </body>

</html>
