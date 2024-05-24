package batallaNaval;

import BaseDades.AccesDades;
import classesBasiques.Tauler;
import classesBasiques.Vaixell;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Lorenzo
 */
public class JocVaixells_v01 {

    static HashMap<String, String> nivells = new HashMap();
    static Scanner entrada = new Scanner(System.in);
    static Tauler taulers[] = new Tauler[2];

    /**
     * Bucle principal del programa
     *
     * @param args
     */
    public static void main(String[] args) {
        String patro, nom;
        boolean jaTirat;
        JocVaixells_v01 meuJoc = new JocVaixells_v01();
        int x = -99, y = -99;
        long inici;
        long min, sec, fi;
        boolean tirarDeNou = false;
        int posTauler = 0;
        AccesDades mevaBD = new AccesDades();
        
        //SQL
        

        meuJoc.inicialitzarNivells();
        
        System.out.print("Digues Nom de jugador 1: ");
        nom = entrada.nextLine();
        patro = meuJoc.calcularNivell(meuJoc);
        taulers[0] = new Tauler(patro, nom);
        taulers[0].colocarVaixells();

        System.out.print("Digues Nom de jugador 2: ");
        nom = entrada.nextLine();
        patro = meuJoc.calcularNivell(meuJoc);
        taulers[1] = new Tauler(patro, nom);
        taulers[1].colocarVaixells();
        
        inici = System.currentTimeMillis();
        do {
            do {
                tirarDeNou = false;
                taulers[posTauler].mostrarEstat();
                taulers[posTauler].mostrarTauler();
                System.out.printf("\n\nTorn del jugador: %s\n", taulers[posTauler].getNomJugador());
                System.out.println("");
                System.out.printf("Intent #%d/%d\n", taulers[posTauler].getIntentActual(), taulers[posTauler].getIntents());
                System.out.print("\tx=");
                try {
                    x = entrada.nextInt() - 1;
                } catch (InputMismatchException ex) {
                    System.out.println("Debe de ser un entero. Vuelve a intentarlo");
                    entrada.nextLine();
                }
                try {
                    System.out.print("\ty=");
                    y = entrada.nextInt() - 1;
                } catch (InputMismatchException ex) {
                    System.out.println("Debe de ser un entero. Vuelve a intentarlo");
                    entrada.nextLine();
                }

                if (x != -99 && y != -99) {
                    if (x >= 0 && x < taulers[posTauler].getMida() && y >= 0 && y < taulers[posTauler].getMida()) {
                        jaTirat = jugada(taulers[posTauler], x, y);
                        if (!jaTirat) {
                            taulers[posTauler].setIntentActual(taulers[posTauler].getIntentActual() + 1);
                            if (taulers[posTauler].getEsquema()[y][x] == 2 || taulers[posTauler].getEsquema()[y][x] == 9) {
                                tirarDeNou = true;
                            }
                        }
                    } else {
                        System.out.println("Posicio fora del tauler! Torna a intentar-ho.");
                        tirarDeNou = true;
                    }
                }
            } while (tirarDeNou && !taulers[posTauler].taulerAcabat());
            if (!taulers[posTauler].taulerAcabat()) {
                if (posTauler == 0) {
                    posTauler = 1;
                } else {
                    posTauler = 0;
                }
            }
        } while (!taulers[posTauler].taulerAcabat());

        fi = System.currentTimeMillis() - inici;
        min = fi / 60000;
        sec = (fi % 60000) / 1000;
        System.out.printf("\nTemps emprat: %dmin %ds", min, sec);
        mevaBD.connectarBD();
        if (taulers[posTauler].taulerAcabat() && taulers[posTauler].guanyat()) {
            //mevaBD.altaBD(taulers[posTauler].getNomJugador(), taulers[posTauler].getPatro(), taulers[posTauler].getIntents(), taulers[posTauler].getIntentActual()); 
            System.out.printf("\nEnhorabona! Has guanyat %s!!\n", taulers[posTauler].getNomJugador());
        } else if (taulers[posTauler].taulerAcabat() && !taulers[posTauler].guanyat()){
            System.out.printf("\nMala sort! Has perdut %s!!\n", taulers[posTauler].getNomJugador());
        }
        mevaBD.imprimirGanadores();
    }

