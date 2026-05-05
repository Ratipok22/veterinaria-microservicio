package com.duoc.backend.Mascotas;

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
    public Mascotas obtenerMascotaPorId(@PathVariable Long id) {
        return mascotasService.obtenerMascotaPorId(id);
    }

    @PostMapping
    public Mascotas guardarMascota(@RequestBody Mascotas mascota) {
        return mascotasService.guardarMascota(mascota);
    }

    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable Long id) {
        mascotasService.eliminarMascota(id);
    }

    @PutMapping("/mascotas/{id}")
    public Mascotas actualizarMascota(@PathVariable Long id, @RequestBody Mascotas mascota) {
        return mascotasService.actualizarMascota(id, mascota);
    }
}