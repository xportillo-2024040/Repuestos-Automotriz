package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Pedido;
import com.xavierportillo.RepuestosAutomotriz.repository.PedidoRepository;
import com.xavierportillo.RepuestosAutomotriz.repository.ClienteRepository;
import com.xavierportillo.RepuestosAutomotriz.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final RepuestoRepository repuestoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteRepository clienteRepository,
                             RepuestoRepository repuestoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pedido no encontrado con ID: " + id)
        );
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        if (!clienteRepository.existsById(pedido.getCliente().getIdCliente())) {
            throw new RuntimeException("El cliente asociado no existe");
        }
        if (!repuestoRepository.existsById(pedido.getRepuesto().getIdRepuesto())) {
            throw new RuntimeException("El repuesto asociado no existe");
        }
        if (pedido.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }
        if (pedido.getFecha() == null) {
            throw new RuntimeException("La fecha es obligatoria");
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Integer id, Pedido pedido) {
        Pedido existingPedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));

        if (!clienteRepository.existsById(pedido.getCliente().getIdCliente())) {
            throw new RuntimeException("El cliente asociado no existe");
        }
        if (!repuestoRepository.existsById(pedido.getRepuesto().getIdRepuesto())) {
            throw new RuntimeException("El repuesto asociado no existe");
        }
        if (pedido.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }
        if (pedido.getFecha() == null) {
            throw new RuntimeException("La fecha es obligatoria");
        }

        existingPedido.setCliente(pedido.getCliente());
        existingPedido.setRepuesto(pedido.getRepuesto());
        existingPedido.setCantidad(pedido.getCantidad());
        existingPedido.setFecha(pedido.getFecha());

        return pedidoRepository.save(existingPedido);
    }

    @Override
    public void deletePedido(Integer id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            System.out.println("El pedido con el ID " + id + " fue eliminado correctamente.");
        } else {
            throw new RuntimeException("El id no existe");
        }
    }
}
