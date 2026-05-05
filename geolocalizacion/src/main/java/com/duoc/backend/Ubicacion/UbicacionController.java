package com.duoc.backend.Ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    // 🔹 GET: obtener todas
    @GetMapping
    public List<Ubicacion> obtenerUbicaciones() {
        return (List<Ubicacion>) ubicacionService.obtenerUbicaciones();
    }

    // 🔹 GET: obtener por ID
    @GetMapping("/{id}")
    public Ubicacion obtenerUbicacionPorId(@PathVariable Long id) {
        return ubicacionService.obtenerUbicacionPorId(id);
    }

    // 🔹 POST: guardar
    @PostMapping
    public Ubicacion guardarUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.guardarUbicacion(ubicacion);
    }

    // 🔹 DELETE: eliminar
    @DeleteMapping("/{id}")
    public void eliminarUbicacion(@PathVariable Long id) {
        ubicacionService.eliminarUbicacion(id);
    }
}