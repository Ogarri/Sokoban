/**
 * Classe Caisse représente une caisse dans le jeu.
 * Elle peut être sur une destination ou non.
 */
package Modele;

public class Caisse extends Element {
    private final boolean surDestination;

    /**
     * Constructeur de la classe Caisse.
     *
     * @param surDestination Indique si la caisse est sur une destination.
     */
    public Caisse(boolean surDestination) {
        this.surDestination = surDestination;
    }

    /**
     * Indique si la caisse est sur une destination.
     *
     * @return true si la caisse est sur une destination, sinon false.
     */
    public boolean isSurDestination() {
        return surDestination;
    }

    /**
     * Retourne le symbole représentant une caisse.
     *
     * @return '*' si la caisse est sur une destination, sinon '$'.
     */
    @Override
    public char getSymbole() {
        return surDestination ? '*' : '$';
    }

    @Override
    public boolean aCaisse() {
        return true;
    }
}

