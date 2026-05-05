package com.duoc.backend;

import java.time.LocalDate;

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

import com.duoc.backend.Ubicacion.Ubicacion;
import com.duoc.backend.Ubicacion.UbicacionRepository;
import com.duoc.backend.Ubicacion.UbicacionService;

@ExtendWith(MockitoExtension.class)
class BackendApplicationTests {

    @Mock
    private UbicacionRepository ubicacionRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UbicacionService ubicacionService;

    @Test
    void guardarUbicacionTest() {

        //  Mock microservicio mascotas
        when(restTemplate.getForObject(anyString(), eq(Object.class)))
                .thenReturn(new Object());

        //  Crear objeto de prueba
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdReporte(1L);
        ubicacion.setLatitud(-33.0458);
        ubicacion.setLongitud(-71.6197);
        ubicacion.setDireccion("Valparaíso");
        ubicacion.setFecha(LocalDate.now());

        //  Mock guardado DB
        when(ubicacionRepository.save(any(Ubicacion.class)))
                .thenReturn(ubicacion);

        //  Ejecutar
        Ubicacion resultado = ubicacionService.guardarUbicacion(ubicacion);

        //  Validaciones
        assertNotNull(resultado);
        assertEquals("Valparaíso", resultado.getDireccion());

        //  Verificaciones
        verify(ubicacionRepository, times(1)).save(any(Ubicacion.class));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Object.class));
    }
}