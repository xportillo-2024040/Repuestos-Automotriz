package com.xavierportillo.RepuestosAutomotriz.controller;

import com.xavierportillo.RepuestosAutomotriz.model.Repuesto;
import com.xavierportillo.RepuestosAutomotriz.service.RepuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuesto")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRepuestos() {
        try {
            List<Repuesto> repuestos = repuestoService.getAllRepuestos();
            return ResponseEntity.ok(repuestos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRepuestoById(@PathVariable Integer id) {
        try {
            Repuesto repuesto = repuestoService.getRepuestoById(id);
            return ResponseEntity.ok(repuesto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createRepuesto(@RequestBody Repuesto repuesto) {
        try {
            Repuesto newRepuesto = repuestoService.saveRepuesto(repuesto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRepuesto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepuesto(@PathVariable Integer id, @RequestBody Repuesto repuesto) {
        try {
            Repuesto updated = repuestoService.updateRepuesto(id, repuesto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRepuesto(@PathVariable Integer id) {
        try {
            repuestoService.deleteRepuesto(id);
            return ResponseEntity.ok("Se elimino correctamente el repuesto");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
