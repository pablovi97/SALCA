/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import static ingui.html.browser_java.IndexControlador.cambiar_nombre_archivo;
import ingui.javafx.browser_java.App_browser_java;
import ingui.javafx.browser_java.FXMLDocumentController;
import innui.archivos.Archivos;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Miguel González Socas
 * @author Pablo Viera
 */
public class Examen_banderas implements App_browser_java {
    public FXMLDocumentController fXMLDocumentController = null;

    @Override
    public FXMLDocumentController getfXMLDocumentController() {
        return fXMLDocumentController;
    }

    @Override
    public void setfXMLDocumentController(FXMLDocumentController fXMLDocumentController) {
        this.fXMLDocumentController = fXMLDocumentController;
    }
/***
 * 
 * @param observable 
 * @param oldValue ruta del html cargado anteriormente
 * @param newValue  ruta del nuevo html cargado
 */
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        boolean ret = true;
        String contenido;
        String[] error = {""}; //NOI18N
        String url = newValue;
        
        try {
            if (url.startsWith("http://browser_java/index")) { //NOI18N
               
                contenido = IndexControlador.procesar(url, error);
                System.out.println(contenido);
                if (contenido != null) {
                    
                    ret = fXMLDocumentController.cargar_contenido(contenido, "text/html", error); //NOI18N
                } else {
                    ret = false;
                }
            }else if (url.startsWith("http://browser_java/sistemador")) { //NOI18N
               
                contenido = SistemadorControlador.procesar(url, error);
                System.out.println(contenido);
                if (contenido != null) {
                    
                    ret = fXMLDocumentController.cargar_contenido(contenido, "text/html", error); //NOI18N
                } else {
                    ret = false;
                }
            }else if (url.startsWith("http://browser_java/usuario")) { //NOI18N
               
                contenido = UsuarioControlador.procesar(url, error);
                System.out.println(contenido);
                if (contenido != null) {
                    
                    ret = fXMLDocumentController.cargar_contenido(contenido, "text/html", error); //NOI18N
                } else {
                    ret = false;
                }
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = java.util.ResourceBundle.getBundle("ingui/html/browser_java/recursos/int").getString("ERROR AL ANALIZAR EL CAMBIO DE URL. ");
            ret = false;
        }
        if (ret == false) {
            fXMLDocumentController.poner_error(error[0]);
        }
    }
/***
 * 
 * @param clase Recibe la clase en la que se inicia el programa
 * @param error Almacena un error en error[0] en caso de que ocurra algun error
 * @return Devuelve el contenido del archivo leido mediante el metodo leer:archivo_texto
 */
    @Override
    public String iniciar_contenido(Class clase, String [] error)
    {
        String archivo = "/ingui/html/browser_java/recursos/index.html"; //NOI18N
        String texto = null;
        String ruta = Archivos.leer_ruta_base(clase, error);
        if (ruta != null) {
            archivo = cambiar_nombre_archivo(archivo, error);
            
            if (archivo != null) {
                texto = Archivos.leer_archivo_texto(archivo, error);
            }
        }
        if (texto != null) {
            texto = texto.replaceAll("\\$\\{\\s*browser_java_ruta\\s*\\}", ruta); //NOI18N
            
        }
        return texto;
    }    
}
