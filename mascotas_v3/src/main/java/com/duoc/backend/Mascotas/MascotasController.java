package com.duoc.backend.Mascotas;

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
@RequestMapping("/mascotas")
@CrossOrigin(origins = "*")
public class MascotasController {

    @Autowired
    private MascotasService mascotasService;

    @GetMapping
    public List<Mascotas> obtenerMascotas() {
        return (List<Mascotas>) mascotasService.obtenerMascotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascotas> obtenerMascotaPorId(@PathVariable Long id) {
        Mascotas mascotas = mascotasService.obtenerMascotaPorId(id);
        if (mascotas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mascotas);
    }

    @PostMapping
    public ResponseEntity<Mascotas> guardarMascota(@Valid @RequestBody Mascotas mascota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotasService.guardarMascota(mascota));
    }

    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
    }

    @PutMapping("/{id}")
    public Mascotas actualizarMascota(@PathVariable Long id,@Valid @RequestBody Mascotas mascota) {
        return mascotasService.actualizarMascota(id, mascota);
    }
}