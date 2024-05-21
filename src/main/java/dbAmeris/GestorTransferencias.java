//clase para manejar las transferencias entre cuentas del banco Ameris... se puede manejar la logica en una sola clase, sin embargo al pensar el proyecto
//como en algo mas escalable, podriamos dejarlas asi, las logicas de transferencias y operaciones separadas.
package dbAmeris;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GestorTransferencias {
//hacer transfernecia
    public static void realizarTransferencia(int cuentaOrigenID, int cuentaDestinoID, double monto, String tipoTransaccion) {
        if (cuentaOrigenID == cuentaDestinoID) {
            JOptionPane.showMessageDialog(null, "No se puede transferir a la misma cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "El monto de la transferencia debe ser un valor positivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionBD.establecerConexion()) {
            conn.setAutoCommit(false);

            // Actualizar el saldo de la cuenta de origen
            String query = "UPDATE Cuenta SET Saldo = Saldo - ? WHERE CuentaID = ? AND Saldo >= ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setDouble(1, monto);
                statement.setInt(2, cuentaOrigenID);
                statement.setDouble(3, monto);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated <= 0) {
                    throw new SQLException("Saldo insuficiente para realizar la transferencia");
                }
            }

            // Actualizar el saldo de la cuenta de destino
            query = "UPDATE Cuenta SET Saldo = Saldo + ? WHERE CuentaID = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setDouble(1, monto);
                statement.setInt(2, cuentaDestinoID);
                statement.executeUpdate();
            }

            // Registrar la transacción
            query = "INSERT INTO Transaccion (CuentaOrigenID, CuentaDestinoID, TipoTransaccion, Monto) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, cuentaOrigenID);
                statement.setInt(2, cuentaDestinoID);
                statement.setString(3, tipoTransaccion);
                statement.setDouble(4, monto);
                statement.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la transferencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Método para eliminar una transferencia (transacción)
    public static boolean eliminarTransferencia(int idTransferencia) {
        try {
            Connection conn = ConexionBD.establecerConexion();
            String query = "DELETE FROM Transaccion WHERE TransaccionID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idTransferencia);
            int rowsDeleted = statement.executeUpdate();
            conn.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la transferencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/


