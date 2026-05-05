package com.duoc.backend;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.duoc.backend.Difusion.Difusion;
import com.duoc.backend.Difusion.DifusionRepository;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private DifusionRepository difusionRepository;

    @Test
    void contextLoads() {
        assertThat(difusionRepository).isNotNull();
    }

    @Test
    void testGuardarDifusion() {
        Difusion difusion = new Difusion();
        difusion.setIdReporte(1L);
        difusion.setPlataforma("FACEBOOK");
        difusion.setEstado("PENDIENTE");

        Difusion guardado = difusionRepository.save(difusion);

        assertThat(guardado.getIdDifusion()).isNotNull();
        assertThat(guardado.getPlataforma()).isEqualTo("FACEBOOK");
    }
}