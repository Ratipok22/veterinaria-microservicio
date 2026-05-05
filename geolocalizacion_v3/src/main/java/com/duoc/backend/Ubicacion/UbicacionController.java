package com.duoc.backend.Ubicacion;

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
    public Ubicacion obtenerUbicacionPorId(@PathVariable Long id) {
        return ubicacionService.obtenerUbicacionPorId(id);
    }

    @PostMapping
    public Ubicacion guardarUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.guardarUbicacion(ubicacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarUbicacion(@PathVariable Long id) {
        ubicacionService.eliminarUbicacion(id);
    }

    @PutMapping("/{id}")
    public Ubicacion actualizarUbicacion(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        return ubicacionService.actualizarUbicacion(id, ubicacion);
    }
}