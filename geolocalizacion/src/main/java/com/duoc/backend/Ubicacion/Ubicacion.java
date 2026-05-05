package com.duoc.backend.Ubicacion;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ubicacion;

    @Column(name = "id_reporte", nullable = false)
    private Long idReporte;

    private Double latitud;
    private Double longitud;

    private String direccion;

    private LocalDate fecha;

    public Long getId_ubicacion() {
        return id_ubicacion;
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId_ubicacion(Long id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
