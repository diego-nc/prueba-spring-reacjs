package com.example.prueba.controllers;

import com.example.prueba.models.Vehiculo;
import com.example.prueba.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    @Autowired
    VehiculoService vehiculoService;
    @PostMapping
    public List<Vehiculo> realizarMantenimento(@RequestBody Vehiculo vehiculo){
        return this.vehiculoService.realizarMantenimento(vehiculo);
    }
}
