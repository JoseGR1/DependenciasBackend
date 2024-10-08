package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import com.edu.umg.model.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroController {

    // Obtener todos los libros
    public List<Libro> getAllLibros() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Libro> libros = null;
        try {
            libros = session.createQuery("FROM Libro", Libro.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return libros;
    }
    
    // Buscar libros cuyo título contenga una cadena parcial
    public List<Libro> getLibrosByTituloParcial(String tituloParcial) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Libro> libros = null;
        try {
            String hql = "FROM Libro WHERE titulo LIKE :tituloParcial";
            libros = session.createQuery(hql, Libro.class)
                    .setParameter("tituloParcial", "%" + tituloParcial + "%") // Coincidencia parcial con LIKE
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return libros;
    }

    // Obtener libro por ID
    public Libro getLibroById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Libro libro = null;
        try {
            libro = session.get(Libro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return libro;
    }

    // Crear un nuevo libro
    public void createLibro(Libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Actualizar un libro existente
    public void updateLibro(Libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
