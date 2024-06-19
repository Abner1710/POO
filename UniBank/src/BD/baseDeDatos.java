package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baseDeDatos {
    private Connection cx = null;

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            cx = DriverManager.getConnection("jdbc:sqlite:UniBank.db");
            System.out.println("Conexion Exitosa!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return cx;
    }

    public void desconectar() {
        try {
            if (cx != null) {
                cx.close();
                System.out.println("Desconexion Exitosa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
