package com.xavierportillo.RepuestosAutomotriz.controller;

import com.xavierportillo.RepuestosAutomotriz.model.Pedido;
import com.xavierportillo.RepuestosAutomotriz.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.getAllPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable Integer id) {
        try {
            Pedido pedido = pedidoService.getPedidoById(id);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido newPedido = pedidoService.savePedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        try {
            Pedido updated = pedidoService.updatePedido(id, pedido);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Integer id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.ok("Se elimino correctamente el pedido");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
