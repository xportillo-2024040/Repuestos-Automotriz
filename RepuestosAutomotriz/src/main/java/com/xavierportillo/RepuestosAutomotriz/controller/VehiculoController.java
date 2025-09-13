package com.xavierportillo.RepuestosAutomotriz.controller;

import com.xavierportillo.RepuestosAutomotriz.model.Vehiculo;
import com.xavierportillo.RepuestosAutomotriz.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVehiculos() {
        try {
            List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
            return ResponseEntity.ok(vehiculos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehiculoById(@PathVariable Integer id) {
        try {
            Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
            return ResponseEntity.ok(vehiculo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo newVehiculo = vehiculoService.saveVehiculo(vehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(newVehiculo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo updated = vehiculoService.updateVehiculo(id, vehiculo);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehiculo(@PathVariable Integer id) {
        try {
            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.ok("Se elimino correctamente el vehiculo");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
