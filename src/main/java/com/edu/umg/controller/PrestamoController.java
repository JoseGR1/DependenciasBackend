package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Prestamo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamoController {

    // Obtener todos los préstamos
    public List<Prestamo> getAllPrestamos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Prestamo> prestamos = null;
        try {
            prestamos = session.createQuery("FROM Prestamo", Prestamo.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return prestamos;
    }

    // Obtener préstamo por ID
    public Prestamo getPrestamoById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Prestamo prestamo = null;
        try {
            prestamo = session.get(Prestamo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return prestamo;
    }

    // Crear un nuevo préstamo
    public void createPrestamo(Prestamo prestamo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Actualizar un préstamo existente
    public void updatePrestamo(Prestamo prestamo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
