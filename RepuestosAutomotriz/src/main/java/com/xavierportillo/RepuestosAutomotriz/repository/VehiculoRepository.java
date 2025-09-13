package com.xavierportillo.RepuestosAutomotriz.repository;

import com.xavierportillo.RepuestosAutomotriz.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    boolean existsByMarca(String marca);
    boolean existsByModelo(String modelo);

    boolean existsByMarcaAndIdVehiculoNot(String marca, Integer idVehiculo);
    boolean existsByModeloAndIdVehiculoNot(String modelo, Integer idVehiculo);
}
