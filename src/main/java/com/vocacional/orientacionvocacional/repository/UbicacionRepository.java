package com.vocacional.orientacionvocacional.repository;

import com.vocacional.orientacionvocacional.model.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion , Long> {
    Ubicacion findByCiudadAndRegionAndPais(String ciudad, String region, String pais);
}