//clase para estableceer la conexion a la base de datos con Postgresql 16, necesario tener instalado Postgresql version 16
package dbAmeris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


//clase conexionDB para la logica de la conexion al servidor de postgresql
public class ConexionBD {

    static Connection conectar = null;
    static String usuario = "postgres";
    static String contrasena = "dongato876";
    static String bd = "BancoAmeris";
    static String ip = "localhost";
    static String puerto = "5432";
    static String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

    // Método estático para establecer la conexión a la base de datos
    public static Connection establecerConexion() {
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            // JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador de base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/