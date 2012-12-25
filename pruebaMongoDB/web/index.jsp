<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/libs/jquery-1.8.1.min.js"></script>
        <script type="text/javascript" src="js/controller/loginController.js"></script>
        <script type="text/javascript" src="js/model/loginModel.js"></script>

        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">        

        <title>Login de Usuario</title>
    </head>
    <body style="background:#999">

        <div class="container">
            <div style="height:200px" ></div>		
            <div class="offset3 span4  hero-unit">
                <h3 class="muted" align="center"> Ingreso </h3>
                <center>
                    <form name="form1" id="form1" method="post" action="">                  
                        <input name="txtNick" type="text"  id="txtNick" size="20" maxlength="20" placeholder="Usuario"><br>
                        <input name="txtPass" type="password"  id="txtPass" size="20" maxlength="20"  placeholder="Contraseña"><br><br>                       
                        <input name="btnVerificar" type="button"  id="btnVerificar" value="Log In" class="btn btn-primary" >
                    </form>
                </center>    
                <div  id="respuestaVerificacion"  align="center" class="alert alert-error"></div>

            </div>
        </div>
    </body>
</html>
