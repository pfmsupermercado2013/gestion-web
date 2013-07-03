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
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionCategoriasEjb extends DAO implements GestionCategoriasEjbLocal {

     @Override
     public Categoria crearCategoria(Categoria categoria) throws Exception{
       try {
            begin();
            getSession().save(categoria);
            commit();
            DAO.close();
            return categoria;
        } catch (HibernateException e) {
            throw new Exception("Error al crear la categoria",e);
        }
    }
    
     @Override
    public List<Categoria> listarCategorias(int limite) throws Exception{
        List<Categoria> listaCategorias = null;
        try {
            begin();
            Query query = getSession().createQuery("FROM Categoria");
            query.setMaxResults(limite);
            listaCategorias = query.list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar las categorias",e);
        }
        
        return listaCategorias;
    }

    
     @Override
    public Categoria buscarCategoria (Integer idCategoria) throws Exception{
        Categoria categoria = null;
        try {
            begin();
            categoria = (Categoria)getSession().createQuery("FROM Categoria c WHERE c.idcategoria = :idCategoria").setParameter("idCategoria", idCategoria).uniqueResult();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al buscar la categoria "+idCategoria);
        }
        
        return categoria; 
    }

   
     @Override
    public Categoria guardarCategoria(Categoria categoria) throws Exception{
        Categoria categoriaActualizada = null;
        try {
            begin();
            getSession().update(categoria);
            commit();
            DAO.close();
            categoriaActualizada = buscarCategoria(categoria.getIdcategoria());
        } catch (HibernateException e) {
            throw new Exception("Error al guardar la categoria "+categoria.getIdcategoria());
        }
        
        return categoriaActualizada;
    }

     @Override
     public List<Categoria> listarTodasCategorias() throws Exception{
        List<Categoria> listaCategorias = null;
        try {
            begin();
            listaCategorias = getSession().createQuery("FROM Categoria").list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar todas las categorias",e);
        }
        
        return listaCategorias;
    }
     
     @Override
     public boolean eliminarCategoria(Categoria categoria) throws Exception {
        boolean categoriaBorrada = false;
        try {
            begin();
            getSession().delete(categoria);
            DAO.close();
            return categoriaBorrada = true;
        } catch (HibernateException e) {
            throw new Exception("Error al borrar categoria "+categoria.getIdcategoria());
        }
     }
}
