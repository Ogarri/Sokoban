package Modele;

public abstract class Element {
    public abstract char getSymbole();
    
    public boolean aJoueur() {
        return false;
    }
    
    public boolean aCaisse() {
        return false;
    }
}