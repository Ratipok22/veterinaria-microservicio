package com.duoc.backend.Notificaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    @GetMapping
    public List<Notificaciones> obtenerNotificaciones() {
        return (List<Notificaciones>) notificacionesService.obtenerNotificaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerNotificacionPorId(@PathVariable Long id) {
        Notificaciones notificaciones = notificacionesService.obtenerNotificacionPorId(id);
        if (notificaciones == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificaciones);
    }

    @PostMapping
    public ResponseEntity<Notificaciones> guardarUsuario(@Valid @RequestBody Notificaciones notificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionesService.guardarNotificacion(notificacion));
    }

    @DeleteMapping("/{id}")
    public void eliminarNotificacion(@PathVariable Long id) {
        notificacionesService.eliminarNotificacion(id);
    }

    @PutMapping("/{id}")
    public Notificaciones actualizarNotificacion(@PathVariable Long id,@Valid @RequestBody Notificaciones notificacion) {
        return notificacionesService.actualizarNotificacion(id, notificacion);
    }
}