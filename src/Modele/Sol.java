/**
 * Classe Sol représente une case vide où le joueur ou une caisse peut se déplacer.
 */
package Modele;

public class Sol extends Element {
    /**
     * Retourne le symbole représentant un sol.
     *
     * @return Le caractère ' '.
     */
    @Override
    public char getSymbole() {
        return ' ';
    }
}
