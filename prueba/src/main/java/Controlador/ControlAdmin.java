/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Persona;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import javax.faces.bean.RequestScoped;

/**
 *
 * @author NATHALY
 */
@Named(value = "controlA")
@RequestScoped
public class ControlAdmin implements Serializable {

    /**
     * Creates a new instance of ControlAdmin
     */
    
     public ControlAdmin() {
    }
    /**
     * Propiedad para hacer un llamado de las propiedades del Bean administrador
     */ 
    @ManagedProperty(value="#{controlp1}")
    Controlp1 control;
    /**
     * obtiene variable control
     * @return 
     */
    public Controlp1 getControl() {
        return control;
    }
    /**
     * retorna variable control
     * @param control 
     */
    public void setControl(Controlp1 control) {
        this.control = control;
    }
    
   
    /**
     * Lista de mensaje de la persona
     * @return 
     */
    public List<Persona>mensajes(){
        return control.getListaMensajes();
    } 
}
