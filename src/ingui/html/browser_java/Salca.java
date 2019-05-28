/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import java.io.File;
import java.util.Map;

/**
 *
 * @author Miguel González Socas
 * @autor Pablo Viera
 */
public class Salca {

    public Salca() {

    }

    public enum Ocupacion {
        ADMINISTRADOR,
        USUARIO,
        SISTEMADOR

    }
    /***
     * 
     * @param ruta Recibe la ruta de instalacion 
     * @param name Recibe el nombre de la carpeta que se va a crear
     * @param ocupacion Enum con los tipos de usuarios
     */
    public void crearCarpetas(String ruta, String name, Ocupacion ocupacion) {
        //pasarle la url para que haga las carpetas de los diferentes html

        try {
            File directorio = new File(ruta + File.separatorChar + "Administradores"); // \\ si es windows

            directorio.mkdir();
            directorio = new File(ruta + File.separatorChar + "Usuarios");
            directorio.mkdir();
            directorio = new File(ruta + File.separatorChar + "Sistemadores");
            directorio.mkdir();
            if (ocupacion.ADMINISTRADOR == Ocupacion.ADMINISTRADOR) {
                directorio = new File(ruta + File.separatorChar + "Administradores" + File.separatorChar + name);
                directorio.mkdir();
            } else if (ocupacion.USUARIO == Ocupacion.USUARIO) {
                directorio = new File(ruta +File.separatorChar + "Usuario" +File.separatorChar + name);
                directorio.mkdir();

            } else {
                directorio = new File(ruta +File.separatorChar + "Sistemador" + File.separatorChar+ name);
                directorio.mkdir();
            }

        } catch (Exception e) {
            System.out.println(ruta);
        }

    }
    /***
     * 
     * @param comprobaciones Mapa con los parametros extraidos del html
     * @return True si todo fue bien y False si la ruta no es correcta
     */
    public boolean comprobarInstalcion(Map<String, String> comprobaciones) {
        boolean resultado = true;
        resultado = comprobarCarpeta(comprobaciones.get("ruta"));
        if (resultado) {
            resultado = comprobaciones.values().stream()
                    .filter(p -> p != "01" && p!="resetear")
                    .allMatch(texto -> texto.length() > 0);
            
            System.out.println(resultado);
            for (String value : comprobaciones.values()) {
                
                System.out.println(value.length());
            }
            
        }
        if (resultado) {
            crearCarpetas(comprobaciones.get("ruta"), comprobaciones.get("nombreAdministrador"), Ocupacion.ADMINISTRADOR);
            // crearCarpetas(comprobaciones.get("ruta"), comprobaciones.get("name"));
        }
        return resultado;
    }

    /***
     * 
     * @param ruta Recibe la ruta de instalacion 
     * @return Devuelve true si todo va bien y false si la ruta no exista
     */
    public boolean comprobarCarpeta(String ruta) {
        boolean resultado = true;

        resultado = new File(ruta).exists();

        return resultado;

    }
}
