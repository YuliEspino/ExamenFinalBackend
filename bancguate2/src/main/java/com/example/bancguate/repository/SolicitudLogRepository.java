/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bancguate.repository;

import com.example.bancguate.model.SolicitudLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC-ELHID
 */
//proporciona una forma fácil y rápida de realizar operaciones de acceso a datos mediante métodos predefinidos
public interface SolicitudLogRepository extends JpaRepository<SolicitudLog, Long> {
   
}
