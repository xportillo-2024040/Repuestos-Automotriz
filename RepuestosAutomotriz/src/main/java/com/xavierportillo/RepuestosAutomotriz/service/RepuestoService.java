package com.xavierportillo.RepuestosAutomotriz.service;

import com.xavierportillo.RepuestosAutomotriz.model.Repuesto;
import java.util.List;

public interface RepuestoService {
    List<Repuesto> getAllRepuestos();
    Repuesto getRepuestoById(Integer idRepuesto);
    Repuesto saveRepuesto(Repuesto repuesto);
    Repuesto updateRepuesto(Integer idRepuesto, Repuesto repuesto);
    void deleteRepuesto(Integer idRepuesto);
}
