package com.xavierportillo.RepuestosAutomotriz.repository;

import com.xavierportillo.RepuestosAutomotriz.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface RepuestoRepository extends JpaRepository<Repuesto, Integer> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdRepuestoNot(String nombre, Integer idRepuesto);

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
    @Query(value = "DELETE FROM repuesto WHERE id_repuesto = :idRepuesto", nativeQuery = true)
    void deleteRepuestoByIdNative(@Param("idRepuesto") Integer idRepuesto);
}
