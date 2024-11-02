/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bancguate.service;

import com.example.bancguate.model.SolicitudLog;
import com.example.bancguate.repository.SolicitudLogRepository;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC-ELHID
 */

//se encarga de obtener el tipo de cambio de un servicio web SOAP
@Service
public class TipoCambioService {

@Autowired
private SolicitudLogRepository solicitudLogRepository;

public String obtenerTipoCambio() {
    SOAPConnection soapConnection = null;
    try {
        // Crear la solicitud SOAP
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        soapConnection = soapConnectionFactory.createConnection();
        String url = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";
        SOAPMessage soapMessage = crearSoapMessage();

        SOAPMessage soapResponse = soapConnection.call(soapMessage, url);
        String respuesta = soapResponse.getSOAPBody().getTextContent();

        // Registrar en la base de datos
        SolicitudLog log = new SolicitudLog();
        log.setNumeroSolicitud(UUID.randomUUID().toString());
        log.setRespuesta(respuesta);
        log.setFechaSolicitud(LocalDateTime.now());
        solicitudLogRepository.save(log);

        return respuesta;

    } catch (SOAPException e) {
        e.printStackTrace();
        return "Error en la conexión SOAP al obtener el tipo de cambio";
    } catch (Exception e) {
        e.printStackTrace();
        return "Error al obtener el tipo de cambio";
    } finally {
        if (soapConnection != null) {
            try {
                soapConnection.close();
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }
    }
}

private SOAPMessage crearSoapMessage() throws SOAPException {
    MessageFactory messageFactory = MessageFactory.newInstance();
    SOAPMessage soapMessage = messageFactory.createMessage();
    SOAPPart soapPart = soapMessage.getSOAPPart();

    String serverURI = "http://www.banguat.gob.gt/variables/ws/";

    SOAPEnvelope envelope = soapPart.getEnvelope();
    envelope.addNamespaceDeclaration("banguat", serverURI);

    // Construir el cuerpo del SOAP
    SOAPBody soapBody = envelope.getBody();
    soapBody.addChildElement("TipoCambioDiaString", "banguat");

    soapMessage.saveChanges();

    return soapMessage;
}

}
