package org.Controller.Parsing;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.Models.GameElements.GameSettings;

/**
 * @author Lorenzo Toscano - 110204
 * è responsabile del caricamento delle impostazioni di gioco
 * fornisce un metodo per caricare le impostazioni da un file di testo.
*/
public class SettingsLoader {
    /**
     * Carica le impostazioni di gioco da un file di testo.
     *
     * @param pathOfSetting il percorso del file di testo che contiene le impostazioni di gioco
     * @return un oggetto GameSettings che rappresenta le impostazioni di gioco caricate
     * @throws Exception se si verifica un errore durante la lettura del file
     */

    public GameSettings LoadSettings(String pathOfSetting) throws Exception {
        GameSettings gameSet = new GameSettings();
        Files.readAllLines(Paths.get(pathOfSetting))
             .forEach(x -> {
                String[] words = x.split(" "); // Divide ogni riga in parole separate da spazi
                if(words.length == 3) // Se la riga contiene esattamente 3 parole, imposta il valore della terza parola come impostazione
                    gameSet.setSetting(words[0], words[2]);
                else { // Altrimenti, combina tutte le parole dalla terza in poi in una singola stringa
                    String value = Arrays.asList(Arrays.copyOfRange(words, 2, words.length))
                                         .stream().reduce("", (a, b) -> a + " " + b);
                    gameSet.setSetting(words[0], value); // Imposta questa stringa combinata come valore dell'impostazione
                }
             });
        return gameSet; // Ritorna l'oggetto GameSettings con tutte le impostazioni caricate
    }
    
}
