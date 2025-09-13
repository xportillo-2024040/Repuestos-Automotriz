package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(Integer idCliente);
    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Integer idCliente, Cliente cliente);
    void deleteCliente(Integer idCliente);
}
