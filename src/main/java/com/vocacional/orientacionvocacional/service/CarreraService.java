package com.vocacional.orientacionvocacional.service;

import com.vocacional.orientacionvocacional.model.entity.Carrera;
import com.vocacional.orientacionvocacional.model.entity.Ubicacion;
import com.vocacional.orientacionvocacional.repository.CarreraRepository;
import com.vocacional.orientacionvocacional.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Carrera> obtenerCarrerasPorUbicacion(String ciudad, String region, String pais) {
        Ubicacion ubicacion = ubicacionRepository.findByCiudadAndRegionAndPais(ciudad, region, pais);
        if (ubicacion != null) {
            return carreraRepository.findByUbicacion(ubicacion);
        }
        return null;
    }
}
