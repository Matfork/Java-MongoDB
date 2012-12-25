<!-- Para poder usar los eventos del menuModel para los enlaces, se debe de importar las librerias,
no se debe creer que porque esta pagina es cargada por ajax, automaticamente se asimilara a los eventos
del menu.jsp, lo hice de esa forma, y no funcionaba, asi que tuve que importar de nuevo los scripts de eventos
y ya funcionan a la perfeccion -->
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="js/controller/mostrarArticuloController.js"></script>
    <script type="text/javascript" src="js/model/mostrarArticuloModel.js"></script>
</head>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<table class="table table-hover table-striped" >
 <thead>
        <tr class="tableTitle">
            <th width="10">ID</th>
            <th width="70">Nombre</th>
            <th width="200">Descripcion</th>
            <th width="20">Precio</th>
            <th width="10">Stock</th>
            <th width="60">Accion</th>            
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listaArticulos}" var="r">
        <tr class="tableData">                           
            <td>${r.idArticulo}</td>
            <td>${r.nomArticulo}</td>
            <td>${r.descripcion}</td>
            <td>${r.precio}</td>
            <td>${r.stock}</td>
            <td>
                <a href="#" onClick="cargarUpdate('${r.idArticulo}')" >
                    <img src="imgs/edit.png" width="24" height="24" border="0">
                </a> 
                <a href="#">
                    <img src="imgs/delete.png"  onClick="eliminarFila('${r.idArticulo}')" width="24" height="24" border="0">
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>