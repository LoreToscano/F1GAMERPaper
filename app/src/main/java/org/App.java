package org;

import org.View.FormulaUnoConsoleController;

/**
 * @author Lorenzo Toscano - 110204
 * Classe principale dell'applicazione.
 */

public class App {


     /**
     * Metodo principale dell'applicazione
     * @param args gli argomenti della riga di comando
     * @throws Exception se si verifica un errore durante l'esecuzione
     */

    public static void main(String[] args) throws Exception {
        new FormulaUnoConsoleController().run();
    }
}
