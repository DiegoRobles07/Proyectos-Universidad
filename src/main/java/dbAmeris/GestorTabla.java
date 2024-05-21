package dbAmeris;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GestorTabla {
    public static void cargarDatosTabla(String consultaSQL, String[] nombresColumnas, JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (String nombreColumna : nombresColumnas) {
            modelo.addColumn(nombreColumna);
        }

        try (Connection conexion = ConexionBD.establecerConexion();
             PreparedStatement statement = conexion.prepareStatement(consultaSQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Object[] fila = new Object[nombresColumnas.length];
                for (int i = 0; i < nombresColumnas.length; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos en la tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        tabla.setModel(modelo);
    }

    public static void cargarTransacciones(JTable tabla) {
        String consultaSQL = "SELECT t.TransaccionID, t.CuentaOrigenID, t.CuentaDestinoID, tt.Nombre AS TipoTransaccion, t.Monto, t.Fecha " +
                             "FROM Transaccion t " +
                             "JOIN TipoTransaccion tt ON t.TipoTransaccionID = tt.TipoTransaccionID";
        String[] nombresColumnas = {"TransaccionID", "CuentaOrigenID", "CuentaDestinoID", "TipoTransaccion", "Monto", "Fecha"};
        cargarDatosTabla(consultaSQL, nombresColumnas, tabla);
    }

    public static void cargarCuentas(JTable tabla) {
        String consultaSQL = "SELECT CuentaID, ClienteID, TipoCuenta, Saldo FROM Cuenta";
        String[] nombresColumnas = {"CuentaID", "ClienteID", "TipoCuenta", "Saldo"};
        cargarDatosTabla(consultaSQL, nombresColumnas, tabla);
    }

    public static void cargarClientes(List<Object[]> clientes, String[] nombresColumnas, JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (String nombreColumna : nombresColumnas) {
            modelo.addColumn(nombreColumna);
        }
        for (Object[] fila : clientes) {
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
}

/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
