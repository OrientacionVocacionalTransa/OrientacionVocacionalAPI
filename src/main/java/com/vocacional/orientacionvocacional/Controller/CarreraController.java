package com.vocacional.orientacionvocacional.Controller;

import com.vocacional.orientacionvocacional.model.entity.Carrera;
import com.vocacional.orientacionvocacional.model.entity.Ubicacion;
import com.vocacional.orientacionvocacional.repository.CarreraRepository;
import com.vocacional.orientacionvocacional.repository.UbicacionRepository;
import com.vocacional.orientacionvocacional.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @GetMapping("/filtrarubicacionexacta")
    public ResponseEntity<List<Carrera>> filtrarCarrerasPorUbicacion(
            @RequestParam String ciudad,
            @RequestParam String region,
            @RequestParam String pais
    ) {
        List<Carrera> carreras = carreraService.obtenerCarrerasPorUbicacion(ciudad, region, pais);
        if (carreras != null && !carreras.isEmpty()) {
            return ResponseEntity.ok(carreras);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping("/insertarubi")
    public ResponseEntity<Ubicacion> agregarUbicacion(@RequestBody Ubicacion ubicacion) {
        Ubicacion nuevaUbicacion = ubicacionRepository.save(ubicacion);
        return ResponseEntity.ok(nuevaUbicacion);
    }


    @PostMapping("/insertarcarrera")
    public ResponseEntity<Carrera> agregarCarrera(@RequestParam String nombreCarrera, @RequestParam Long ubicacionId) {
        Ubicacion ubicacion = ubicacionRepository.findById(ubicacionId).orElse(null);
        if (ubicacion == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Carrera carrera = new Carrera();
        carrera.setNombre(nombreCarrera);
        carrera.setUbicacion(ubicacion);

        Carrera nuevaCarrera = carreraRepository.save(carrera);
        return ResponseEntity.ok(nuevaCarrera);
    }


    @GetMapping("/mostrarcarreras")
    public ResponseEntity<List<Carrera>> obtenercarreras(){
        List<Carrera> carreras = carreraRepository.findAll();
        return ResponseEntity.ok(carreras);
    }
}
