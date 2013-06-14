<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="usuario" value="${usuario}" scope="session" />
<c:set var="listaSupermercados" value="${listaSupermercados}" scope="session" />
<c:set var="menu"   value="${menu}"  scope="session"/>
<c:set var="menu_1" value="dropdown" scope="session"/>
<c:set var="menu_2" value="dropdown" scope="session"/>
<c:set var="menu_3" value="dropdown" scope="session"/>
<c:set var="menu_4" value="dropdown" scope="session"/>
<c:set var="menu_5" value="dropdown" scope="session"/>
<c:set var="menu_6" value="dropdown" scope="session"/>
<c:set var="menu_7" value="dropdown" scope="session"/>
<c:choose>
    <c:when test="${menu == '1'}" >
        <c:set var="menu_1" value="dropdown active" scope="session"/>
    </c:when>
    <c:when test="${menu == '2'}" >
       <c:set var="menu_2" value="dropdown active" scope="session"/> 
    </c:when>
    <c:when test="${menu == '3'}" >
       <c:set var="menu_3" value="dropdown active" scope="session"/>
    </c:when>
    <c:when test="${menu == '4'}" >
       <c:set var="menu_4" value="dropdown active" scope="session"/>
    </c:when>
    <c:when test="${menu == '5'}" >
       <c:set var="menu_5" value="dropdown active" scope="session"/>
    </c:when>
    <c:when test="${menu == '6'}" >
       <c:set var="menu_6" value="dropdown active" scope="session"/>
    </c:when>
    <c:when test="${menu == '7'}" >
       <c:set var="menu_7" value="dropdown active" scope="session"/>
    </c:when>
</c:choose>
<div class="navbar blue blue2 navbar-fixed-top">
        <div class="navbar-inner">
                <div class="container-fluid">
                        <a class="brand pull-left"></i>Backend</a>
                        <ul class="nav pull-left">
                                <li class="${menu_1}">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Supermercado
                                                <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionSupermercado?cmd=nuevo-supermercado&menu=1">Nuevo supermercado</a></li>
                                                </c:if>
                                                <li><a href="GestionSupermercado?cmd=gestion-supermercado&menu=1">Gestionar supermercados</a></li>
                                    </ul>
                                </li>
                                <li class="${menu_2}">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Empleados
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionEmpleados?cmd=nuevo-empleado&menu=2">Nuevo empleado</a></li>
                                                </c:if>
                                                <li><a href="GestionEmpleados?cmd=gestion-empleados&menu=2">Gestionar empleados</a></li>
                                        </ul>
                                </li>
                                <li class="${menu_3}">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Estanterías
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionEstanterias?cmd=nueva-estanteria&menu=3">Nueva estantería</a></li>
                                                </c:if>
                                                <li><a href="GestionEstanterias?cmd=gestion-estanterias&menu=3">Gestionar estanterias</a></li>
                                        </ul>
                                </li>
                                <li class="${menu_4}">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Categorias
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionCategorias?cmd=nueva-categoria&menu=4">Nueva categoria</a></li>
                                                </c:if>
                                                <li><a href="GestionCategorias?cmd=gestion-categorias&menu=4">Gestionar categorias</a></li>
                                        </ul>
                                </li>
                                <li class="${menu_5}">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Productos
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionProductos?cmd=nuevo-producto&menu=5">Nuevo producto</a></li>
                                                </c:if>
                                                <li><a href="GestionProductos?cmd=gestion-productos&menu=5">Gestionar productos</a></li>
                                        </ul>
                                </li>
                                 <li class="${menu_6}">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Subasta
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="#">Nueva subasta</a></li>
                                                </c:if>
                                                <li><a href="#">Gestionar subastas</a></li>
                                        </ul>
                                </li>
                                <li class="${menu_7}">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Proveedores
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="nuevo_proveedor.jsp">Nuevo proveedor</a></li>
                                                </c:if>
                                                <li><a href="#">Gestionar proveedores</a></li>
                                        </ul>
                                </li>
                        </ul>
                        <ul class="nav pull-right">
                                <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Cuenta
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <li><a href="GestionEmpleados?cmd=editar-empleado">Modificar mis datos</a></li>
                                                <li class="divider"></li>
                                                <li><a href="login?cmd=logout">Salir</a></li>
                                        </ul>
                                </li>
                        </ul>
                </div>
        </div>
</div>

