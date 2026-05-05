package com.duoc.backend.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosRepository;

    @GetMapping
    public List<Usuarios> obtenerUsuarios() {
        return (List<Usuarios>) usuariosRepository.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public Usuarios obtenerUsuarioPorId(@PathVariable Long id) {
        return usuariosRepository.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuarios guardarUsuario(@RequestBody Usuarios usuario) {
        return usuariosRepository.guardarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuariosRepository.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuarios actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuario) {
        return usuariosRepository.actualizarUsuario(id, usuario);
    }
}
