package potlemon.core.tools;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Connect to the SQLite database
 */

public class BDDFactory {

    /**
     * getConnection() will retruen the connection to the database, if the datatbase
     * don't exist, it create it
     *
     * @return
     */
    public static Connection getConnection() {
        if (!new File("data.db").exists()) {//if database don't exists
            try {
                new File("data.db").createNewFile();//create file
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    /**
     * Create every table
     */
    private static void init() {
        Connection c = getConnection();
        String tablePokemon = "CREATE TABLE pokemon (ID INT PRIMARY KEY NOT NULL,"
                + "NAME TEXT NOT NULL,"
                + "PVMAX INT NOT NULL)";

        String tableItem = "CREATE TABLE item (ID INT PRIMARY KEY NOT NULL,"
                + "NAME TEXT NOT NULL,"
                + "pv INT)";


        PreparedStatement create;
        PreparedStatement create2;
        try {
            create = c.prepareStatement(tablePokemon);
            create.execute();

            create2 = c.prepareStatement(tableItem);
            create2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
