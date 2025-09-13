package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Vehiculo;
import java.util.List;

public interface VehiculoService {
    List<Vehiculo> getAllVehiculos();
    Vehiculo getVehiculoById(Integer idVehiculo);
    Vehiculo saveVehiculo(Vehiculo vehiculo);
    Vehiculo updateVehiculo(Integer idVehiculo, Vehiculo vehiculo);
    void deleteVehiculo(Integer idVehiculo);
}
