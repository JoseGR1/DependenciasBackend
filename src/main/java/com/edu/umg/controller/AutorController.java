
package com.edu.umg.controller;

import com.edu.umg.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.edu.umg.model.Autor;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AutorController {

    // Obtener todos los autores
    public List<Autor> getAllAutores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Autor> autores = null;
        try {
            autores = session.createQuery("FROM Autor", Autor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return autores;
    }
    
    // Buscar autores cuyo nombre contiene una cadena
    public List<Autor> getAutorByNombreParcial(String nombreParcial) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Autor> autores = null;
        try {
            String hql = "FROM Autor WHERE nombre LIKE :nombreParcial";
            autores = session.createQuery(hql, Autor.class)
                    .setParameter("nombreParcial", nombreParcial + "%")
                    .list();  // Coincidencia parcial con LIKE
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return autores;
    }

    // Obtener autor por ID
    public Autor getAutorById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Autor autor = null;
        try {
            autor = session.get(Autor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return autor;
    }

public void createAutor(Autor autor) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        Date fechaRegistro = autor.getFecha_registro();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaRegistro);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        autor.setFecha_registro(calendar.getTime());
        session.save(autor);
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}

  // Actualizar un autor existente
public void updateAutor(Autor autor) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        
        Date fechaRegistro = autor.getFecha_registro();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaRegistro);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        autor.setFecha_registro(calendar.getTime());

        session.update(autor);
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}

}