    /**
     * Inicializa los distintos niveles de juego
     */
    public static void inicialitzarNivells() {

        nivells.put("Inicial", "1211");
        nivells.put("Baix", "1222");
        nivells.put("Mitjà", "2322");
        nivells.put("Alt", "2333");
        nivells.put("Superior", "3343");
    }

    /**
     * Pregunta al usuario el nivel que desea y devuelve el nivel elegido
     *
     * @param meuJoc Objeto de la classe JocVaixells_v01 para aceder a las
     * variables
     * @return String con el nivel elegido
     */
    public static String calcularNivell(JocVaixells_v01 meuJoc) {
        String codi = "";
        boolean sortir;

        do {
            sortir = true;
            System.out.println("Tria de nivell de dificultat: ");
            System.out.println("[I]nicial");
            System.out.println("[B]aix");
            System.out.println("[M]itjà");
            System.out.println("[A]lt");
            System.out.println("[S]uperior");
            System.out.print("   Nivell?: ");
            char op = Character.toUpperCase(entrada.nextLine().charAt(0));
            switch (op) {
                case 'I':
                    codi = meuJoc.nivells.get("Inicial");
                    break;
                case 'B':
                    codi = meuJoc.nivells.get("Baix");
                    break;
                case 'M':
                    codi = meuJoc.nivells.get("Mitjà");
                    break;
                case 'A':
                    codi = meuJoc.nivells.get("Alt");
                    break;
                case 'S':
                    codi = meuJoc.nivells.get("Superior");
                    break;
                default:
                    System.out.println("Opcio incorrecte.\n");
                    sortir = false;
                    break;
            }
        } while (!sortir);
        return codi;
    }

    /**
     * Metodo que ejecuta la jugada del usuario en el tablero y lo actualiza,
     * notifica tambien al usuario el resultado de su movimiento
     *
     * @param tauler Objeto de Tauler
     * @param x Entero. Posicion x
     * @param y Entero. Posicion y
     * @return Boolean. Devuelve true si ya se ha tirado a esa pos
     */
    public static boolean jugada(Tauler tauler, int x, int y) {
        int esquema[][] = tauler.getEsquema();
        Vaixell barco = null;
        int decrementar = -1;
        boolean jaTirat = false;

        if (esquema[y][x] == 1) {
            barco = tauler.trobarVaixellTocat(x, y);
            if (barco != null) {
                esquema[y][x] = 2;
                tauler.setEsquema(esquema);
                barco.setPartsTocades(barco.getPartsTocades() + 1);
                if (tauler.trobarVaixellTocat(x, y).enfonsat()) {
                    System.out.println("Vaixell enfonsat");
                    decrementar = barco.getTipus();
                    tauler.eliminarBarco(barco);
                } else {
                    System.out.println("Vaixell tocat");
                }
            }
            switch (decrementar) {
                case 1:
                    tauler.setnVaix1(tauler.getnVaix1() - 1);
                    break;
                case 2:
                    tauler.setnVaix2(tauler.getnVaix2() - 1);
                    break;
                case 3:
                    tauler.setnVaix3(tauler.getnVaix3() - 1);
                    break;
                case 4:
                    tauler.setnVaix4(tauler.getnVaix4() - 1);
                    break;
                default:
                    break;
            }

        } else if (esquema[y][x] == 0) {
            System.out.println("Aigua!!");
            esquema[y][x] = 8;
        } else if (esquema[y][x] == 2) {
            System.out.println("Vaixell ja tocat!!");
            jaTirat = true;
        }

        return jaTirat;
    }
}
