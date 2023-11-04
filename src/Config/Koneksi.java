package Config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Koneksi {

    private static Connection con;

    public static Connection getKoneksi() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/klinik";
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = (Connection) DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return con;
    }

}
