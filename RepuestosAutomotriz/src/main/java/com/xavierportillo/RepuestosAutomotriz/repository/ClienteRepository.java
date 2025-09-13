package com.xavierportillo.RepuestosAutomotriz.repository;

import com.xavierportillo.RepuestosAutomotriz.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByNombre(String nombre);
    boolean existsByApellido(String apellido);
    boolean existsByEmail(String email);
    boolean existsByTelefono(String telefono);

    boolean existsByNombreAndIdClienteNot(String nombre, Integer idCliente);
    boolean existsByApellidoAndIdClienteNot(String apellido, Integer idCliente);
    boolean existsByEmailAndIdClienteNot(String email, Integer idCliente);
    boolean existsByTelefonoAndIdClienteNot(String telefono, Integer idCliente);

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cliente WHERE id_cliente = :idCliente", nativeQuery = true)
    void deleteClienteByIdNative(@Param("idCliente") Integer idCliente);
}
