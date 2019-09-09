/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Modelo.Persona;
import javax.faces.application.FacesMessage;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author NATHALY
 */
@PushEndpoint("/notify")
/**
 *Clase que sirve de canal para la comunicacion entre usuario y administrador
 */
public class Notificacion {  
    Persona per = new Persona();
    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage msg){
        per.setMensaje(msg.getDetail());
        per.guardaMensajes(per.getMensaje());
        
        return msg;
    }
    
}
