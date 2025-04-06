/**
 * Classe Destination représente une case cible où une caisse doit être placée.
 */
package Modele;

public class Destination extends Sol {
    /**
     * Retourne le symbole représentant une destination.
     *
     * @return Le caractère '.'.
     */
    @Override
    public char getSymbole() {
        return '.';
    }
}
