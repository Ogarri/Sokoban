package VueTexte;

import Modele.Carte;
import Modele.Direction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModeTexte {

    public static void main(String[] args) {
        try {
            // Initialisation de la carte avec un argument valide
            Carte carte = new Carte("defaultMap"); // Remplacez "defaultMap" par un argument approprié
            ModeTexte modeTexte = new ModeTexte(carte);
            modeTexte.lancerPartie();
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
    }

    // Méthode pour afficher l'état actuel de la carte
    public void afficherCarte() {
        System.out.println(carte.toString());
    }

    // Méthode pour lire le déplacement du joueur
    public char lireCommande() {
        Scanner scanner = new Scanner(System.in); // Scanner non fermé pour réutilisation
        char commande;
        do {
            System.out.print("Entrez un déplacement (z=haut, s=bas, q=gauche, d=droite) : ");
            String input = scanner.nextLine().trim().toLowerCase();
            commande = input.isEmpty() ? ' ' : input.charAt(0);
        } while (!commandes.containsKey(commande)); // Vérifie si la commande est valide
        return commande;
    }

    // Méthode pour lire le déplacement du joueur (alias pour lireCommande)
    public String lireDeplacement() {
        return String.valueOf(lireCommande());
    }

    public void lancerPartie() {
        System.out.println("Début de la partie !");
        afficherCarte(); // Afficher la carte initiale

        while (!carte.finDePartie()) {
            char commande = lireCommande();
            Direction direction = commandes.get(commande);
            if (direction != null) {
                carte.deplacerJoueur(direction);
                afficherCarte(); // Mettre à jour l'affichage après chaque déplacement
            }
        }

        System.out.println("Fin de la partie !");
    }
}
