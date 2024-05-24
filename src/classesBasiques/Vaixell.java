/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesBasiques;

import java.util.Set;

/**
 *
 * @author Lorenzo
 */
public class Vaixell {

    private int x;
    private int y;
    private char orientacio;
    private int tipus;
    private int partsTocades;
    private String ocupacio;
    
    /**
     * Constructor de la classe Vaixell
     * @param x Entero. Posicion x
     * @param y Entero. Posicion y
     * @param orientacio Entero. Orientación del barco
     * @param tipus  Entero. Tipo del barco
     */
    public Vaixell(int x, int y, char orientacio, int tipus) {
        this.x = x;
        this.y = y;
        this.orientacio = orientacio;
        this.tipus = tipus;
        this.ocupacio = "";
        this.partsTocades = 0;

        if (orientacio == 'H') {
            for (int j = x; j < x + this.tipus; j++) {
                this.ocupacio += String.format("%02d", j);
                this.ocupacio += String.format("%02d", y);

            }
        } else {
            for (int j = y; j < y + this.tipus; j++) {
                this.ocupacio += String.format("%02d", x);
                this.ocupacio += String.format("%02d", j);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getOrientacio() {
        return orientacio;
    }

    public int getTipus() {
        return tipus;
    }

    public int getPartsTocades() {
        return partsTocades;
    }

    public String getOcupacio() {
        return ocupacio;
    }

    public void setPartsTocades(int partsTocades) {
        this.partsTocades = partsTocades;
    }
    
    /**
     * Devuelve true si se ha hundido el barco
     * @return Boolean. True si esta hundido
     */
    public boolean enfonsat() {
        boolean enfonsat = false;

        if (this.partsTocades == this.tipus) {
            enfonsat = true;
        }

        return enfonsat;
    }

}
