/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Categoria;
import org.back.hibernate.model.Producto;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionProductosEjb extends DAO implements GestionProductosEjbLocal {

    public Producto crearProducto(Producto producto) throws Exception{
       try {
            begin();
            getSession().save(producto);
            commit();
            DAO.close();
            return producto;
        } catch (HibernateException e) {
            throw new Exception("Error al crear producto",e);
        }
    }
    
    @Override
    public List<Producto> listarTodosProductos(int limite) throws Exception {
        List<Producto> listaTodosProductos = null;
        try {
            begin();
            Query query = getSession().createQuery("FROM Producto p ");
            query.setMaxResults(limite);
            listaTodosProductos = query.list();
            DAO.close();
            return listaTodosProductos;
        } catch (HibernateException e) {
            throw new Exception("Error al listar todos los productos",e);
        }
    }
    
    public List<Producto> listarProductosPorCategoria(int idCategoria, int limite) throws Exception{
        List<Producto> listaPoductosPorCategoria = null;
        try {
            begin();
            Query query = getSession().createQuery("FROM Producto p WHERE p.categoriaIdCategoria = :idCategoria").setParameter("idCategoria", idCategoria);
            query.setMaxResults(limite);
            listaPoductosPorCategoria = query.list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar las categorias",e);
        }
        
        return listaPoductosPorCategoria;
    }

    public Producto buscarProductoPorId (int idProducto) throws Exception{
        Producto producto = null;
        try {
            begin();
            producto = (Producto)getSession().createQuery("FROM Producto p WHERE p.idproducto = :idProducto").setParameter("idProducto", +idProducto).uniqueResult();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar producto "+idProducto);
        }
        
        return producto; 
    }
    
    public List<Producto> buscarProductoPorNombre (String nombreProducto) throws Exception{
        List<Producto> listaProductos = null;
        try {
            begin();
            listaProductos = getSession().createQuery("FROM Producto p WHERE p.nombreProducto LIKE :nombreProducto").setParameter("nombreProducto", "%"+nombreProducto+"%").list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar producto "+nombreProducto);
        }
        
        return listaProductos; 
    }

   
    public Producto guardarProducto(Producto producto) throws Exception{
        Producto productoActualizado = null;
        try {
            begin();
            getSession().update(producto);
            commit();
            DAO.close();
            productoActualizado = buscarProductoPorId(producto.getIdproducto());
        } catch (HibernateException e) {
            throw new Exception("Error al guardar producto "+producto.getIdproducto());
        }
        
        return productoActualizado;
    }

}
