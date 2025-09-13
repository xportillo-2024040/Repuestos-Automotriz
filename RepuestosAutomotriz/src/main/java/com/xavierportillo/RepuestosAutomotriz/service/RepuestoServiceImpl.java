package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Repuesto;
import com.xavierportillo.RepuestosAutomotriz.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImpl implements RepuestoService {

    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto getRepuestoById(Integer id) {
        return repuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repuesto no encontrado con ID: " + id));
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) {
        if (repuestoRepository.existsByNombre(repuesto.getNombre()))
            throw new RuntimeException("El nombre del repuesto ya esta en uso");

        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuesto) {
        Repuesto existingRepuesto = repuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repuesto no encontrado con ID: " + id));

        if (repuestoRepository.existsByNombreAndIdRepuestoNot(repuesto.getNombre(), id))
            throw new RuntimeException("El nombre del repuesto ya esta en uso por otro repuesto");

        existingRepuesto.setNombre(repuesto.getNombre());
        existingRepuesto.setDescripcion(repuesto.getDescripcion());
        existingRepuesto.setPrecio(repuesto.getPrecio());
        existingRepuesto.setStock(repuesto.getStock());

        return repuestoRepository.save(existingRepuesto);
    }

    @Override
    public void deleteRepuesto(Integer id) {
        if (!repuestoRepository.existsById(id))
            throw new RuntimeException("El repuesto no existe");

        repuestoRepository.disableForeignKeyChecks();
        repuestoRepository.deleteRepuestoByIdNative(id);
        repuestoRepository.enableForeignKeyChecks();
    }
}
