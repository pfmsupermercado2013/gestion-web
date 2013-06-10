<!DOCTYPE html>
<c:set var="usuario" value="${usuario}" scope="session" />
<c:set var="listaSupermercados" value="${listaSupermercados}" scope="session" />
<div class="navbar blue blue2 navbar-fixed-top">
        <div class="navbar-inner">
                <div class="container-fluid">
                        <a class="brand pull-left"></i>Backend</a>
                        <ul class="nav pull-left">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Supermercado
                                                <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionSupermercado?cmd=nuevo-supermercado">Nuevo supermercado</a></li>
                                                </c:if>
                                                <li><a href="GestionSupermercado?cmd=gestion-supermercado">Gestionar supermercados</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Productos
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="nuevo_producto.jsp">Nuevo producto</a></li>
                                                </c:if>
                                                <li><a href="#">Gestionar productos</a></li>
                                        </ul>
                                </li>
                                <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                Empleados
                                                <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                                <c:if test="${usuario.rol == 'pas'}" >
                                                <li><a href="GestionEmpleados?cmd=nuevo-empleado">Nuevo empleado</a></li>
                                                </c:if>
                                                <li><a href="GestionEmpleados?cmd=gestion-empleados">Gestionar empleados</a></li>
                                        </ul>
                                </li>
                                 <li class="dropdown">
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
                                <li class="dropdown">
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
                                                <li><a href="GestionEmpleados?cmd=editar-empleado&idEmpleado=${empleado.idempleado}">Modificar mis datos</a></li>
                                                <li class="divider"></li>
                                                <li><a href="login?cmd=logout">Salir</a></li>
                                        </ul>
                                </li>
                        </ul>
                </div>
        </div>
</div>

