//clase para manejar las operaciones de deposito y retiro de las cuentas
package dbAmeris;

import dbAmeris.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GestorOperaciones {
    //funcion para her un deposito
    public static void realizarDeposito(int cuentaID, double monto, String tipoTransaccion) {
        // Verificar si el monto es un número positivo
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "El monto a depositar debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = ConexionBD.establecerConexion();

            // Consulta SQL para realizar el depósito
            String insertTransaccionQuery = "INSERT INTO Transaccion (CuentaOrigenID, Monto, TipoTransaccion) VALUES (?, ?, ?)";
            PreparedStatement insertTransaccionStatement = conn.prepareStatement(insertTransaccionQuery);
            insertTransaccionStatement.setInt(1, cuentaID);
            insertTransaccionStatement.setDouble(2, monto);
            insertTransaccionStatement.setString(3, tipoTransaccion);
            insertTransaccionStatement.executeUpdate();

            // Consulta SQL para actualizar el saldo en la cuenta
            String updateSaldoQuery = "UPDATE Cuenta SET Saldo = Saldo + ? WHERE CuentaID = ?";
            PreparedStatement updateSaldoStatement = conn.prepareStatement(updateSaldoQuery);
            updateSaldoStatement.setDouble(1, monto);
            updateSaldoStatement.setInt(2, cuentaID);
            updateSaldoStatement.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el depósito: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//funcion para hacer rretirors
    public static void realizarRetiro(int cuentaID, String montoStr, String tipoTransaccion) {
        double monto;
        // Verificar si el monto es un número
        try {
            monto = Double.parseDouble(montoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El monto a retirar debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el monto es un número positivo
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "El monto a retirar debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cuentaID == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta válida para realizar el retiro", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = ConexionBD.establecerConexion();
            String query = "UPDATE Cuenta SET Saldo = Saldo - ? WHERE CuentaID = ? AND Saldo >= ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, monto);
            statement.setInt(2, cuentaID);
            statement.setDouble(3, monto);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Verificar si tipoTransaccion es nulo o vacío y asignar un valor predeterminado si es necesario
                if (tipoTransaccion == null || tipoTransaccion.isEmpty()) {
                    tipoTransaccion = "Retiro";
                }
                // Ahora insertamos una nueva transacción en la tabla Transaccion
                insertarTransaccion(conn, cuentaID, tipoTransaccion, -monto); // Se resta el monto en el caso del retiro
                JOptionPane.showMessageDialog(null, "Retiro realizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar el retiro", "Error", JOptionPane.ERROR_MESSAGE);
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el retiro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
//funcion para insertar transaccion
   private static void insertarTransaccion(Connection conn, int cuentaID, String tipoTransaccion, double monto) throws SQLException {
    // Consulta SQL para insertar una nueva transacción en la tabla de transacciones
    String insertQuery = "INSERT INTO Transaccion (CuentaOrigenID, Monto, TipoTransaccion) VALUES (?, ?, ?)";
    PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
    insertStatement.setInt(1, cuentaID);
    insertStatement.setDouble(2, monto);

    // Verificamos si el tipo de transacción no es nulo antes de establecerlo
    if (tipoTransaccion != null) {
        insertStatement.setString(3, tipoTransaccion);
    } else {
        // Si el tipo de transacción es nulo, podemos asignar un valor predeterminado o lanzar una excepción
        // Aquí se asigna un valor predeterminado, pero podrías elegir manejarlo de otra manera según tus necesidades
        insertStatement.setString(3, "Tipo Desconocido");
    }

    insertStatement.executeUpdate();
}
    // Método para eliminar una transacción
    public static boolean eliminarTransaccion(int idTransaccion) {
        try {
            Connection conn = ConexionBD.establecerConexion();
            String query = "DELETE FROM Transaccion WHERE TransaccionID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idTransaccion);
            int rowsDeleted = statement.executeUpdate();
            conn.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la transacción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/