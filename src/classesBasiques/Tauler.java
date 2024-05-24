/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesBasiques;

import java.util.ArrayList;

/**
 *
 * @author Lorenzo
 */
public class Tauler {

    private String nomJugador;
    private int mida;
    private int nVaixells;
    private String patro;
    private int nVaix1;
    private int nVaix2;
    private int nVaix3;
    private int nVaix4;
    private int intents;
    private int intentActual;
    private int esquema[][];
    private ArrayList<Vaixell> totsVaixells;

    /**
     * Constructor Tauler
     *
     * @param patro String. Conte el patro del tauler.
     */
    public Tauler(String patro, String nomJugador) {
        this.patro = patro;
        this.mida = 0;
        this.nVaixells = 0;
        for (int i = 0; i < 4; i++) {
            this.nVaixells += Integer.parseInt(Character.toString(patro.charAt(i)));
            this.mida += Integer.parseInt(Character.toString(patro.charAt(i)));
        }
        this.nVaix1 = Integer.parseInt(Character.toString(patro.charAt(0)));
        this.nVaix2 = Integer.parseInt(Character.toString(patro.charAt(1)));
        this.nVaix3 = Integer.parseInt(Character.toString(patro.charAt(2)));
        this.nVaix4 = Integer.parseInt(Character.toString(patro.charAt(3)));
        this.intents = (int) ((this.mida * this.mida) * 0.6);
        this.esquema = new int[this.mida][this.mida];
        this.totsVaixells = new ArrayList();
        this.nomJugador = nomJugador;
        this.intentActual = 1;
    }

    public int[][] getEsquema() {
        return esquema;
    }

    public void setEsquema(int[][] esquema) {
        this.esquema = esquema;
    }

    public int getIntents() {
        return intents;
    }

    public void setIntents(int intents) {
        this.intents = intents;
    }

    public ArrayList<Vaixell> getTotsVaixells() {
        return totsVaixells;
    }

    public int getnVaix1() {
        return nVaix1;
    }

    public int getnVaix2() {
        return nVaix2;
    }

    public int getnVaix3() {
        return nVaix3;
    }

    public int getnVaix4() {
        return nVaix4;
    }

    public String getPatro() {
        return patro;
    }
    
    

    public void setnVaix1(int nVaix1) {
        this.nVaix1 = nVaix1;
    }

    public void setnVaix2(int nVaix2) {
        this.nVaix2 = nVaix2;
    }

    public void setnVaix3(int nVaix3) {
        this.nVaix3 = nVaix3;
    }

    public void setnVaix4(int nVaix4) {
        this.nVaix4 = nVaix4;
    }

