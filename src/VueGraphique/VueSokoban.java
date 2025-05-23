/**
 * Classe VueSokoban représente l'interface graphique du jeu Sokoban.
 * Elle gère l'affichage des éléments du jeu et les interactions clavier.
 */
package VueGraphique;

import Modele.Carte;
import Modele.Direction;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Classe VueSokoban représente l'interface graphique du jeu Sokoban.
 * Elle gère l'affichage des éléments du jeu et les interactions clavier.
 */
public class VueSokoban extends JPanel {
    private final Carte carte;
    private final Map<Character, ImageIcon> images;
    private Runnable resetCallback;

    /**
     * Définit une fonction de rappel pour réinitialiser le niveau.
     *
     * @param resetCallback Fonction à exécuter lors de la réinitialisation.
     */
    public void setResetCallback(Runnable resetCallback) {
        this.resetCallback = resetCallback;
    }

    /**
     * Constructeur de VueSokoban.
     * Initialise la carte, charge les images et configure les événements clavier.
     *
     * @param carte La carte du jeu à afficher.
     */
    public VueSokoban(Carte carte) {
        this.carte = carte;
        this.images = new HashMap<>();
        chargerImages();
        setPreferredSize(new Dimension(carte.getLargeur() * 32, carte.getHauteur() * 32));

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction direction = null;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Z -> {
                        direction = Direction.HAUT;
                        images.put('@', new ImageIcon("bin/img/Haut.gif"));
                    }
                    case KeyEvent.VK_Q -> {
                        direction = Direction.GAUCHE;
                        images.put('@', new ImageIcon("bin/img/Gauche.gif"));
                    }
                    case KeyEvent.VK_S -> {
                        direction = Direction.BAS;
                        images.put('@', new ImageIcon("bin/img/Bas.gif"));
                    }
                    case KeyEvent.VK_D -> {
                        direction = Direction.DROITE;
                        images.put('@', new ImageIcon("bin/img/Droite.gif"));
                    }
                    case KeyEvent.VK_R -> {
                        if (resetCallback != null) {
                            resetCallback.run();
                        }
                        return;
                    }
                }
                if (direction != null) {
                    carte.deplacerJoueur(direction);
                    repaint();
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

    /**
     * Charge les images des différents éléments du jeu.
     */
    private void chargerImages() {
        images.put('$', new ImageIcon("bin/img/caisse1.gif"));
        images.put('+', new ImageIcon("bin/img/Bas.gif.gif"));
        images.put('#', new ImageIcon("bin/img/mur.gif"));
        images.put(' ', new ImageIcon("bin/img/sol.gif"));
        images.put('.', new ImageIcon("bin/img/but.gif"));
        images.put('@', new ImageIcon("bin/img/Bas.gif"));
        images.put('*', new ImageIcon("bin/img/caisse2.gif"));
    }

    /**
     * Dessine les éléments du jeu sur le panneau.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                char symbole = carte.getGrille()[y][x].getSymbole();
                if (symbole == '+') {
                    ImageIcon butImage = images.get('.');
                    if (butImage != null) {
                        g.drawImage(butImage.getImage(), x * 32, y * 32, 32, 32, this);
                    }
                    ImageIcon joueurImage = images.get('@');
                    if (joueurImage != null) {
                        g.drawImage(joueurImage.getImage(), x * 32, y * 32, 32, 32, this);
                    }
                } else {
                    ImageIcon image = images.get(symbole);
                    if (image != null) {
                        g.drawImage(image.getImage(), x * 32, y * 32, 32, 32, this);
                    }
                }
            }
        }
    }
}
