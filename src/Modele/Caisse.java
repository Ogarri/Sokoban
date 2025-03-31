package Modele;

public class Caisse extends Element {
    private final boolean surDestination;

    public Caisse(boolean surDestination) {
        this.surDestination = surDestination;
    }

    @Override
    public char getSymbole() {
        return surDestination ? '*' : '$';
    }

    @Override
    public boolean aCaisse() {
        return true;
    }
}

