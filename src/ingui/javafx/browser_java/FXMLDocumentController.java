/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

import ingui.html.browser_java.IndexControlador;
import ingui.html.browser_java.SistemadorControlador;
import ingui.html.browser_java.UsuarioControlador;
import innui.archivos.Archivos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 *
 * @author Miguel González Socas
 * @author Pablo Viera
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private WebView webView;

    static WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean ret = true;
        String[] error = {""};
        webEngine = webView.getEngine();
        ret = poner_escuchador_de_url(error);
        String archivo = "/ingui/html/browser_java/recursos/index.html";
        String texto = null;
        String ruta = Archivos.leer_ruta_base(this.getClass(), error);
        if (ruta != null) {
            texto = Archivos.leer_archivo_texto(archivo, error);
        }
        if (texto != null) {
            texto = texto.replace("${browser_java_ruta}", ruta);
            System.out.println(texto);
            texto = texto.replace("${ruta}", "");
            texto = texto.replace("${nombreAdministrador}", "");
            texto = texto.replace("${telefono}", "");
            texto = texto.replace("${email}", "");

             webEngine.loadContent(texto);

        }

    }


/***
 * 
 * @param error Almacena un error en error[0] en caso de que ocurra algun error
 * @return Devulve true si todo es correcto a la hora de cambiar el path del html
 */
    public boolean poner_escuchador_de_url(String[] error) {
        boolean ret = true;
        WebEngine webEngine = this.webView.getEngine();
        ReadOnlyStringProperty readOnlyStringProperty = webEngine.locationProperty();
        readOnlyStringProperty.addListener(new ChangeListener<String>() {
            @Override

            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean ret = true;
                String contenido;
                String[] error = {""};
                String url = newValue;
                try {
                    if (url.startsWith("http://browser_java/index")) {
                        contenido = IndexControlador.procesar(url, error);
                        if (contenido != null) {
                            ret = cargar_contenido(contenido, "text/html", error);
                        } else {
                            ret = false;
                        }
                    }else  if (url.startsWith("http://browser_java/sistemador")) {
                        contenido = SistemadorControlador.procesar(url, error);
                        if (contenido != null) {
                            ret = cargar_contenido(contenido, "text/html", error);
                        } else {
                            ret = false;
                        }
                    }else  if (url.startsWith("http://browser_java/usuario")) {
                        contenido = UsuarioControlador.procesar(url, error);
                        if (contenido != null) {
                            ret = cargar_contenido(contenido, "text/html", error);
                        } else {
                            ret = false;
                        }
                    }   
                } catch (Exception e) {
                    error[0] = e.getMessage();
                    if (error[0] == null) {
                        error[0] = "";
                    }
                    error[0] = "Error al analizar el cambio de URL. ";
                    ret = false;
                }
                if (ret == false) {
                    poner_error(error[0]);
                }
            }
        });
        return ret;
    }
    /***
     * 
     * @param mensaje Recibe el mensaje de error que desea reflejar
     * @return Devuelve true si todo va bien o false si algo no va como debería
     */
    public boolean poner_error(String mensaje) {
        String[] error = {""};
        return cargar_contenido(mensaje, "text/html", error);
    }
    /***
     * 
     * @param contenido Contiene el contenido de un documento
     * @param tipo_contenido Especifica el tipo del contenido como en este caso html/text
     * @param error Almacena un error en error[0] en caso de que ocurra algun error
     * @return Devuelve true si todo es correcto y false si algo falla
     */
    public boolean cargar_contenido(String contenido, String tipo_contenido, String[] error) {
        boolean ret = true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WebEngine webEngine = webView.getEngine();
                webEngine.loadContent(contenido, tipo_contenido);
            }

        });
        return ret;

    }
}
