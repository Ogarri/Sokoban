/**
 * Classe Vide représente une case inutilisée dans la carte.
 */
package Modele;

public class Vide extends Element {
    /**
     * Retourne le symbole représentant une case vide.
     *
     * @return Le caractère ' '.
     */
    @Override
    public char getSymbole() {
        return ' ';
    }
}
