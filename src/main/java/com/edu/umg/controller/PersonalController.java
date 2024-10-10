package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Personal;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.TimeZone;

public class PersonalController {

    // Obtener todos los registros de Personal
    public List<Personal> getAllPersonal() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Personal> personalList = null;
        try {
            personalList = session.createQuery("FROM Personal", Personal.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return personalList;
    }
    
        // Buscar registros de Personal por coincidencia parcial de nombre
    public List<Personal> getPersonalByNombreParcial(String nombreParcial) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Personal> personalList = null;
        try {
            String hql = "FROM Personal WHERE nombre LIKE :nombreParcial";
            personalList = session.createQuery(hql, Personal.class)
                    .setParameter("nombreParcial", nombreParcial + "%") // Coincidencia parcial con LIKE
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return personalList;
    }


    // Obtener un registro de Personal por ID
    public Personal getPersonalById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Personal personal = null;
        try {
            personal = session.get(Personal.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return personal;
    }

    public void createPersonal(Personal personal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Formatear la fecha de ingreso
            Date fechaIngreso = personal.getFecha_ingreso();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaIngreso);
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                personal.setFecha_ingreso(calendar.getTime());

            session.save(personal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Actualizar un registro de Personal existente
    public void updatePersonal(Personal personal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Formatear la fecha de ingreso
            Date fechaIngreso = personal.getFecha_ingreso();
            if (fechaIngreso != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaIngreso);
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                personal.setFecha_ingreso(calendar.getTime());
            }

            session.update(personal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
