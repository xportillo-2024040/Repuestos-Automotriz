package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Vehiculo;
import com.xavierportillo.RepuestosAutomotriz.repository.VehiculoRepository;
import com.xavierportillo.RepuestosAutomotriz.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ClienteRepository clienteRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, ClienteRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Vehiculo no encontrado con ID: " + id)
        );
    }

    @Override
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        if (!clienteRepository.existsById(vehiculo.getCliente().getIdCliente())) {
            throw new RuntimeException("El cliente asociado no existe");
        }
        if (vehiculo.getMarca() == null || vehiculo.getMarca().isEmpty()) {
            throw new RuntimeException("La marca es obligatoria");
        }
        if (vehiculo.getModelo() == null || vehiculo.getModelo().isEmpty()) {
            throw new RuntimeException("El modelo es obligatorio");
        }
        if (vehiculo.getAño() <= 1900) {
            throw new RuntimeException("El año no es valido");
        }
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo) {
        Vehiculo existingVehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + id));

        if (!clienteRepository.existsById(vehiculo.getCliente().getIdCliente())) {
            throw new RuntimeException("El cliente asociado no existe");
        }
        if (vehiculo.getMarca() == null || vehiculo.getMarca().isEmpty()) {
            throw new RuntimeException("La marca es obligatoria");
        }
        if (vehiculo.getModelo() == null || vehiculo.getModelo().isEmpty()) {
            throw new RuntimeException("El modelo es obligatorio");
        }
        if (vehiculo.getAño() <= 1900) {
            throw new RuntimeException("El año no es valido");
        }

        existingVehiculo.setMarca(vehiculo.getMarca());
        existingVehiculo.setModelo(vehiculo.getModelo());
        existingVehiculo.setAño(vehiculo.getAño());
        existingVehiculo.setCliente(vehiculo.getCliente());

        return vehiculoRepository.save(existingVehiculo);
    }

    @Override
    public void deleteVehiculo(Integer id) {
        if (vehiculoRepository.existsById(id)) {
            vehiculoRepository.deleteById(id);
            System.out.println("El vehiculo con el ID " + id + " fue eliminado correctamente.");
        } else {
            throw new RuntimeException("El id no existe");
        }
    }
}
