package Modele;

public class Joueur extends Element {
    private final boolean surDestination;
    private int x;
    private int y;

    public Joueur(boolean surDestination) {
        this.surDestination = surDestination;
    }

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

    @Override
    public char getSymbole() {
        return surDestination ? '+' : '@';
    }

    @Override
    public boolean aJoueur() {
        return true;
    }
}
