package com.duoc.backend;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.backend.Usuarios.Usuarios;
import com.duoc.backend.Usuarios.UsuariosRepository;
import com.duoc.backend.Usuarios.UsuariosService;

@ExtendWith(MockitoExtension.class)
class BackendApplicationTests {

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private UsuariosService usuariosService;

    @Test
    void guardarUsuarioTest() {

        //  Crear usuario de prueba
        Usuarios usuario = new Usuarios();
        usuario.setNombre("Juan Perez");
        usuario.setCorreo("juan@test.com");
        usuario.setPassword("123456");
        usuario.setTelefono("123456789");
        usuario.setRol("dueño");
        usuario.setFecha_registro(LocalDate.now());
        usuario.setEstado_cuenta("activo");

        //  Mock guardado en DB
        when(usuariosRepository.save(any(Usuarios.class)))
                .thenReturn(usuario);

        //  Ejecutar
        Usuarios resultado = usuariosService.guardarUsuario(usuario);

        //  Validaciones
        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        assertEquals("activo", resultado.getEstado_cuenta());

        //  Verificar interacción
        verify(usuariosRepository, times(1)).save(any(Usuarios.class));
    }
}