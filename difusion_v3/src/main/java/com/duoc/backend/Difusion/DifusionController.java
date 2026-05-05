package com.duoc.backend.Difusion;

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
    public Difusion obtenerDifusionPorId(@PathVariable Long id) {
        return difusionService.obtenerDifusionPorId(id);
    }

    @PostMapping
    public Difusion guardarDifusion(@RequestBody Difusion difusion) {
        return difusionService.guardarDifusion(difusion);
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