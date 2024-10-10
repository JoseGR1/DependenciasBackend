package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Prestamo;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.TimeZone;

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
            
            // Formatear la fecha de préstamo
            Date fechaPrestamo = prestamo.getFecha_prestamo();
                Calendar calendar_prestamo = Calendar.getInstance();
                calendar_prestamo.setTime(fechaPrestamo);
                calendar_prestamo.setTimeZone(TimeZone.getTimeZone("UTC"));
                prestamo.setFecha_prestamo(calendar_prestamo.getTime());
            
            
            // Formatear la fecha de devolución
            Date fechaDevolucion = prestamo.getFecha_devolucion();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaDevolucion);
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                prestamo.setFecha_devolucion(calendar.getTime());
            

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
            
            // Formatear la fecha de préstamo
            Date fechaPrestamo = prestamo.getFecha_prestamo();
            if (fechaPrestamo != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaPrestamo);
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                prestamo.setFecha_prestamo(calendar.getTime());
            }
            
            // Formatear la fecha de devolución
            Date fechaDevolucion = prestamo.getFecha_devolucion();
            if (fechaDevolucion != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaDevolucion);
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                prestamo.setFecha_devolucion(calendar.getTime());
            }

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
