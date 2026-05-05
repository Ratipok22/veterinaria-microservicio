package com.duoc.backend;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.duoc.backend.Mascotas.Mascotas;
import com.duoc.backend.Mascotas.MascotasRepository;
import com.duoc.backend.Mascotas.MascotasService;

@ExtendWith(MockitoExtension.class)
class BackendApplicationTests {

    @Mock
    private MascotasRepository mascotasRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MascotasService mascotasService;

    @Test
    void guardarMascotaTest() {

        when(restTemplate.getForObject(anyString(), eq(Object.class)))
                .thenReturn(new Object());

        Mascotas mascota = new Mascotas();
        mascota.setTipo_reporte("perdido");
        mascota.setTipo_mascota("perro");
        mascota.setRaza("Labrador");
        mascota.setColor("negro");
        mascota.setTamaño("grande");
        mascota.setDescripcion("Test mascota");
        mascota.setFoto_url("http://test.com");
        mascota.setEstado("activo");
        mascota.setFecha_reporte(LocalDate.now());
        mascota.setIdUsuario(1L);

        when(mascotasRepository.save(any(Mascotas.class)))
                .thenReturn(mascota);

        Mascotas resultado = mascotasService.guardarMascota(mascota);

        assertNotNull(resultado);
        assertEquals("perro", resultado.getTipo_mascota());

        verify(mascotasRepository, times(1)).save(any(Mascotas.class));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Object.class));
    }
}