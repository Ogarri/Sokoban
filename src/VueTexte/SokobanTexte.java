package VueTexte;

import Modele.Carte;
import Modele.Direction;
import Modele.Lecture;
import java.io.IOException; // Import the Direction class or enum
import java.util.List;

public class SokobanTexte {
    public static void main(String[] args) {
        // Lecture de la carte depuis le fichier map4.txt
        List<String> carteTexte;
        try {
            Lecture lecture = new Lecture("bin/map/map4.txt");
            carteTexte = lecture.getLignes();
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            return;
        }

        // Création de l'instance de Carte
        Carte carte = new Carte(carteTexte);

        // Création de l'instance de ModeTexte
        ModeTexte modeTexte = new ModeTexte(carte);

        // Lancement de la partie
        modeTexte.lancerPartie();

        // Boucle pour gérer les déplacements
        while (!carte.estTerminee()) {
            modeTexte.afficherCarte(); // Affiche la carte actuelle
            String deplacement = modeTexte.lireDeplacement(); // Lit le déplacement du joueur
            Direction direction;
            try {
                direction = Direction.valueOf(deplacement.toUpperCase()); // Convertit le déplacement en Direction
            } catch (IllegalArgumentException e) {
                System.err.println("Déplacement invalide: " + deplacement);
                continue; // Ignore le déplacement invalide et continue la boucle
            }
            carte.deplacerJoueur(direction); // Met à jour la carte avec le déplacement
        }

        System.out.println("Félicitations, vous avez terminé le niveau !");
    }
}
