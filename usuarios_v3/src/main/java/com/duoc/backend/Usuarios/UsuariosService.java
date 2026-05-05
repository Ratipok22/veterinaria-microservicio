package com.duoc.backend.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    //  Obtener todos los usuarios
    public Iterable<Usuarios> obtenerUsuarios() {
        return usuariosRepository.findAll();
    }

    //  Obtener usuario por ID
    public Usuarios obtenerUsuarioPorId(Long id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    //  Crear usuario
    public Usuarios guardarUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    //  Eliminar usuario
    public void eliminarUsuario(Long id) {
        usuariosRepository.deleteById(id);
    }

    public Usuarios actualizarUsuario(Long id, Usuarios usuarioActualizado) {

        Usuarios existente = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(usuarioActualizado.getNombre());
        existente.setCorreo(usuarioActualizado.getCorreo());
        existente.setPassword(usuarioActualizado.getPassword());
        existente.setTelefono(usuarioActualizado.getTelefono());
        existente.setRol(usuarioActualizado.getRol());
        existente.setFecha_registro(usuarioActualizado.getFecha_registro());
        existente.setEstado_cuenta(usuarioActualizado.getEstado_cuenta());

        return usuariosRepository.save(existente);
    }
}