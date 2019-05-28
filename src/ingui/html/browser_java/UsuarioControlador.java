/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import ingui.javafx.browser_java.FXMLDocumentController;
import innui.archivos.Archivos;
import innui.http.Url_operaciones;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author daw
 */
public class UsuarioControlador {

    public static int k_nota_un_punto = 1;
    public static double k_nota_penalizacion = 0.5;
/***
 * 
 * @param archivo Contiene el path del documento html que teniamos cargado
 * @param error Almacena un error en error[0] en caso de que ocurra algun error
 * @return Devulve un nuevo path que hace referencia a otro html si todo es correcto
 */
    public static String cambiar_nombre_archivo(String archivo, String[] error) {
        String retorno = null;
        Locale locale = Locale.getDefault();
        String codigo_idioma = locale.getLanguage();
        File file = new File(archivo);
        String path = file.getParent();
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos >= 0) {
            name = name.substring(0, pos) + "_" + codigo_idioma + name.substring(pos);
        } else {
            name = name + "_" + codigo_idioma;
        }
        String nuevo_archivo = path + File.separatorChar + name;
        URL url = IndexControlador.class.getResource(nuevo_archivo);
        if (url == null) {
            url = IndexControlador.class.getResource(archivo);
            if (url != null) {
                retorno = archivo;
            } else {
                error[0] = "No existen los archivos que cambiar. ";
            }
        } else {
            retorno = nuevo_archivo;
        }
        return retorno;
    }


/***
 * 
 * @param archivo Contiene el path del documento html que teniamos cargado
 * @param error Almacena un error en error[0] en caso de que ocurra algun error
 * @return Devulve un nuevo path que hace referencia a otro html si todo es correcto
 */
    public static String procesar(String url_texto, String[] error) {
        Salca sc = new Salca();
        String retorno = null;
        boolean ret = true;
        String respuesta;
        String solucion;
        double punto = 0.0;
        String comentario = ""; //NOI18N
        String calificacion;
        int preguntas_num = -1;
        String preguntas_num_texto;

        try {
            Map<String, String> query_mapa = new HashMap();
            URL url = new URL(url_texto);
            System.out.println(url);
            ret = Url_operaciones.extraer_parametros_query(url, query_mapa, error);

            if (sc.comprobarInstalcion(query_mapa) == false) {
                url_texto = "/ingui/html/browser_java/recursos/usuario.html";
                String archivo = Archivos.leer_archivo_texto(
                        url_texto, error); //NOI18N
                if (archivo != null) {
                        // cambiar por remplace de usuario
                    archivo = archivo.replace("${ruta}", query_mapa.get("ruta")); //NOI18N
                    archivo = archivo.replace("${nombreAdministrador}", query_mapa.get("nombreAdministrador"));
                    archivo = archivo.replace("${telefono}", query_mapa.get("telefono"));
                    archivo = archivo.replace("${email}", query_mapa.get("email"));

                    retorno = archivo;

                }
            } else {
                if (sc.comprobarInstalcion(query_mapa) == false) {
                } else {
                    String archivo_html = "/ingui/html/browser_java/recursos/fin.html";
                    archivo_html = cambiar_nombre_archivo(archivo_html, error);
                    if (archivo_html != null) {
                        String archivo = Archivos.leer_archivo_texto(
                                archivo_html, error); //NOI18N
                        if (archivo != null) {

                            retorno = archivo;
                        }
                    }
                }

            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("ingui/html/browser_java/recursos/int").getString("ERROR EN PROCESAR. {0}"), new Object[]{error[0]});
            ret = false;
            retorno = null;
        }
        return retorno;
    }
}
