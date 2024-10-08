package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioController {

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> usuarios = null;
        try {
            usuarios = session.createQuery("FROM Usuario", Usuario.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuarios;
    }
    
    // Buscar usuarios cuyo nombre contiene una cadena
    public List<Usuario> getUsuarioByNombreParcial(String nombreParcial) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> usuarios = null;
        try {
            String hql = "FROM Usuario WHERE nombre LIKE :nombreParcial";
            usuarios = session.createQuery(hql, Usuario.class)
                    .setParameter("nombreParcial", nombreParcial + "%")
                    .list();  // Coincidencia parcial con LIKE
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuarios;
    }


    // Obtener usuario por ID
    public Usuario getUsuarioById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario = null;
        try {
            usuario = session.get(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    // Crear un nuevo usuario
    public void createUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Actualizar un usuario existente
    public void updateUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
