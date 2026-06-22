package com.duoc.backend.Difusion;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "difusion")
public class Difusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDifusion")
    private Long idDifusion;

    @Column(name = "idReporte", nullable = false)
    private Long idReporte; //mascota

    private String plataforma;

    public enum Estado {publicado,pendiente,error};

    @Column(name = "url_publicacion")
    private String urlPublicacion;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Difusion() {}

    public Long getIdDifusion() {
        return idDifusion;
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getUrlPublicacion() {
        return urlPublicacion;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setIdDifusion(Long idDifusion) {
        this.idDifusion = idDifusion;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setUrlPublicacion(String urlPublicacion) {
        this.urlPublicacion = urlPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}