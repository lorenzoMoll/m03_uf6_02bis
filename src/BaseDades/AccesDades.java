/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDades;

import java.sql.*;

/**
 *
 * @author ausias
 */
public class AccesDades {

    private final String BDURL;
    private final String BD_USUARI;
    private final String BD_PASS;

    private Connection conn = null;

    public AccesDades() {
        BDURL = "jdbc:mysql://daw.inspedralbes.cat:3306/a23lormolang_M03_UF6_A01";
        BD_USUARI = "a23lormolang_LMA";
        BD_PASS = "LMA_m3uf6";
    }

    public void connectarBD() {
        try {
            // Pas #1: Crea un objecte 'conn' de connexió amb la BD
            // [Només per a MySQL] El format és "jdbc:mysql://hostname:port/databaseName", "username", "password"
            conn = DriverManager.getConnection(BDURL, BD_USUARI, BD_PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void altaBD(String nom, String dificultat, int intentsMAX, int intentsFETS) {
        try {
            if (this.conn != null) {
                //Statement stmt = conn.createStatement();
                // Pas #3: Establir la sentència de consulta SQL i executar-la mitkançant el 'Statement' creat anteriorment.
                // I el resultat es desarà en l'objecte 'rset' de tipus ResultSet.
                //String strInsert = "INSERT INTO jugades (nom, dificultat, intentsMAX, intentsFETS) VALUES('" + nom + "','" + dificultat + "','" + intentsMAX + "','" + intentsFETS + "')";
                //ALTERNATIVA A LA FUNCION
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO jugades VALUES(?,?,?,?)");
                pstmt.setString(1, nom);
                pstmt.setString(2, dificultat);
                pstmt.setInt(3, intentsMAX);
                pstmt.setInt(4, intentsFETS);
                pstmt.executeUpdate();
                //int rset = stmt.executeUpdate(strInsert);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirGanadores() {

        try {
            if (this.conn != null) {
                Statement stmt = conn.createStatement();
                // Pas #3: Establir la sentència de consulta SQL i executar-la mitkançant el 'Statement' creat anteriorment.
                // I el resultat es desarà en l'objecte 'rset' de tipus ResultSet.
                String strSelect = "SELECT * FROM jugades";
                ResultSet rset = stmt.executeQuery(strSelect);

                System.out.println("\nGuanyadors d'edicions anteriors: ");
                while (rset.next()) {
                    String nom = rset.getString("nom");
                    String dificultat = rset.getString("dificultat");
                    int intentsMAX = rset.getInt("intentsMAX");
                    int intentsFETS = rset.getInt("intentsFETS");
                    System.out.println(nom + " " + dificultat + " " + intentsMAX + " " + intentsFETS);
                }

                System.out.println("\n\nLa millor jugada realitzada seria la seguent: ");
                strSelect = "SELECT * FROM jugades ORDER BY (intentsMAX - intentsFETS) DESC LIMIT 1";
                rset = stmt.executeQuery(strSelect);
                rset.next();
                String nom = rset.getString("nom");
                String dificultat = rset.getString("dificultat");
                int intentsMAX = rset.getInt("intentsMAX");
                int intentsFETS = rset.getInt("intentsFETS");
                System.out.println(nom + " " + dificultat + " " + intentsMAX + " " + intentsFETS);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void taulaGuanyadors(){
        try{
            Statement stmt = conn.createStatement();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
