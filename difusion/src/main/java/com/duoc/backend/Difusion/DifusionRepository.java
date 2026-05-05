package com.duoc.backend.Difusion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DifusionRepository extends JpaRepository<Difusion, Long> {

    List<Difusion> findByIdReporte(Long id_reporte);

    List<Difusion> findByEstado(String estado);

    List<Difusion> findByPlataforma(String plataforma);
}