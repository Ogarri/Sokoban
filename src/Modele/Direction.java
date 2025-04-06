/**
 * Énumération Direction représente les directions possibles pour le déplacement.
 */
package Modele;

public enum Direction {
    HAUT(0, -1),
    BAS(0, 1),
    GAUCHE(-1, 0),
    DROITE(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Retourne le déplacement horizontal associé à la direction.
     *
     * @return La valeur dx.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Retourne le déplacement vertical associé à la direction.
     *
     * @return La valeur dy.
     */
    public int getDy() {
        return dy;
    }
}
