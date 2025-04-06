/**
 * Classe ModeTexte permet de jouer à Sokoban en mode texte.
 */
package VueTexte;

import Modele.Carte;
import Modele.Direction;
import Modele.Lecture;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModeTexte {

    public static void main(String[] args) {
        try {
            String[] cartes = {
                "bin/map/map1.txt",
                "bin/map/map2.txt",
                "bin/map/map3.txt"
            };

            for (String cheminCarte : cartes) {
                Carte carte = new Carte(new Lecture(cheminCarte).getLignes());
                ModeTexte modeTexte = new ModeTexte(carte);
                modeTexte.lancerPartie();
            }

            System.out.println("Félicitations, vous avez terminé toutes les cartes !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation de la carte : " + e.getMessage());
        }
    }

    private final Carte carte;
    private final Map<Character, Direction> commandes;

    public ModeTexte(Carte carte) {
        this.carte = carte;
        this.commandes = new HashMap<>();
        commandes.put('q', Direction.GAUCHE);
        commandes.put('z', Direction.HAUT);
        commandes.put('d', Direction.DROITE);
        commandes.put('s', Direction.BAS);
        commandes.put('r', null);
    }

    /**
     * Affiche la carte actuelle dans la console.
     */
    public void afficherCarte() {
        System.out.println(carte.toString());
    }

    /**
     * Lit une commande de déplacement depuis l'entrée utilisateur.
     *
     * @return Le caractère correspondant à la commande.
     */
    public char lireCommande() {
        Scanner scanner = new Scanner(System.in);
        char commande;
        do {
            System.out.print("Entrez un déplacement (z=haut, s=bas, q=gauche, d=droite, r=réinitialiser) : ");
            String input = scanner.nextLine().trim().toLowerCase();
            commande = input.isEmpty() ? ' ' : input.charAt(0);
        } while (!commandes.containsKey(commande));
        return commande;
    }

    public String lireDeplacement() {
        return String.valueOf(lireCommande());
    }

    /**
     * Lance une partie en mode texte.
     */
    public void lancerPartie() {
        System.out.println("Début de la partie !");
        afficherCarte();

        while (!carte.finDePartie()) {
            char commande = lireCommande();
            if (commande == 'r') {
                System.out.println("Réinitialisation du niveau...");
                carte.reinitialiser();
                afficherCarte();
                continue;
            }
            Direction direction = commandes.get(commande);
            if (direction != null) {
                carte.deplacerJoueur(direction);
                afficherCarte();
            }
        }

        System.out.println("Fin de la partie !");
    }
}
