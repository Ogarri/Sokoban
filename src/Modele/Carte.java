package Modele;

import java.io.IOException;
import java.util.List;

public class Carte {
    private final int largeur;
    private final int hauteur;
    private final Element[][] grille;
    private final List<String> lignes;
    private final List<String> carteInitiale;
    private List<String> carteTexte;
    private Joueur joueur;
    private List<Case> cases;

    public Carte(List<String> carteTexte) {
        this.carteTexte = carteTexte;
        this.carteInitiale = List.copyOf(carteTexte);
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
        this.carteInitiale = List.copyOf(lignes);
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

            if (destination instanceof Sol || destination instanceof Destination) {
                grille[joueur.getY()][joueur.getX()] = (joueur.isSurDestination()) 
                                                        ? new Destination()
                                                        : new Sol();
                joueur.setPosition(newX, newY);
                joueur.setSurDestination(destination instanceof Destination);
                grille[newY][newX] = joueur;
                mettreAJourAffichage();
            } else if (destination instanceof Caisse caisse) {
                int nextX = newX + direction.getDx();
                int nextY = newY + direction.getDy();

                if (nextX >= 0 && nextX < largeur && nextY >= 0 && nextY < hauteur) {
                    Element nextDestination = grille[nextY][nextX];

                    if (nextDestination instanceof Sol || nextDestination instanceof Destination) {
                        grille[nextY][nextX] = (nextDestination instanceof Destination) 
                                               ? new Caisse(true)
                                               : new Caisse(false);
                        grille[newY][newX] = (caisse.isSurDestination()) 
                                             ? new Destination()
                                             : new Sol();
                        grille[joueur.getY()][joueur.getX()] = (joueur.isSurDestination()) 
                                                                ? new Destination()
                                                                : new Sol();
                        joueur.setPosition(newX, newY);
                        joueur.setSurDestination(destination instanceof Destination);
                        grille[newY][newX] = joueur;
                        mettreAJourAffichage();
                    }
                }
            }
        }
    }

    private void mettreAJourAffichage() {
        System.out.println(this);
    }

    public boolean finDePartie() {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Element element = grille[y][x];
                if (element instanceof Caisse caisse && !caisse.isSurDestination()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estTerminee() {
        for (Case c : cases) {
            if (c.estCible() && !c.contientCaisse()) {
                return false;
            }
        }
        return true;
    }

    public void reinitialiser() {
        for (int y = 0; y < hauteur; y++) {
            String ligne = carteInitiale.get(y);
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

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Element[][] getGrille() {
        return grille;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                sb.append(grille[y][x].getSymbole());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

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

    public void afficherCases() {
        for (Case c : cases) {
        }
    }
}

//Faire un système de mémoire qui mémorise toutes les cases "." en début de partie.
//C'est a dire que une case "." ne peut pas devenir une case ".", si une case " " était au départ une case "." alors celle si se change en case "."

//Si ça ne fonctionne pas alors faire en sorte qu'une case "+" qui devenir une case " " soit une case "."