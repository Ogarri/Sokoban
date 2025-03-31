package Modele;

import java.io.IOException;
import java.util.List;

public class Carte {
    private final int largeur;
    private final int hauteur;
    private final Element[][] grille;
    private final List<String> lignes;
    private List<String> carteTexte;
    private Joueur joueur;
    private List<Case> cases; // Déclaration de la liste des cases

    // Constructeur acceptant une List<String>
    public Carte(List<String> carteTexte) {
        this.carteTexte = carteTexte;
        this.lignes = carteTexte;
        this.hauteur = lignes.size();
        this.largeur = lignes.get(0).length();
        this.grille = new Element[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            String ligne = lignes.get(y);
            for (int x = 0; x < largeur; x++) {
                char c = ligne.charAt(x);
                Element element = creerElement(c);
                grille[y][x] = element;
                if (element instanceof Joueur joueurElement) {
                    joueur = joueurElement;
                    joueur.setPosition(x, y);
                }
            }
        }
    }

    public Carte(String nomFichier) throws IOException {
        Lecture lecture = new Lecture(nomFichier);
        this.lignes = lecture.getLignes();
        this.hauteur = lignes.size();
        this.largeur = lignes.get(0).length();
        this.grille = new Element[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            String ligne = lignes.get(y);
            for (int x = 0; x < largeur; x++) {
                char c = ligne.charAt(x);
                Element element = creerElement(c);
                grille[y][x] = element;
                if (element instanceof Joueur joueurElement) {
                    joueur = joueurElement;
                    joueur.setPosition(x, y);
                }
            }
        }
    }

    private Element creerElement(char c) {
        return switch (c) {
            case '#' -> new Mur();
            case ' ' -> new Sol();
            case '.' -> new Destination();
            case '$' -> new Caisse(false);
            case '*' -> new Caisse(true);
            case '@' -> new Joueur(false);
            case '+' -> new Joueur(true);
            case '/' -> new Vide();
            default -> throw new IllegalArgumentException("Caractère inconnu : " + c);
        };
    }

    public void deplacerJoueur(Direction direction) {
        int newX = joueur.getX() + direction.getDx();
        int newY = joueur.getY() + direction.getDy();

        if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
            Element destination = grille[newY][newX];

            if (destination instanceof Sol || destination instanceof Destination) { // Vérifie que la destination est un Sol ou une Destination
                grille[joueur.getY()][joueur.getX()] = (grille[joueur.getY()][joueur.getX()] instanceof Joueur joueurActuel && joueurActuel.isSurDestination()) 
                                                        ? new Destination() // Si le joueur était sur une destination, restaure la destination
                                                        : new Sol(); // Sinon, restaure un sol
                joueur.setPosition(newX, newY);
                grille[newY][newX] = (destination instanceof Destination) 
                                     ? new Joueur(true) // Transforme en joueur sur destination
                                     : joueur; // Sinon, joueur normal
                mettreAJourAffichage(); // Mise à jour de l'affichage après modification
            } else if (destination instanceof Caisse caisse) { // Vérifie que la destination est une Caisse
                if (caisse.isSurDestination()) {
                    return; // Empêche de pousser une caisse sur une destination
                }
                int nextX = newX + direction.getDx();
                int nextY = newY + direction.getDy();

                if (nextX >= 0 && nextX < largeur && nextY >= 0 && nextY < hauteur) {
                    if (grille[nextY][nextX] instanceof Destination) {
                        grille[nextY][nextX] = new Caisse(true); // Transforme en caisse sur destination
                    } else {
                        grille[nextY][nextX] = caisse; // Déplace la caisse normalement
                    }
                    grille[newY][newX] = joueur;
                    grille[joueur.getY()][joueur.getX()] = (grille[joueur.getY()][joueur.getX()] instanceof Destination) 
                                                            ? new Destination() 
                                                            : new Sol(); // Restaure correctement la case précédente
                    joueur.setPosition(newX, newY);
                    mettreAJourAffichage(); // Mise à jour de l'affichage après modification
                }
            }
        }
    }

    private void mettreAJourAffichage() {
        System.out.println(this); // Affiche l'état actuel de la carte
    }

    public boolean finDePartie() {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Element element = grille[y][x];
                if (element instanceof Caisse caisse && !caisse.isSurDestination()) {
                    return false; // Si une caisse n'est pas sur une destination, la partie n'est pas terminée
                }
            }
        }
        return true; // Toutes les caisses sont sur des destinations
    }

    // Méthode pour vérifier si le jeu est terminé
    public boolean estTerminee() {
        // Implémentez la logique pour vérifier si tous les objectifs sont atteints
        // Exemple : vérifiez si toutes les cases cibles contiennent des caisses
        for (Case c : cases) { // Supposons que 'cases' est une liste des cases de la carte
            if (c.estCible() && !c.contientCaisse()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                sb.append(grille[y][x].getSymbole()); // Utilise getSymbole() pour chaque élément
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Classe interne ou externe représentant une case de la carte
    public static class Case {
        private boolean estCible;
        private boolean contientCaisse;

        public Case(boolean estCible, boolean contientCaisse) {
            this.estCible = estCible;
            this.contientCaisse = contientCaisse;
        }

        public boolean estCible() {
            return estCible;
        }

        public boolean contientCaisse() {
            return contientCaisse;
        }

        public void setContientCaisse(boolean contientCaisse) {
            this.contientCaisse = contientCaisse;
        }
    }

    // Exemple d'utilisation de la liste 'cases'
    public void afficherCases() {
        for (Case c : cases) {
            // Ajoutez ici le traitement pour chaque case
        }
    }
}