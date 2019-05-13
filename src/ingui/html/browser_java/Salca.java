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
 * @author pablo
 */
public class Salca {

    public Salca() {

    }

    public enum Ocupacion {
        ADMINISTRADOR,
        USUARIO,
        SISTEMADOR

    }

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

    public boolean comprobarCarpeta(String ruta) {
        boolean resultado = true;

        resultado = new File(ruta).exists();

        return resultado;

    }

    public static void main(String[] args) {
        
        
    }

}
