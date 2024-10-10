package com.edu.umg.controller;


import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Puesto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PuestoController {

    // Obtener todos los puestos
    public List<Puesto> getAllPuestos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Puesto> puestos = null;
        try {
            puestos = session.createQuery("FROM Puesto", Puesto.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return puestos;
    }

    // Obtener puesto por ID
    public Puesto getPuestoById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Puesto puesto = null;
        try {
            puesto = session.get(Puesto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return puesto;
    }

    // Crear un nuevo puesto
    public void createPuesto(Puesto puesto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(puesto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Actualizar un puesto existente
    public void updatePuesto(Puesto puesto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(puesto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
