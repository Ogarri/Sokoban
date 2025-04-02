package VueTexte;

import Modele.Carte;
import Modele.Direction;
import Modele.Lecture;
import java.io.IOException;
import java.util.List;

public class SokobanTexte {
    public static void main(String[] args) {
        List<String> carteTexte;
        try {
            Lecture lecture = new Lecture("bin/map/map4.txt");
            carteTexte = lecture.getLignes();
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            return;
        }

        Carte carte = new Carte(carteTexte);

        ModeTexte modeTexte = new ModeTexte(carte);

        modeTexte.lancerPartie();

        while (!carte.estTerminee()) {
            modeTexte.afficherCarte();
            String deplacement = modeTexte.lireDeplacement();
            Direction direction;
            try {
                direction = Direction.valueOf(deplacement.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Déplacement invalide: " + deplacement);
                continue;
            }
            carte.deplacerJoueur(direction);
        }

        System.out.println("Félicitations, vous avez terminé le niveau !");
    }
}
