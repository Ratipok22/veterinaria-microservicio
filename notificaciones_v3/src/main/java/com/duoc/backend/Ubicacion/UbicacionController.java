package com.duoc.backend.Ubicacion;

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
@RequestMapping("/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public List<Ubicacion> obtenerUbicaciones() {
        return (List<Ubicacion>) ubicacionService.obtenerUbicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> obtenerUbicacionPorId(@PathVariable Long id) {
        Ubicacion ubicacion = ubicacionService.obtenerUbicacionPorId(id);
        if (ubicacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ubicacion);
    }

    @PostMapping
    public ResponseEntity<Ubicacion> guardarUbicacion(@Valid @RequestBody Ubicacion ubicacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ubicacionService.guardarUbicacion(ubicacion));
    }

    @DeleteMapping("/{id}")
    public void eliminarUbicacion(@PathVariable Long id) {
        ubicacionService.eliminarUbicacion(id);
    }

    @PutMapping("/{id}")
    public Ubicacion actualizarUbicacion(@PathVariable Long id,@Valid @RequestBody Ubicacion ubicacion) {
        return ubicacionService.actualizarUbicacion(id, ubicacion);
    }
}