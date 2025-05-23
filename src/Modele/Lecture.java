/**
 * Classe Lecture permet de lire une carte depuis un fichier texte.
 */
package Modele;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lecture {
    private final List<String> lignes;
    private final int nombreDeLignes;
    private final int tailleDesLignes;

    public Lecture(String nomFichier) throws IOException {
        lignes = Files.readAllLines(Paths.get(nomFichier));
        nombreDeLignes = lignes.size();
        tailleDesLignes = lignes.isEmpty() ? 0 : lignes.get(0).length();
    }

    /**
     * Retourne les lignes lues depuis le fichier.
     *
     * @return Une liste de chaînes représentant les lignes.
     */
    public List<String> getLignes() {
        return lignes;
    }

    public int getNombreDeLignes() {
        return nombreDeLignes;
    }

    public int getTailleDesLignes() {
        return tailleDesLignes;
    }
}
