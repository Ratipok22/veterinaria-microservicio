package com.duoc.backend.Ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private RestTemplate restTemplate; // 

    // Obtener todas
    public Iterable<Ubicacion> obtenerUbicaciones() {
        return ubicacionRepository.findAll();
    }

    // Obtener por ID
    public Ubicacion obtenerUbicacionPorId(Long id) {
        return ubicacionRepository.findById(id).orElse(null);
    }

    // Guardar (validando mascota)
    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {

        String url = "http://localhost:8080/mascotas/" + ubicacion.getIdReporte();

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

        return ubicacionRepository.save(ubicacion);
    }

    // Eliminar
    public void eliminarUbicacion(Long id) {
        ubicacionRepository.deleteById(id);
    }

    public Ubicacion actualizarUbicacion(Long id, Ubicacion ubicacionActualizada) {

        Ubicacion existente = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));

        if (ubicacionActualizada.getIdReporte() == null) {
            throw new RuntimeException("El idReporte es obligatorio");
        }

        String MASCOTAS_URL = "http://localhost:8081/mascotas/" + ubicacionActualizada.getIdReporte();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(MASCOTAS_URL, Object.class);

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new RuntimeException("La mascota no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("La mascota no existe");
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con mascotas");
        }

        existente.setIdReporte(ubicacionActualizada.getIdReporte());
        existente.setLatitud(ubicacionActualizada.getLatitud());
        existente.setLongitud(ubicacionActualizada.getLongitud());
        existente.setDireccion(ubicacionActualizada.getDireccion());
        existente.setFecha(ubicacionActualizada.getFecha());

        return ubicacionRepository.save(existente);
    }

}