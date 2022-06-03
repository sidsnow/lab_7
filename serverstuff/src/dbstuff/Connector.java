package src.serverstuff.dbstuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connector instance;
 /*  static String DB_URL = "jdbc:postgresql://pg:5432/studs";
    static String DB_name =  "s338975";
    static String DB_password = "ljg001"; */
    static String DB_URL ="jdbc:postgresql://localhost:7777/postgres";
    static String DB_name = "postgres";
    static String DB_password = "admin";

    private Connector() {
    }

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }
    public void setDBInfo (String url, String name, String password) {
        DB_URL = url; DB_name = name; DB_password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_name,DB_password);
    }
}
