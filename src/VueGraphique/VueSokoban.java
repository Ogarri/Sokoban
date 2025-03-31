package VueGraphique;

import Modele.Carte;
import Modele.Direction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class VueSokoban extends JPanel {
    private final Carte carte;
    private final Map<Character, ImageIcon> images;

    public VueSokoban(Carte carte) {
        this.carte = carte;
        this.images = new HashMap<>();
        chargerImages();
        setPreferredSize(new Dimension(carte.getLargeur() * 32, carte.getHauteur() * 32)); // Taille des cases 32x32

        // Ajout du KeyListener
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction direction = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Z -> direction = Direction.HAUT;
                    case KeyEvent.VK_Q -> direction = Direction.GAUCHE;
                    case KeyEvent.VK_S -> direction = Direction.BAS;
                    case KeyEvent.VK_D -> direction = Direction.DROITE;
                }
                if (direction != null) {
                    carte.deplacerJoueur(direction);
                    repaint(); // Met à jour l'affichage après un déplacement
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Rien à faire ici
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Rien à faire ici
            }
        });
    }

    private void chargerImages() {
        images.put('$', new ImageIcon("bin/img/caisse1.gif"));
        images.put('+', new ImageIcon("bin/img/Bas.gif.gif"));
        images.put('#', new ImageIcon("bin/img/mur.gif"));
        images.put(' ', new ImageIcon("bin/img/sol.gif"));
        images.put('.', new ImageIcon("bin/img/but.gif")); // Ajout de l'image pour '.'
        images.put('@', new ImageIcon("bin/img/Bas.gif"));
        images.put('*', new ImageIcon("bin/img/caisse2.gif"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                char symbole = carte.getGrille()[y][x].getSymbole();
                ImageIcon image = images.get(symbole);
                if (image != null) {
                    g.drawImage(image.getImage(), x * 32, y * 32, 32, 32, this);
                }
            }
        }
    }
}
