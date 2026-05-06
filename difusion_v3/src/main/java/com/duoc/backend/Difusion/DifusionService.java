package com.duoc.backend.Difusion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class DifusionService {

    @Autowired
    private DifusionRepository difusionRepository;

    @Autowired
    private RestTemplate restTemplate; 

    public Iterable<Difusion> obtenerDifusiones() {
        return difusionRepository.findAll();
    }

    public Difusion obtenerDifusionPorId(Long id) {
        return difusionRepository.findById(id).orElse(null);
    }

    public Difusion guardarDifusion(Difusion difusion) {

        if (difusion.getIdReporte() == null) {
            throw new RuntimeException("El idReporte es obligatorio");
        }

        String url = "http://localhost:8080/mascotas/" + difusion.getIdReporte();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

            // Validar status HTTP
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("La mascota no existe");
            }

            // Validar body
            if (response.getBody() == null) {
                throw new RuntimeException("La mascota no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("La mascota no existe");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con microservicio de mascotas");
        }

        return difusionRepository.save(difusion);
    }   

    public void eliminarDifusion(Long id) {
        difusionRepository.deleteById(id);
    }

    public Difusion actualizarDifusion(Long id, Difusion difusion) {

        // 1. Verificar que la difusión exista
        Difusion existente = difusionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La difusión no existe"));

        // 2. Validar idReporte obligatorio
        if (difusion.getIdReporte() == null) {
            throw new RuntimeException("El idReporte es obligatorio");
        }

        // 3. Validar que la mascota exista (microservicio mascotas)
        String url = "http://localhost:8080/mascotas/" + difusion.getIdReporte();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new RuntimeException("La mascota no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("La mascota no existe");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con microservicio de mascotas");
        }

        existente.setIdReporte(difusion.getIdReporte());
        existente.setPlataforma(difusion.getPlataforma());
        existente.setEstado(difusion.getEstado());
        existente.setUrlPublicacion(difusion.getUrlPublicacion());
        existente.setFechaPublicacion(difusion.getFechaPublicacion());

        return difusionRepository.save(existente);
    }
    
}