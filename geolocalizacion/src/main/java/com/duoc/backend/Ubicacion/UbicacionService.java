package com.duoc.backend.Ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private RestTemplate restTemplate; // ✅ necesario

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

        String url = "http://localhost:3307/mascotas/" + ubicacion.getIdReporte();

        try {
            restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            throw new RuntimeException("La mascota no existe");
        }

        return ubicacionRepository.save(ubicacion);
    }

    // Eliminar
    public void eliminarUbicacion(Long id) {
        ubicacionRepository.deleteById(id);
    }
}