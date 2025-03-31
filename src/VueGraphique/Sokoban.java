package VueGraphique;

import Modele.Carte;
import Modele.Lecture;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Sokoban {
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

        // Création de la fenêtre principale
        JFrame frame = new JFrame("Sokoban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Ajout de la vue graphique
        VueSokoban vue = new VueSokoban(carte);
        frame.add(vue);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
