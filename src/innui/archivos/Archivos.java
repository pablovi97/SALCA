/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Miguel González Socas
 * @author Pablo Viera
 */
public class Archivos {
/***
 * 
 * @param nombre Es el path del archivo html
 * @param error Almacena un error en error[0] en caso de que ocurra algun error
 * @return Devuelve el contenido del archivo html
 */
    public static String leer_archivo_texto(String nombre, String[] error) {
        // Leer bytes: InputStream / OutputStream
        // Leer caracteres: Reader / Writer
        String resultado = "";
        String texto = null;
        try {
            Class clase = Archivos.class;
            InputStream inputStream = clase.getResourceAsStream(nombre);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                texto = bufferedReader.readLine();
                if (texto == null) {
                    break;
                }
                resultado = resultado + texto;
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error en leer_archivo_texto. " + error[0];
            resultado = null;
        }
        return resultado;
    }
    /**
     * Para conseguir que los archivos de assets se copien a la carpeta de distribución (dist)
     * se debe modificar el archivo: built.xml
     * <pre>{@code 
     * <target name="-post-compile"> 
     *     <copy todir="${dist.dir}/assets">
     *         <fileset dir="assets" includes="**" />
     *     </copy>
     *  </target> 
     * }</pre>
     * @param clase Clase que solicita su ruta base (su directorio base (si es un archivo jar), o la ruta del paquete donde se encuentra (si no es un jar)
     * @param error Mensaje de error en la posición 0, si lo hay.
     * @return true si no hay errores, false si hay errores
     */
    public static String leer_ruta_base(Class clase, String[] error) {
        String resultado = "";
        File file;
        try {
            URL url = clase.getResource("");
            String forma = url.toExternalForm();
            String path = url.getPath();
            if (forma.startsWith("jar:")) {
                url = new URL(path);
                path = url.getPath();
                int pos = path.indexOf("!");
                if (pos >= 0) {
                    path = path.substring(0, pos);
                }
                file = new File(path);
                file = file.getParentFile();
            } else {
                String ruta_paquete;
                Package package_o = clase.getPackage();
                ruta_paquete = package_o.getName();
                ruta_paquete = ruta_paquete.replace(".", "/");
                int pos = path.indexOf(ruta_paquete);
                if (pos >= 0) {
                    path = path.substring(0, pos);
                }
                file = new File(path);
            }
            resultado = file.getAbsolutePath();
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = "";
            }
            error[0] = "Error en leer_archivo_texto. " + error[0];
            resultado = null;
        }
        return resultado;
    }

}
