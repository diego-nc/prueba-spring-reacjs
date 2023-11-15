package com.example.prueba.services;

import com.example.prueba.models.Vehiculo;
import com.example.prueba.repositories.VehiculoRepository;
import com.example.prueba.utils.Feriados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;
    Feriados feriados;
    public List<Vehiculo> getVehiculos()
    {
        return  (List<Vehiculo>) this.vehiculoRepository.findAll();
    }
    public void saveVehiculo (Vehiculo vehiculo)
    {
        this.vehiculoRepository.save(vehiculo);
    }
    public  List<Vehiculo> realizarMantenimento(Vehiculo vehiculo)
    {
        Map<String, String> vehiculosPlacas = new HashMap<String, String>();
        vehiculosPlacas.put("2023-09-29", "PAA7924");
        vehiculosPlacas.put("2023-10-06", "PAA7920");
        vehiculosPlacas.put("2023-10-12", "PAA7888");
        vehiculosPlacas.put("2023-10-13", "PAA7919");
        vehiculosPlacas.put("2023-10-20", "PAA7918");
        vehiculosPlacas.put("2023-10-16", "PAA7914");
        vehiculosPlacas.put("2023-10-21", "PAA7916");
        vehiculosPlacas.put("2023-10-22", "PAA7915");
        vehiculosPlacas.put("2023-10-23", "PAA7910");
        vehiculosPlacas.put("2023-10-28", "PAA7909");
        vehiculosPlacas.put("2023-10-26", "PAA7908");
        vehiculosPlacas.put("2023-10-27", "PAA7907");
        vehiculosPlacas.put("2023-10-30", "PAA7906");
        vehiculosPlacas.put("2023-10-10", "PDN4883");
        vehiculosPlacas.put("2023-10-29", "PDN4881");

        List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();

        var fechasList = listarFechasMantenimiento(vehiculo);

        LocalDate fechaList=null;
        for (String i : vehiculosPlacas.keySet()) {
            fechaList = LocalDate.parse(i);
            String valor = vehiculosPlacas.get(i);
            Vehiculo oVehiculo = new Vehiculo();

            oVehiculo.setFechaDeCompra(LocalDate.parse(i));
            oVehiculo.setPlaca(valor);
            listaVehiculos.add(oVehiculo);
        }

        var feriadoDia= esFeriado(vehiculo.getFechaDeCompra().toString());
        var esFinDeSemana = esFinDeSemana(vehiculo.getFechaDeCompra());
        List<Vehiculo> vehiculosMantenimiento = new ArrayList<Vehiculo>();
        if (esFinDeSemana || feriadoDia) {

        }else {

            for (String f: fechasList ) {
                for (Vehiculo v : listaVehiculos) {

                    var fecha = v.getFechaDeCompra().toString();
                    if (f.equals(fecha)) {
                        Vehiculo vehiculoM = new Vehiculo();
                        vehiculoM.setFechaDeCompra(v.getFechaDeCompra());
                        vehiculoM.setPlaca(v.getPlaca());
                        vehiculosMantenimiento.add(vehiculoM);
                    }

                }
            }
            var  fechasFinList = fechasFinSemana(vehiculo);
            for (String ff : fechasFinList) {
                for (Vehiculo v : listaVehiculos) {

                    var fecha = v.getFechaDeCompra().toString();
                    if (ff.equals(fecha)) {
                        Vehiculo vehiculoMFS = new Vehiculo();
                        vehiculoMFS.setFechaDeCompra(v.getFechaDeCompra());
                        vehiculoMFS.setPlaca(v.getPlaca());
                        vehiculosMantenimiento.add(vehiculoMFS);
                    }

                }
            }

            var fechasXFeriadoList= fechasXFeriado(vehiculo);
            if (fechasXFeriadoList != null) {
                for (String fxf: fechasXFeriadoList) {
                    var fechaXferiado = LocalDate.parse(fxf).minusDays(7);
                    for (Vehiculo v: listaVehiculos) {
                        var fecha = v.getFechaDeCompra().toString();
                        if (fechaXferiado.toString().equals(fecha)) {
                            Vehiculo vehiculoMFS = new Vehiculo();
                            vehiculoMFS.setFechaDeCompra(v.getFechaDeCompra());
                            vehiculoMFS.setPlaca(v.getPlaca());
                            vehiculosMantenimiento.add(vehiculoMFS);
                        }
                    }

                }
            }

        }
        return  vehiculosMantenimiento;
    }

    public List<String> listarFechasMantenimiento( Vehiculo vehiculo)
    {
        var i=0;
        List<String> fechasList= new ArrayList<String>();
        LocalDate newFecha = vehiculo.getFechaDeCompra();
        while(i<10)
        {
           newFecha = newFecha.minusDays(7);
            fechasList.add(newFecha.toString());
            i++;
        }
        return  fechasList;
    }
    public boolean esFeriado(String fecha)
    {
        var diaFeriado= false;
        List<LocalDate> diasFeriados = feriados.listarFeriados();

        for (LocalDate df: diasFeriados)
        {
            if (df.toString().equals(fecha)) {
                diaFeriado=true;
            }
        }

        return  diaFeriado;
    }
     public boolean esFinDeSemana(LocalDate fecha)
     {
         var findeSemana = false;
         var diaSemana = fecha.getDayOfWeek().toString();
         if (diaSemana.equals("SATURDAY")|| diaSemana.equals("SUNDAY")) {
             findeSemana=true;
         }
        return findeSemana;
     }
    public List<String> fechasFinSemana( Vehiculo vehiculo)
    {
        var i=0;
        List<String> fechasFinList= new ArrayList<String>();
        LocalDate newFecha = vehiculo.getFechaDeCompra();

        if (esFinDeSemana(vehiculo.getFechaDeCompra().minusDays(1))) {
            while(i<9)
            {
                newFecha = newFecha.minusDays(1);
                if (esFinDeSemana(newFecha)) {
                    fechasFinList.add(newFecha.toString());
                }

                i++;
            }
        }

        return  fechasFinList;
    }

    public List<String> fechasXFeriado(Vehiculo vehiculo)
    {
        var i=0;
        List<String> fechasXFeriadoList= new ArrayList<String>();
        var diasferiados = feriados.listarFeriados();
        LocalDate newFecha = vehiculo.getFechaDeCompra();
        while(i<20)
        {
            newFecha = newFecha.minusDays(1);
            for (LocalDate df: diasferiados) {
                if (df.toString().equals(newFecha.toString())) {
                    fechasXFeriadoList.add(newFecha.toString());
                }
            }


            i++;
        }
        return fechasXFeriadoList;
    }


}
