package VueTexte;

import Modele.Carte;
import Modele.Direction;
import Modele.Outil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModeTexte {
    private final Carte carte;
    private final Map<Character, Direction> commandes;

    public ModeTexte(Carte carte) {
        this.carte = carte;
        this.commandes = new HashMap<>();
        commandes.put('q', Direction.GAUCHE);
        commandes.put('z', Direction.HAUT);
        commandes.put('d', Direction.DROITE);
        commandes.put('s', Direction.BAS);
    }

    // Méthode pour afficher l'état actuel de la carte
    public void afficherCarte() {
        System.out.println(carte.toString());
    }

    // Méthode pour lire le déplacement du joueur
    public String lireDeplacement() {
        System.out.print("Entrez un déplacement (z=haut, s=bas, q=gauche, d=droite) : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();

        if (commandes.containsKey(input.charAt(0))) {
            carte.deplacerJoueur(commandes.get(input.charAt(0)));
            afficherCarte(); // Mettre à jour l'affichage après chaque déplacement
        } else {
            System.out.println("Déplacement invalide !");
        }
        return input;
    }

    private char lireCommande() {
        Scanner scanner = new Scanner(System.in);
        char commande;
        do {
            System.out.print("Entrez une commande (q: gauche, z: haut, d: droite, s: bas) : ");
            String input = scanner.nextLine().trim().toLowerCase();
            commande = input.isEmpty() ? ' ' : input.charAt(0);
        } while (!commandes.containsKey(commande));
        return commande;
    }

    public void lancerPartie() {
        System.out.println("Début de la partie !");
        afficherCarte(); // Afficher la carte initiale

        while (!carte.finDePartie()) {
            char commande = lireCommande();
            Direction direction = commandes.get(commande);
            carte.deplacerJoueur(direction);
            afficherCarte(); // Mettre à jour l'affichage après chaque déplacement
        }

        System.out.println("Fin de la partie !");
    }
}
