/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Persona;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author NATHALY
 */
@ManagedBean(name="controlp1")
@SessionScoped
public class Controlp1 {
    
    /**
     * Variable que almacena nombre de usuario con anotacion de maxima cantidad de caracteres
     */
    @Size(min=2,max=8)
    
    private String nombre;
    
    /**
     * Variable que almacena ciudad con anotacion de maxima cantidad de caracteres
     */
    @Size(min=2,max=10)
   
    private String ciudad;
    /**
     * Variable que almacena correo de usuario con validacion
     */
    @Pattern(regexp ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String correo;
    /**
     * Lista que almacena los usuarios registrados
     */
    private static List<Persona>lista = new ArrayList<>();
    /**
     * Variable que almacena el mensaje 
     */
    private String mensaje;
    /**
     * Lista de mensajes almacenados para el administrador
     */
    private static List<Persona>listaMensajes = new ArrayList<>();
    
    /**
     * obtiene nombre 
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Envia variable nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * obtiene ciudad
     * @return 
     */
    public String getCiudad() {
        return ciudad;
    }
    /**
     * envia variable ciudad
     * @param ciudad 
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    /**
     * obtiene correo
     * @return 
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * envia correo
     * @param correo 
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * obtiene lista de personas
     * @return 
     */
    public List<Persona> getLista() {
        return lista;
    }
    /**
     * envia lista de personas
     * @param lista 
     */
    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }
    /**
     * obtiene mensaje
     * @return 
     */
    public String getMensaje() {
        return mensaje;
    }
    /**
     * envia mensaje
     * @param mensaje 
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    /**
     * Obtiene lista mensajes
     * @return 
     */
    public List<Persona> getListaMensajes() {
        return listaMensajes;
    }
    /**
     * envia lista de mensajes
     * @param listaMensajes 
     */
    public void setListaMensajes(List<Persona> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    
    
    /**
     * Creates a new instance of Controlp1
     */
    public Controlp1() {
    }
    
    /**
     * Funcion que recibe las variables desde la vista y las setea al modelo para agregar a la lista
     */
    public void agregarPersona(){
        Persona auxp = new Persona();
        auxp.setNombreP(nombre);
        auxp.setCiudadP(ciudad);
        auxp.setCorreoP(correo);
        
        auxp.agregarPersona(nombre, ciudad, correo);
        Persona nw = new Persona(nombre,correo,ciudad);
        lista.add(nw);
        agregarPush();
    }
    /**
     * Funcion que edita la informacion de la lista
     * @param event 
     */
    public void Editar(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Usuario Editado", ((Persona) event.getObject()).getNombreP());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        editarPush();
    }
     /**
      * Funcion que cancela la edicion de la informacion de la lista
      * @param event 
      */
    public void Cancelar(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada", ((Persona) event.getObject()).getNombreP());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     /**
      * Funcion que verifica la edicion exitosa
      * @param event 
      */
    public void Editado(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha cambiado:", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    /**
     * Funcion que elimina de la lista la persona seleccionada
     * 
     * @param correo 
     */
    public void eliminarP(String correo){
        List<Persona>otra = new ArrayList();
        for(Persona p : lista){
            if(p.getCorreoP().equals(correo)){
                otra.add(p);
            }
        }
        lista.removeAll(otra);
        eliminarPush();
     }
    
    /**
     * Funcion que genera el mensaje, el tipo  de accion y la notificacion para el administrador
     */
    public void agregarPush(){
        try {
            mensaje = "se agrego usuario: " + correo;
            String summary= "ADICION";
            String CHANNEL= "/notify";
            listaMensajes.add(new Persona(mensaje));
            EventBus canal = EventBusFactory.getDefault().eventBus();
            canal.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(mensaje)));
        } catch (Exception ex) {
                
        }   
    }
    /**
     * Funcion que genera el mensaje,tipo de accion y notificacion de edicion para el administrador
     */
    public void editarPush(){
        try {
            mensaje = "Se modifico lista: " + correo;
            String summary= "MODIFICACION";
            String CHANNEL= "/notify";
            listaMensajes.add(new Persona(mensaje));
            EventBus canal = EventBusFactory.getDefault().eventBus();
            canal.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(mensaje)));
        } catch (Exception ex) {
                
        }   
    }
    /**
     * Funcion que genera el mensaje,tipo de accion y notificacion de eliminacion para el administrador
     */
    public void eliminarPush(){
        try {
            mensaje = "se elimino de la lista " + correo;
            String summary= "ELIMINACION";
            String CHANNEL= "/notify";
            listaMensajes.add(new Persona(mensaje));
            EventBus canal = EventBusFactory.getDefault().eventBus();
            canal.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(mensaje)));
        } catch (Exception ex) {
                
        }   
    }
}
