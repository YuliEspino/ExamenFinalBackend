/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bancguate.controller;

import com.example.bancguate.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC-ELHID
 */

//maneja las solicitudes GET a la ruta
@RestController
@RequestMapping("/tipo-cambio")
public class TipoCambioController {
     @Autowired
    private TipoCambioService tipoCambioService;

    @GetMapping("/obtener")
    public String obtenerTipoCambio() {
        return tipoCambioService.obtenerTipoCambio();
    }
}
