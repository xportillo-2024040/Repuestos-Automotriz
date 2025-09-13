package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Pedido;
import java.util.List;

public interface PedidoService {
    List<Pedido> getAllPedidos();
    Pedido getPedidoById(Integer idPedido);
    Pedido savePedido(Pedido pedido);
    Pedido updatePedido(Integer idPedido, Pedido pedido);
    void deletePedido(Integer idPedido);
}