    public int getMida() {
        return mida;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public int getIntentActual() {
        return intentActual;
    }

    public void setIntentActual(int intentActual) {
        this.intentActual = intentActual;
    }

    /**
     * Enseña el estado de los barcos en el tablero
     */
    public void mostrarEstat() {
        System.out.printf("\nVaixells --> [1p]%d/%d [2p]%d/%d [3p]%d/%d [4p]%d/%d", this.nVaix1, Integer.parseInt(Character.toString(this.patro.charAt(0))), this.nVaix2, Integer.parseInt(Character.toString(this.patro.charAt(1))), this.nVaix3, Integer.parseInt(Character.toString(this.patro.charAt(2))), this.nVaix4, Integer.parseInt(Character.toString(this.patro.charAt(3))));
    }

    /**
     * Coloca los barcos segun el tipo
     */
    public void colocarVaixells() {

        colocarBarcoDefinitiu(nVaix4, 4);
        colocarBarcoDefinitiu(nVaix3, 3);
        colocarBarcoDefinitiu(nVaix2, 2);
        colocarBarcoDefinitiu(nVaix1, 1);

    }

    /**
     * Enseña el tablero por pantalla
     */
    public void mostrarTauler() {
        System.out.print("\n\n     ");
        for (int i = 0; i < this.mida; i++) {
            System.out.printf("%02d ", i + 1);
        }
        System.out.print("\n     ");
        for (int i = 0; i < this.mida; i++) {
            System.out.print("-- ");
        }
        for (int i = 0; i < this.mida; i++) {
            System.out.printf("\n\u001B[30m%02d>  ", i + 1);
            for (int j = 0; j < this.mida; j++) {
                if (this.esquema[i][j] == 2) {
                    System.out.printf("\u001B[31m%2d ", this.esquema[i][j]);
                } else if (this.esquema[i][j] == 8) {
                    System.out.printf("\u001B[34m 0 ", this.esquema[i][j]);
                } else if (this.esquema[i][j] == 9) {
                    System.out.printf("\u001B[35m 1 ", this.esquema[i][j]);
                } else if (this.esquema[i][j] == 1) {
                    System.out.printf("\u001B[38m 1 ", this.esquema[i][j]);
                } else {
                    System.out.printf("\u001B[30m 0 ", this.esquema[i][j]);
                }
            }
        }
        System.out.printf("\u001B[30m");
    }

    /**
     * Devuelve true si se ha finalizado la partida
     *
     * @return Boolean. Indica el estado de finalización de la partida
     */
    public boolean taulerAcabat() {
        boolean acabat = false;

        if (this.totsVaixells.isEmpty() || this.intentActual > this.intents) {
            acabat = true;
        }

        return true;
    }

    public boolean guanyat() {
        boolean guanyat = false;

        if (this.totsVaixells.isEmpty()) {
            guanyat = true;
        }

        return true;
    }

    /**
     * Encuentra un barco segun las cordenadas y lo devuelve
     *
     * @param x Entero. Posicion x
     * @param y Entero. Posicion y
     * @return Objeto Vaixell. Devuelve el barco encontrado o null
     */
    public Vaixell trobarVaixellTocat(int x, int y) {

        Vaixell barco = null;
        int xL, yL;

        for (int i = 0; i < this.totsVaixells.size(); i++) {

            for (int j = 0; j + 3 < this.totsVaixells.get(i).getOcupacio().length(); j += 4) {
                //Lx = String.valueOf(this.totsVaixells.get(i).getOcupacio().charAt(j))+String.valueOf(this.totsVaixells.get(i).getOcupacio().charAt(j+1));
                xL = Integer.parseInt(String.valueOf(this.totsVaixells.get(i).getOcupacio().charAt(j)) + String.valueOf(this.totsVaixells.get(i).getOcupacio().charAt(j + 1)));
                yL = Integer.parseInt(String.valueOf(this.totsVaixells.get(i).getOcupacio().charAt(j + 2)) + String.valueOf(this.totsVaixells.get(i).getOcupacio().charAt(j + 3)));
                if (xL == x && yL == y) {
                    barco = this.totsVaixells.get(i);
                }
            }

        }
        return barco;
    }

    /**
     * Comprueba si se puede colocar el barco
     *
     * @param x Entero. Posicion x
     * @param y Entero. Posicion y
     * @param orientacio Char. Orientación del barco
     * @param tipus Int. Tipo de barco
     * @return Boolean. Devuelve true si se puede colocar.
     */
    public boolean esPotColocable(int x, int y, char orientacio, int tipus) {
        boolean colocar = true;

        if (orientacio == 'H') {
            for (int i = x; i < x + tipus; i++) {
                if (i < this.mida) {
                    if (esquema[y][i] == 1) {
                        colocar = false;
                    }
                } else {
                    colocar = false;
                }
            }
        } else {
            for (int i = y; i < y + tipus; i++) {
                if (i < this.mida) {
                    if (esquema[i][x] == 1) {
                        colocar = false;
                    }
                } else {
                    colocar = false;
                }
            }
        }

        return colocar;
    }

    /**
     * Coloca el barco en el esquema
     *
     * @param nVaix Entero. Numero de barcos
     * @param tipusBarco Entero. Tipo de barco
     */
    public void colocarBarcoDefinitiu(int nVaix, int tipusBarco) {
        Vaixell barco;
        char orientacio;
        int x, y;
        int tipus;
        String ocupacio;
        boolean colocable;

        for (int i = 0; i < nVaix; i++) {
            colocable = false;
            ocupacio = "";
            tipus = tipusBarco;
            if ((int) (Math.random() * 2) == 0) {
                orientacio = 'H';
            } else {
                orientacio = 'V';
            }
            do {
                x = (int) (Math.random() * this.mida);
                y = (int) (Math.random() * this.mida);
                colocable = esPotColocable(x, y, orientacio, tipus);
                if (colocable) {
                    if (orientacio == 'H') {
                        for (int j = x; j < x + tipus; j++) {
                            this.esquema[y][j] = 1;
                        }
                    } else {
                        for (int j = y; j < y + tipus; j++) {
                            this.esquema[j][x] = 1;
                        }
                    }
                }
            } while (!colocable);
            barco = new Vaixell(x, y, orientacio, tipus);
            this.totsVaixells.add(barco);
        }
    }

    /**
     * Metodo que elimina el barco del ArrayList de barcos y del esquema
     *
     * @param barco Objeto Vaixell. El barco que hay que eliminar.
     */
    public void eliminarBarco(Vaixell barco) {
        if (barco.getOrientacio() == 'H') {
            for (int j = barco.getX(); j < barco.getX() + barco.getTipus(); j++) {
                this.esquema[barco.getY()][j] = 9;
            }
        } else {
            for (int j = barco.getY(); j < barco.getY() + barco.getTipus(); j++) {
                this.esquema[j][barco.getX()] = 9;
            }
        }
        this.totsVaixells.remove(barco);
    }
}
