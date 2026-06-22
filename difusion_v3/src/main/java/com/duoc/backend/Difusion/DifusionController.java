package com.duoc.backend.Difusion;

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

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/difusion")
@CrossOrigin(origins = "*")
public class DifusionController {

    @Autowired
    private DifusionService difusionService;

    @GetMapping
    public List<Difusion> obtenerDifusiones() {
        return (List<Difusion>) difusionService.obtenerDifusiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Difusion> obtenerDifusionPorId(@PathVariable Long id) {
        Difusion difusion = difusionService.obtenerDifusionPorId(id);
        if (difusion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(difusion);
    }

    @PostMapping
    public ResponseEntity<Difusion> guardarDifusion(@Valid @RequestBody Difusion difusion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(difusionService.guardarDifusion(difusion));
    }

    @DeleteMapping("/{id}")
    public void eliminarDifusion(@PathVariable Long id) {
        difusionService.eliminarDifusion(id);
    }

    @PutMapping("/{id}")
    public Difusion actualizarDifusion(@PathVariable Long id, @RequestBody Difusion difusion) {
        return difusionService.actualizarDifusion(id, difusion);
}


}