/**
 * Classe Joueur représente le joueur dans le jeu.
 * Le joueur peut être sur une destination ou non.
 */
package Modele;

public class Joueur extends Element {
    private boolean surDestination;
    private int x;
    private int y;

    public Joueur(boolean surDestination) {
        this.surDestination = surDestination;
    }

    /**
     * Définit la position du joueur.
     *
     * @param x La position horizontale.
     * @param y La position verticale.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSurDestination() {
        return surDestination;
    }

    public void setSurDestination(boolean surDestination) {
        this.surDestination = surDestination;
    }

    /**
     * Retourne le symbole représentant le joueur.
     *
     * @return '+' si le joueur est sur une destination, sinon '@'.
     */
    @Override
    public char getSymbole() {
        return surDestination ? '+' : '@';
    }

    @Override
    public boolean aJoueur() {
        return true;
    }
}
