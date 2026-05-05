package com.duoc.backend.Notificaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Iterable<Notificaciones> obtenerNotificaciones() {
        return notificacionesRepository.findAll();
    }

    public Notificaciones obtenerNotificacionPorId(Long id) {
        return notificacionesRepository.findById(id).orElse(null);
    }

    public Notificaciones guardarNotificacion(Notificaciones notificacion) {

        String USUARIOS_URL = "http://localhost:8180/usuarios/" + notificacion.getIdUsuario();

        //  Validación obligatoria
        if (notificacion.getIdUsuario() == null) {
            throw new RuntimeException("El idUsuario es obligatorio");
        }

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(USUARIOS_URL, Object.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("El usuario no existe");
            }

            if (response.getBody() == null) {
                throw new RuntimeException("El usuario no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("El usuario no existe");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con microservicio de usuarios");
        }

        return notificacionesRepository.save(notificacion);
    }

    public void eliminarNotificacion(Long id) {
        notificacionesRepository.deleteById(id);
    }

    public Notificaciones actualizarNotificacion(Long id, Notificaciones notificacionActualizada) {

        Notificaciones existente = notificacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        if (notificacionActualizada.getIdUsuario() == null) {
            throw new RuntimeException("El idUsuario es obligatorio");
        }

        String USUARIOS_URL = "http://localhost:8180/usuarios/" + notificacionActualizada.getIdUsuario();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(USUARIOS_URL, Object.class);

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new RuntimeException("El usuario no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("El usuario no existe");
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con usuarios");
        }

        existente.setIdUsuario(notificacionActualizada.getIdUsuario());
        existente.setMensaje(notificacionActualizada.getMensaje());
        existente.setTipo(notificacionActualizada.getTipo());
        existente.setEstado(notificacionActualizada.getEstado());
        existente.setFecha_envio(notificacionActualizada.getFecha_envio());

        return notificacionesRepository.save(existente);
    }
}