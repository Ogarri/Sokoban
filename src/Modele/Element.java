/**
 * Classe abstraite Element représente un élément de la carte.
 * Les sous-classes doivent implémenter le symbole associé.
 */
package Modele;

public abstract class Element {
    /**
     * Retourne le symbole représentant l'élément.
     *
     * @return Le caractère associé à l'élément.
     */
    public abstract char getSymbole();
    
    public boolean aJoueur() {
        return false;
    }
    
    public boolean aCaisse() {
        return false;
    }
}