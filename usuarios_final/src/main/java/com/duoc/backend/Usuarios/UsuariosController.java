package com.duoc.backend.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public List<Usuarios> obtenerUsuarios() {
        return (List<Usuarios>) usuariosService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuarios usuario = usuariosService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuarios> guardarUsuario(@Valid @RequestBody Usuarios usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.guardarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuariosService.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuarios actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuarios usuario) {
        return usuariosService.actualizarUsuario(id, usuario);
    }
}
