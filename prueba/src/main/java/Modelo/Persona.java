/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 *
 * @author NATHALY
 */
public class Persona implements Serializable {
    /**
     * Variable que almacena nombre
     */
    private String nombreP;
    /**
     * variable que almacen correo
     */
    private String correoP;
    /**
     * Variable que almacena la ciudad
     */
    private String ciudadP;
    /**
     * lista que guarda los usuarios
     */
    private static List<Persona>listap = new ArrayList<>();
    /**
     * Variable que almacena los mensajes
     */
    private String mensaje;
    /**
     * Lista de mensajes para el administrador
     */
    private static List<Persona>listaMensaje = new ArrayList<>();

    /**
     * Constructor para almacenar los usuarios
     * @param nombreP
     * @param correoP
     * @param ciudadP 
     */
    public Persona(String nombreP, String correoP, String ciudadP) {
        this.nombreP = nombreP;
        this.correoP = correoP;
        this.ciudadP = ciudadP;
    }
    /**
     * retorna mensaje
     * @param mensaje 
     */
    public Persona(String mensaje) {
        this.mensaje = mensaje;
    }
   
    public Persona() {
        
    }
    /**
     * obtiene nombre usuario
     * @return 
     */
    public String getNombreP() {
        return nombreP;
    }
    /**
     * Retorna nombre usuario
     * @param nombreP 
     */
    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }
    /**
     * Obtiene correo usuario
     * @return 
     */
    public String getCorreoP() {
        return correoP;
    }
    /**
     * Retorna correo usuario
     * @param correoP 
     */
    public void setCorreoP(String correoP) {
        this.correoP = correoP;
    }
    /**
     * Obtiene ciudad usuario
     * @return 
     */
    public String getCiudadP() {
        return ciudadP;
    }
    /**
     * Retorna ciudad usuario
     * @param ciudadP 
     */
    public void setCiudadP(String ciudadP) {
        this.ciudadP = ciudadP;
    }
    /**
     * Obtiene lista usuarios
     * @return 
     */
    public List<Persona> getListap() {
        return listap;
    }
    /**
     * Obtiene lista usuarios
     * @param listap 
     */
    public void setListap(List<Persona> listap) {
        this.listap = listap;
    }
    /**
     * Obtiene mensaje 
     * @return 
     */
    public String getMensaje() {
        return mensaje;
    }
    /**
     * Retorna mensaje 
     * @param mensaje 
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    /**
     * Obtiene la lista de mensajes
     * @return 
     */
    public List<Persona> getListaMensaje() {
        return listaMensaje;
    }
    /**
     * Retorna lista mensajes
     * @param listaMensaje 
     */
    public void setListaMensaje(List<Persona> listaMensaje) {
        this.listaMensaje = listaMensaje;
    }
    
    /**
     * Recibe los parametros de la vista para agregar a la lista usuario
     * @param nombre
     * @param ciudad
     * @param correo 
     */
    
    public void agregarPersona( String nombre, String ciudad, String correo){
        Persona per = new Persona(nombre,correo,ciudad);
        listap.add(per);
        
        
    }
    /**
     * Recibe mensaje para agregar a la lista de mensajes
     * @param mensajeEntra
     * @return 
     */
    public List<Persona> guardaMensajes(String mensajeEntra){
        setMensaje(mensajeEntra);
        listaMensaje.add(new Persona(mensajeEntra));
        return listaMensaje;
    }

   
    
}
