package com.example.prueba.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Feriados {
    public static  List<LocalDate> listarFeriados()
    {
        List<LocalDate> diasFeriados = new ArrayList<LocalDate>();
        diasFeriados.add(LocalDate.parse("2023-10-09"));
        diasFeriados.add(LocalDate.parse("2023-11-02"));
        diasFeriados.add(LocalDate.parse("2023-11-03"));
        diasFeriados.add(LocalDate.parse("2023-12-25"));
        return diasFeriados;
    }

}
