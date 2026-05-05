package com.duoc.backend.Mascotas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class MascotasService {

    @Autowired
    private MascotasRepository mascotasRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Iterable<Mascotas> obtenerMascotas() {
        return mascotasRepository.findAll();
    }

    public Mascotas obtenerMascotaPorId(Long id) {
        return mascotasRepository.findById(id).orElse(null);
    }

    public Mascotas guardarMascota(Mascotas mascota) {

        if (mascota.getIdUsuario() == null) {
            throw new RuntimeException("El idUsuario es obligatorio");
        }

        String USUARIOS_URL = "http://localhost:8180/usuarios/" + mascota.getIdUsuario();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(USUARIOS_URL, Object.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("El usuario no existe");
            }

            if (response.getBody() == null) {
                throw new RuntimeException("El usuario no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("El usuario no existe");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con microservicio de usuarios");
        }

        return mascotasRepository.save(mascota);
    }

    public void eliminarMascota(Long id) {
        mascotasRepository.deleteById(id);
    }

    public Mascotas actualizarMascota(Long id, Mascotas mascotaActualizada) {

        Mascotas mascotaExistente = mascotasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if (mascotaActualizada.getIdUsuario() == null) {
            throw new RuntimeException("El idUsuario es obligatorio");
        }

        String USUARIOS_URL = "http://localhost:8180/usuarios/" + mascotaActualizada.getIdUsuario();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(USUARIOS_URL, Object.class);

            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                throw new RuntimeException("El usuario no existe");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("El usuario no existe");
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con microservicio de usuarios");
        }

        // Actualización de campos
        mascotaExistente.setTipo_reporte(mascotaActualizada.getTipo_reporte());
        mascotaExistente.setTipo_mascota(mascotaActualizada.getTipo_mascota());
        mascotaExistente.setRaza(mascotaActualizada.getRaza());
        mascotaExistente.setColor(mascotaActualizada.getColor());
        mascotaExistente.setTamaño(mascotaActualizada.getTamaño());
        mascotaExistente.setDescripcion(mascotaActualizada.getDescripcion());
        mascotaExistente.setFoto_url(mascotaActualizada.getFoto_url());
        mascotaExistente.setEstado(mascotaActualizada.getEstado());
        mascotaExistente.setFecha_reporte(mascotaActualizada.getFecha_reporte());
        mascotaExistente.setIdUsuario(mascotaActualizada.getIdUsuario());

        return mascotasRepository.save(mascotaExistente);
    }
}