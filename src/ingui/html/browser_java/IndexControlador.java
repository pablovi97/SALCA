/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import innui.archivos.Archivos;
import innui.http.Urls_operaciones;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author daw
 */
public class IndexControlador {

    public static String procesar(String url_texto, String[] error) {
        String retorno = null;
        boolean ret = true;
        String respuesta;
        String solucion;
        double punto = 0.0;
        String comentario = "";
        String calificacion;
        //int preguntas_num = -1;
        String name;
        String contrasenya;
        try {
            Map<String, String> query_mapa = new HashMap();
            URL url = new URL(url_texto);
            ret = Urls_operaciones.extraer_parametros_query(url, query_mapa, error);
            name = query_mapa.get("nombreAdministrador");
            System.out.println(name);
            if (name != null) {
               // preguntas_num = Integer.valueOf(name);
               
            }
            
            contrasenya = query_mapa.get("contrasenya");
            
            /*
            int i = 1;
            while (true) {
                respuesta = query_mapa.get("radio_pregunta_" + i);
                if (preguntas_num == -1) {
                    if (respuesta == null) {
                        break;
                    }
                } else {
                    if (i > preguntas_num) {
                        break;
                    }
                }
                if (respuesta != null) {
                    solucion = query_mapa.get("radio_solucion_" + i);
                    if (solucion != null) {
                        if (respuesta.equals(solucion)) {
                            punto = punto + 1;
                        } else {
                            punto = punto - 0.5;
                        }
                    }
                }
                i = i + 1;
            }
            i = 1;
            int j = 1;
            boolean sin_respuesta;
            while (true) {
                sin_respuesta = true;
                while (true) {
                    respuesta = query_mapa.get("checkbox_pregunta_" + i + "_" + j);
                    if (j > 4) {
                        break;
                    }
                    if (respuesta != null) {
                        punto = punto + Double.valueOf(respuesta);
                        sin_respuesta = false;
                    }
                    j = j + 1;
                }
                if (preguntas_num == -1) {
                    if (sin_respuesta) {
                        break;
                    }
                } else {
                    if (i > preguntas_num) {
                        break;
                    }
                }
                j = 1;
                i = i + 1;
            }
            if (punto <= 0) {
                calificacion = "<span style='color:red'>" + punto + "</span> ";
                comentario = "<span style='color:purple'>Tú contribuyes a tu futuro. Pero... puede ser que no esté aquí, el camino que te lleva a él.</span> ";
            } else {
                calificacion = "<span style='color:green'>" + punto + "</span> ";
                comentario = "<span style='color:olive'>Vas por buen camino. Todo esfuerzo que hagas será en tu propio beneficio.</span> ";
            }
            String archivo = Archivos.leer_archivo_texto(
                    "/ingui/html/browser_java/recursos/calificacion.html", error);
            if (archivo != null) {
                archivo = archivo.replace("${nota}", calificacion);
                archivo = archivo.replace("${Comentario}", comentario);
                retorno = archivo;
            }
*/
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error en procesar. " + error[0];
            ret = false;
            retorno = null;
        }
        return retorno;

}
}
