package org.Controller.Parsing;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.Exception.ParsingException;

/**
 * @author Lorenzo Toscano - 110204
 * La classe è responsabile della validazione dei file di impostazioni di gioco.
 * Questa classe fornisce un metodo per verificare la correttezza delle impostazioni caricate da un file di testo.
 */

public class SettingsParser {
    /**
     * Verifica la correttezza di un file di impostazioni di gioco.
     *
     * @param pathOfSetting il percorso del file di testo che contiene le impostazioni di gioco
     * @throws Exception se si verifica un errore durante la lettura del file
     * @throws ParsingException se una riga del file non contiene almeno 3 parole o se il secondo elemento non è un segno di uguale
     */

    public void parseSettingsFile(String pathOfSetting) throws Exception {
        // Legge tutte le righe del file specificato e le converte in una lista
        List<String> lines = Files.readAllLines(Paths.get(pathOfSetting)).stream().toList();
        for(int i = 0; i < lines.size(); i++) {
            String[] words = lines.get(i).split(" ");
            if(words.length < 3 || words[1].equals("=")) // Verifica se la riga contiene almeno 3 parole e se il secondo elemento è un segno di uguale
                throw new ParsingException(i); // Lancia un'eccezione ParsingException se la riga non è valida
        }
    }
    
}