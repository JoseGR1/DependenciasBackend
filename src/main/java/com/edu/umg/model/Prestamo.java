package com.edu.umg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_prestamo;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Formato de fecha
    private Date fecha_prestamo;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Formato de fecha
    private Date fecha_devolucion;

    private String estado;

    // Getters y Setters
    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
