/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import java.io.File;

/**
 *
 * @author pablo
 */
public class Salca {

    public void crearCarpetas(String ruta, String name) {

        try {
            File directorio = new File(ruta + "\\" + name);

            directorio.mkdir();

        } catch (Exception e) {
            System.out.println(ruta);
        }

    }

    public Salca() {

    }

    public static void main(String[] args) {
        Salca sc = new Salca();
        sc.crearCarpetas("C:\\Users\\pablo\\Downloads", "usuario");
    }

}
