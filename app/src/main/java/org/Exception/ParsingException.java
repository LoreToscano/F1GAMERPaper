package org.Exception;

/**
 * @author Lorenzo Toscano - 110204
 * La classe ParsingException rappresenta un'eccezione che viene lanciata quando si verifica un errore di parsing.
 */
public class ParsingException extends Exception {
    
    /**
     * Costruttore della classe ParsingException.
     * Inizializza l'eccezione con un messaggio che indica la linea in cui si è verificato l'errore di parsing.
     *
     * @param line la linea in cui si è verificato l'errore di parsing
     */
    
    public ParsingException(int line){
        super("L'errore di parsing si e' verificato alla linea " + line);
    }

}
