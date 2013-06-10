/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionEmpleadosEjb extends DAO implements GestionEmpleadosEjbLocal {
        
    @Override
    public Empleado crearEmpleado(Empleado empleado) throws Exception {
        try{
            begin();
            getSession().save(empleado);
            commit();
            DAO.close();
        return empleado; 
        } catch (HibernateException e) {
            throw new Exception("Error al crear el empleado",e);
        }
    }

    @Override
    public Empleado editarEmpleado(Empleado empleado) throws Exception {
        Empleado empleadoActualizado;
        try {
            begin();
            getSession().update(empleado);
            commit();
            DAO.close();
            empleadoActualizado = buscarEmpleado(empleado.getIdempleado());
        } catch (HibernateException e) {
            throw new Exception("Error al guardar los cambios del empleado "+empleado.getIdempleado());
        }
        return empleadoActualizado;
    }

    @Override
    public Empleado validarIdentidadEmpleado(String idEmpleado, String password) throws Exception{
        Empleado empleado = null;
        try{
            begin();
            // Buscamos al empleado de acuerdo a su identificador y password
            empleado = (Empleado)getSession().createQuery("FROM Empleado e WHERE e.nif = :nif AND e.activo = 'S'").setParameter("nif", idEmpleado).uniqueResult();
            
            if(empleado != null){
                // Si las contraseñas no son iguales devolvemos nulo
                if(!empleado.getPassword().equals(password)){
                    empleado = null;
                }
            }
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al validar identidad del empleado",e);
        }
        return empleado;
    }

    @Override
    public List<Empleado> listarEmpleados(int limite) throws Exception{
        List<Empleado> listaEmpleados = null;
        try {
            begin();
            Query query = getSession().createQuery("FROM Empleado e WHERE e.activo = :activo ORDER BY e.apellidosEmpleado").setParameter("activo", "S");
            query.setMaxResults(limite);
            listaEmpleados = query.list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar empleados.");
        }
        
        return listaEmpleados;
    }

    @Override
    public List<Empleado> paginarResultados(int limite, int offset) throws Exception{
        List<Empleado> listaEmpleados = null;
        try {
            begin();
            Query query = getSession().createQuery("FROM Empleado e WHERE e.activo = :activo ORDER BY e.apellidosEmpleado").setParameter("activo", "S");
            query.setMaxResults(limite);
            query.setFirstResult(limite * offset);
            listaEmpleados = query.list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al paginar resultados de empleados.");
        }
        
        return listaEmpleados;
    }

    @Override
    public int obtenerNumeroPaginas(int limite) throws Exception{
        long numEmpleados = 0;
        int numPaginas = 0;
        try {
            begin();
            numEmpleados = (Long) getSession().createQuery("SELECT COUNT(*) FROM Empleado e WHERE e.activo = :activo").setParameter("activo", "S").uniqueResult();
            DAO.close();
            numPaginas = (int) Math.ceil(numEmpleados / limite);
        } catch (HibernateException e) {
            throw new Exception("Error al obtener número total de empleados.");
        }
        return numPaginas;
    }

    @Override
    public Empleado buscarEmpleado(int idEmpleado) throws Exception {
        Empleado empleado = null;
        try {
            begin();
            empleado = (Empleado)getSession().createQuery("FROM Empleado e WHERE e.idempleado = :idEmpleado AND e.activo = 'S'").setParameter("idEmpleado", idEmpleado).uniqueResult();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar empleados.");
        }
         return empleado;
    }

    @Override
    public boolean inactivarEmpleado(int idEmpleado) throws Exception {
        boolean inactivo = false;
        Empleado empleado = null;
        try {
            empleado = buscarEmpleado(idEmpleado);
            if(empleado != null){
                empleado.setActivo("N");
                begin();
                getSession().update(empleado);
                commit();
                DAO.close();
                inactivo = true;
            }
        } catch (HibernateException e) {
            throw new Exception("Error al inactivar empleado.");
        }
        return inactivo;
    }
   
    
}
