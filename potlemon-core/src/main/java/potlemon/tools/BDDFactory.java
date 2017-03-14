package potlemon.tools;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * gestion de la BDD : propose une fonction
 * et dans la cas ou la base n'existe pas encore elle sera crée
 * la base de donnée est en SQLite
 * @author asvevi
 *
 */

public class BDDFactory {

    /**
     * getDBI() qui retourne la connection à la base de donnée
     * si la base n'existe pas elle sera crée
     * @return
     */
    public static Connection getConnection() {
    	if(!new File("data.db").exists()){//si la BDD n'existe pas
    		try {
				new File("data.db").createNewFile();//creation du fichier vide
				init();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        Connection c = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return c;
    }

    /**
     * Cree les tables de la BDD ainsi que les users de base
     */
	private static void init() {
		Connection c = getConnection();
		String tablePokemon = "CREATE TABLE pokemon (ID INT PRIMARY KEY NOT NULL,"
				+ "NAME TEXT NOT NULL,"
				+ "PVMAX INT NOT NULL)";
		
		
		
		PreparedStatement create;
		try {
			create = c.prepareStatement(tablePokemon);
			create.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
