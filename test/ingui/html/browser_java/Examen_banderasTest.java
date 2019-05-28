/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.html.browser_java;

import ingui.javafx.browser_java.FXMLDocumentController;
import javafx.beans.value.ObservableValue;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Miguel González Socas
 */
public class Examen_banderasTest {
    
    public Examen_banderasTest() {
    }

    /**
     * Test of getfXMLDocumentController method, of class Examen_banderas.
     */
    @org.junit.Test
    public void testGetfXMLDocumentController() {
        System.out.println("getfXMLDocumentController");
        Examen_banderas instance = new Examen_banderas();
        FXMLDocumentController expResult = null;
        FXMLDocumentController result = instance.getfXMLDocumentController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setfXMLDocumentController method, of class Examen_banderas.
     */
    @org.junit.Test
    public void testSetfXMLDocumentController() {
        System.out.println("setfXMLDocumentController");
        FXMLDocumentController fXMLDocumentController = null;
        Examen_banderas instance = new Examen_banderas();
        instance.setfXMLDocumentController(fXMLDocumentController);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changed method, of class Examen_banderas.
     */
    @org.junit.Test
    public void testChanged() {
        System.out.println("changed");
        ObservableValue<? extends String> observable = null;
        String oldValue = "";
        String newValue = "";
        Examen_banderas instance = new Examen_banderas();
        instance.changed(observable, oldValue, newValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniciar_contenido method, of class Examen_banderas.
     */
    @org.junit.Test
    public void testIniciar_contenido() {
        System.out.println("iniciar_contenido");
        Class clase = null;
        String[] error = null;
        Examen_banderas instance = new Examen_banderas();
        String expResult = "";
        String result = instance.iniciar_contenido(clase, error);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
