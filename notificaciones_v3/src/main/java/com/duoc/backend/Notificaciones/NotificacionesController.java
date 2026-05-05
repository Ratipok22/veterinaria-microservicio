package com.duoc.backend.Notificaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Notificaciones obtenerNotificacionPorId(@PathVariable Long id) {
        return notificacionesService.obtenerNotificacionPorId(id);
    }

    @PostMapping
    public Notificaciones guardarNotificacion(@RequestBody Notificaciones notificacion) {
        return notificacionesService.guardarNotificacion(notificacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarNotificacion(@PathVariable Long id) {
        notificacionesService.eliminarNotificacion(id);
    }

    @PutMapping("/{id}")
    public Notificaciones actualizarNotificacion(@PathVariable Long id, @RequestBody Notificaciones notificacion) {
        return notificacionesService.actualizarNotificacion(id, notificacion);
    }
}