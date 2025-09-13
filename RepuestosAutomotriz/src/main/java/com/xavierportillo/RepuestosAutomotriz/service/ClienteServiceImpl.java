package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Cliente;
import com.xavierportillo.RepuestosAutomotriz.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        if (clienteRepository.existsByNombre(cliente.getNombre()))
            throw new RuntimeException("El nombre ya esta en uso");
        if (clienteRepository.existsByApellido(cliente.getApellido()))
            throw new RuntimeException("El apellido ya esta en uso");
        if (clienteRepository.existsByEmail(cliente.getEmail()))
            throw new RuntimeException("El correo ya esta en uso");
        if (cliente.getTelefono() != null && !cliente.getTelefono().isEmpty() &&
                clienteRepository.existsByTelefono(cliente.getTelefono()))
            throw new RuntimeException("El telefono ya esta en uso");

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Integer id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        if (clienteRepository.existsByNombreAndIdClienteNot(cliente.getNombre(), id))
            throw new RuntimeException("El nombre ya esta en uso por otro cliente");
        if (clienteRepository.existsByApellidoAndIdClienteNot(cliente.getApellido(), id))
            throw new RuntimeException("El apellido ya esta en uso por otro cliente");
        if (clienteRepository.existsByEmailAndIdClienteNot(cliente.getEmail(), id))
            throw new RuntimeException("El correo ya esta en uso por otro cliente");
        if (cliente.getTelefono() != null && !cliente.getTelefono().isEmpty() &&
                clienteRepository.existsByTelefonoAndIdClienteNot(cliente.getTelefono(), id))
            throw new RuntimeException("El telefono ya esta en uso por otro cliente");

        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setApellido(cliente.getApellido());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setTelefono(cliente.getTelefono());

        return clienteRepository.save(existingCliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        if (!clienteRepository.existsById(id))
            throw new RuntimeException("El cliente no existe");

        clienteRepository.disableForeignKeyChecks();
        clienteRepository.deleteClienteByIdNative(id);
        clienteRepository.enableForeignKeyChecks();
    }
}
