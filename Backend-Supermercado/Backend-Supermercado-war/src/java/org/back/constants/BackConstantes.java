package org.back.constants;


public class BackConstantes {
    
    /* Gestión de Empleados */
    public static final String GESTION_EMPLEADOS  = "gestion-empleados";
    public static final String NUEVO_EMPLEADO     = "nuevo-empleado";
    public static final String CREAR_EMPLEADO     = "crear-empleado";
    public static final String EDITAR_EMPLEADO    = "editar-empleado";
    public static final String BORRAR_EMPLEADO    = "borrar-empleado";
    public static final String VER_EMPLEADO       = "ver-empleado";
    public static final String LISTAR_EMPLEADOS   = "listar-empleados";
    public static final String GUARDAR_EMPLEADO   = "guardar-empleado";
    public static final String EDITAR_USUARIO     = "editar-usuario";
    public static final String GUARDAR_USUARIO    = "guardar-usuario";
    
    /* Gestión de Supermercado */
    public static final String GESTION_SUPERMERCADO = "gestion-supermercado";
    public static final String NUEVO_SUPERMERCADO   = "nuevo-supermercado";
    public static final String CREAR_SUPERMERCADO   = "crear-supermercado";
    public static final String EDITAR_SUPERMERCADO  = "editar-supermercado";
    public static final String BORRAR_SUPERMERCADO  = "borrar-supermercado";
    public static final String VER_SUPERMERCADO     = "ver-supermercado";
    public static final String LISTAR_SUPERMERCADO  = "listar-supermercados";
    public static final String GUARDAR_SUPERMERCADO = "guardar-supermercado";
    
    /* Gestión de Productos */
    public static final String GESTION_PRODUCTOS = "gestion-productos";
    public static final String NUEVO_PRODUCTO    = "nuevo-producto";
    public static final String CREAR_PRODUCTO    = "crear-producto";
    public static final String EDITAR_PRODUCTO   = "editar-producto";
    public static final String BORRAR_PRODUCTO   = "borrar-producto";
    public static final String VER_PRODUCTO      = "ver-producto";
    public static final String LISTAR_PRODUCTOS  = "listar-productos";
    public static final String GUARDAR_PRODUCTO  = "guardar-producto";
    public static final String UBICAR_PRODUCTO   = "ubicar-producto";
    
    /* Gestión de Categorias */
    public static final String GESTION_CATEGORIAS = "gestion-categorias";
    public static final String NUEVA_CATEGORIA    = "nueva-categoria";
    public static final String CREAR_CATEGORIA    = "crear-categoria";
    public static final String EDITAR_CATEGORIA   = "editar-categoria";
    public static final String BORRAR_CATEGORIA   = "borrar-categoria";
    public static final String VER_CATEGORIA      = "ver-categoria";
    public static final String LISTAR_CATEGORIAS  = "listar-categorias";
    public static final String GUARDAR_CATEGORIA  = "guardar-categoria";
    
    /* Gestión de Estanterias */
    public static final String GESTION_ESTANTERIAS_SUPERMERCADO = "gestion-estanterias-super";
    public static final String GESTION_ESTANTERIAS = "gestion-estanterias";
    public static final String NUEVA_ESTANTERIA    = "nueva-estanteria";
    public static final String CREAR_ESTANTERIA    = "crear-estanteria";
    public static final String EDITAR_ESTANTERIA   = "editar-estanteria";
    public static final String BORRAR_ESTANTERIA   = "borrar-estanteria";
    public static final String VER_ESTANTERIA      = "ver-estanteria";
    public static final String LISTAR_ESTANTERIAS  = "listar-estanterias";
    public static final String GUARDAR_ESTANTERIA  = "guardar-estanteria";
    public static final Integer ID_PROD_FICTICIO   = -1;
    public static final Integer ID_CATEG_FICTICIA  = -1;
    
    /* Gestión de Ubicación Producto */
    public static final String LISTAR_ESTANTERIAS_SUPER = "listar-estanterias-super";
    public static final String CREAR_UBICACION_PRODUCTO = "crear-ubicacion-producto";
    public static final String VER_UBICACION_PRODUCTO   = "ver-ubicacion-producto";
    
    /* Roles */
    public static final String ROL_SUPER  = "pas";
    public static final String ROL_NORMAL = "normal";
    
    /* Comandos Login*/
    public static final String LOGIN_ADMIN  = "login-admin";
    public static final String LOGIN_NORMAL = "login-normal";
    public static final String LOGOUT       = "logout";
    
    /* Paginación de resultados */
    public static final int NUM_REG_MAX = 20;
    
    /* Ruta almacenamiento Archivos subidos*/
    public static final String RUTA_ARCHIVOS_UPLOAD = "img/tmp/";

    /* Para la generación de la contraseña de los proveedores*/
    public static final String SALT_KEY = "716EA45X34";
    
    /* Códigos de respuesta login proveedores*/
    public static final int OK= 0;
    public static final int PROVEEDOR_NO_ACTIVADO= 1;
    public static final int BAD_PASSWORD= -1;
    public static final int PROVEEDOR_NO_ENCONTRADO= -2;
    
    
    /* Número máximo de secciones por estante */
    public static final int NUMERO_MAX_SECCIONES    = 4;
    
    /* Número máximo de productos por sección*/
    public static final int NUMERO_MAX_PROD_SECCION = 30;
    
}
