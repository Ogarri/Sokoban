/**
 * Classe Mur représente un mur dans le jeu.
 */
package Modele;

public class Mur extends Element {
    /**
     * Retourne le symbole représentant un mur.
     *
     * @return Le caractère '#'.
     */
    @Override
    public char getSymbole() {
        return '#';
    }
}