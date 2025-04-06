/**
 * Classe Sokoban permet de jouer à Sokoban en mode graphique.
 */
package VueGraphique;

import Modele.Carte;
import Modele.Lecture;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class Sokoban {
    /**
     * Point d'entrée principal pour jouer à Sokoban en mode graphique.
     *
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        String[] cartes = {
            "bin/map/map1.txt",
            "bin/map/map2.txt",
            "bin/map/map3.txt"
        };

        try {
            for (String cheminCarte : cartes) {
                List<String> carteTexte = new Lecture(cheminCarte).getLignes();
                Carte carte = new Carte(carteTexte);

                JFrame frame = new JFrame("Sokoban");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);

                VueSokoban vue = new VueSokoban(carte);
                frame.add(vue);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                vue.setResetCallback(() -> {
                    System.out.println("Réinitialisation du niveau...");
                    carte.reinitialiser();
                    vue.repaint();
                });

                while (!carte.finDePartie()) {
                    Thread.sleep(100);
                }

                frame.dispose();
            }

            System.out.println("Félicitations, vous avez terminé toutes les cartes !");
        } catch (IOException | InterruptedException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
