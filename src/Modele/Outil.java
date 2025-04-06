/**
 * Classe Outil fournit des utilitaires pour le jeu, comme la lecture d'entrée utilisateur.
 */
package Modele;

import java.util.Scanner;

public class Outil {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lit un caractère depuis l'entrée utilisateur.
     *
     * @return Le caractère lu.
     */
    public static char lireCar() {
        return scanner.next().charAt(0);
    }
}
