/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bancguate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author PC-ELHID
 */

// representa información sobre los tipos de cambio en una base de datos
@Entity
public class TipoCambio {
@Id
@GeneratedValue(strategy = GenerationType. IDENTITY)
private Long id;
private String fecha;
private String tipoCambio;
}
