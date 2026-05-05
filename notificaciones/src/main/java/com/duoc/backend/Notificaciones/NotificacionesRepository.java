package com.duoc.backend.Notificaciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {

    List<Notificaciones> findByIdUsuario(Long id_usuario);

    List<Notificaciones> findByEstado(String estado);

    List<Notificaciones> findByTipo(String tipo);
}