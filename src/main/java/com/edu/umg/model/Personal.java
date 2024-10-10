package com.edu.umg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_personal;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "telefono", nullable = true)
    private String telefono;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ingreso", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Formato de fecha
    private Date fecha_ingreso;

    @ManyToOne
    @JoinColumn(name = "id_puesto", nullable = false)
    private Puesto puesto;

    // Constructor vacío
    public Personal() {
    }

    // Getters y Setters
    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
}
