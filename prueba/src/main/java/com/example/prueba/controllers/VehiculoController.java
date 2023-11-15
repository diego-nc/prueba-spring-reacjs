package com.example.prueba.controllers;

import com.example.prueba.models.Vehiculo;
import com.example.prueba.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpResponse;
import java.util.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;
    RestTemplate restTemplate;
    @GetMapping()
    public List<Vehiculo> getVehiculos()
    {
        return this.vehiculoService.getVehiculos();

    }
    @PostMapping
    public  void createVehiculo(@RequestBody Vehiculo vehiculo)
    {
        var vehiculoObjApi = getPrecio(vehiculo.getPlaca());
        var texto = vehiculoObjApi.replaceAll("[{}]", "").replace(",",";");
        var txtArray = texto.split(";");
        var campoArr= txtArray[1].split(":");
        var precio = Float.parseFloat( campoArr[1].toString());
         Vehiculo vehiculoObj = new Vehiculo();
         vehiculoObj.setAño( vehiculo.getAño());
         vehiculoObj.setFechaDeCompra(vehiculo.getFechaDeCompra());
         vehiculoObj.setModelo(vehiculo.getModelo());
         vehiculoObj.setPlaca(vehiculo.getPlaca());
         vehiculoObj.setObservaciones(vehiculo.getObservaciones());
         vehiculoObj.setPrecio(precio);
        this.vehiculoService.saveVehiculo(vehiculoObj);
    }

    public String getPrecio(String placa) {

        String url = "https://auto.jedai.workers.dev/";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("placa", "{placa}")
                .encode()
                .toUriString();
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("placa", placa);

        HttpEntity<String> response = template.exchange(
                urlTemplate,
                HttpMethod.GET,
                requestEntity,
                String.class,
                uriVariables
        );

        return response.getBody();
    }

}
