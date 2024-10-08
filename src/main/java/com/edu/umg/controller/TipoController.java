package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Tipo;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TipoController {

    // Obtener todos los tipos
    public List<Tipo> getAllTipos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tipo> tipos = null;
        try {
            tipos = session.createQuery("FROM Tipo", Tipo.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tipos;
    }

    // Obtener tipo por ID
    public Tipo getTipoById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tipo tipo = null;
        try {
            tipo = session.get(Tipo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tipo;
    }

    // Crear un nuevo tipo
    public void createTipo(Tipo tipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(tipo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Actualizar un tipo existente
    public void updateTipo(Tipo tipo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(tipo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
