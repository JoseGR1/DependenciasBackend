
package com.edu.umg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jose5
 */
@Entity
@Table(name = "Puesto")
public class Puesto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_puesto;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    // Constructor vacío
    public Puesto() {}

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}