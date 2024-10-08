package com.edu.umg.extraerproperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CargarPropiedades {
    private Properties properties;

    // Constructor que carga las propiedades
    public CargarPropiedades() {
        properties = new Properties();
        loadProperties(); // Llama al método para cargar las propiedades
    }

    
    // Método para cargar las propiedades desde el archivo
    private void loadProperties() {
        String tomcatBase = System.getProperty("catalina.base");
        
        // Ruta del archivo de propiedades usando catalina.base
        String propertiesFilePath = tomcatBase + "\\conf\\bd-config.cfg"; // Cambiar a .cfg
        System.out.println("Ruta del archivo de propiedades: " + propertiesFilePath); // Verifica la ruta

        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("No se pudieron cargar las propiedades. Verifica la ruta.");
            e.printStackTrace();
        }
    }

    // Métodos para obtener valores específicos de las propiedades
    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    // Método para obtener un valor específico por clave
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}