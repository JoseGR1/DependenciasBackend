package com.edu.umg.config;

/**
 *
 * @author jose5
 */

import com.edu.umg.extraerproperties.CargarPropiedades;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        try {
            Configuration configuration = new Configuration();
            CargarPropiedades cargarPropiedades = new CargarPropiedades();
            
            // Configuración de Hibernate
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", cargarPropiedades.getUrl());
            configuration.setProperty("hibernate.connection.username",cargarPropiedades.getUser());
            configuration.setProperty("hibernate.connection.password", cargarPropiedades.getPassword());
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            // Añadir la clase de entidad
            configuration.addAnnotatedClass(com.edu.umg.model.Usuario.class);
            configuration.addAnnotatedClass(com.edu.umg.model.Autor.class);
            configuration.addAnnotatedClass(com.edu.umg.model.Tipo.class);
            configuration.addAnnotatedClass(com.edu.umg.model.Libro.class);
            configuration.addAnnotatedClass(com.edu.umg.model.Prestamo.class);
            configuration.addAnnotatedClass(com.edu.umg.model.Puesto.class);
            configuration.addAnnotatedClass(com.edu.umg.model.Personal.class);
            
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    return sessionFactory;
}


    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

