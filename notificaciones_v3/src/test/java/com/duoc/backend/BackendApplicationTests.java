package com.duoc.backend;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.duoc.backend.Notificaciones.Notificaciones;
import com.duoc.backend.Notificaciones.Notificaciones.Estado;
import com.duoc.backend.Notificaciones.Notificaciones.Tipo;
import com.duoc.backend.Notificaciones.NotificacionesRepository;
import com.duoc.backend.Notificaciones.NotificacionesService;

@ExtendWith(MockitoExtension.class)
class BackendApplicationTests {

    @Mock
    private NotificacionesRepository notificacionesRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NotificacionesService notificacionesService;

    @Test
    void guardarNotificacionTest() {

        // Mock microservicio usuarios
        when(restTemplate.getForObject(anyString(), eq(Object.class)))
                .thenReturn(new Object());

        // Crear objeto de prueba
        Notificaciones notificacion = new Notificaciones();
        notificacion.setIdUsuario(1L);
        notificacion.setMensaje("Mascota encontrada");
        notificacion.setTipo(Tipo.alerta);
        notificacion.setEstado(Estado.enviada);
        notificacion.setFecha_envio(LocalDateTime.now());

        //  Mock DB
        when(notificacionesRepository.save(any(Notificaciones.class)))
                .thenReturn(notificacion);

        //  Ejecutar
        Notificaciones resultado = notificacionesService.guardarNotificacion(notificacion);

        // Validaciones
        assertNotNull(resultado);
        assertEquals("alerta", resultado.getTipo());

        //  Verificaciones
        verify(notificacionesRepository, times(1)).save(any(Notificaciones.class));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Object.class));
    }
}