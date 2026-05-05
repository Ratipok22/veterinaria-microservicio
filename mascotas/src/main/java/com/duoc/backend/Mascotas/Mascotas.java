package com.duoc.backend.Mascotas;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mascotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reporte;

    private String tipo_reporte; // perdido o encontrado

    private String tipo_mascota; // perro, gato, etc.
    private String raza;
    private String color;
    private String tamaño;

    private String descripcion;

    private String foto_url;

    private String estado; // activo, resuelto

    private LocalDate fecha_reporte;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario; 

    public Long getId_reporte() {
        return id_reporte;
    }

    public String getTipo_reporte() {
        return tipo_reporte;
    }

    public String getTipo_mascota() {
        return tipo_mascota;
    }

    public String getRaza() {
        return raza;
    }

    public String getColor() {
        return color;
    }

    public String getTamaño() {
        return tamaño;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFecha_reporte() {
        return fecha_reporte;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setId_reporte(Long id_reporte) {
        this.id_reporte = id_reporte;
    }

    public void setTipo_reporte(String tipo_reporte) {
        this.tipo_reporte = tipo_reporte;
    }

    public void setTipo_mascota(String tipo_mascota) {
        this.tipo_mascota = tipo_mascota;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha_reporte(LocalDate fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


}
