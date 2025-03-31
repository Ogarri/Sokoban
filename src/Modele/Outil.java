package Modele;

import java.util.Scanner;

public class Outil {
    private static final Scanner scanner = new Scanner(System.in);

    public static char lireCar() {
        return scanner.next().charAt(0);
    }
}
