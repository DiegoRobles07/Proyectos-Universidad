//clase GestorCuentas para la logica de insertar, eliminar, una cuenta en el sistema bancario Ameris Bank.
package dbAmeris;
//librerias neceesarias
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GestorCuentas {

  public static void agregarCuenta(String nombre, String apellido, String email, String telefono, String tipoCuenta, BigDecimal saldoInicial) throws SQLException {
    // Validar si los campos están vacíos
    if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || email == null || email.isEmpty() || telefono == null || telefono.isEmpty() || tipoCuenta == null || tipoCuenta.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar el nombre y los apellidos
    if (!nombre.matches("[a-zA-Z ]+") || !apellido.matches("[a-zA-Z ]+")) {
        JOptionPane.showMessageDialog(null, "El nombre y los apellidos solo pueden contener letras y espacios", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar el email
    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        JOptionPane.showMessageDialog(null, "El email no tiene un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar el teléfono
    if (!telefono.matches("[0-9\\-]+")) {
        JOptionPane.showMessageDialog(null, "El teléfono solo puede contener números y guiones", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar el saldo inicial
    if (saldoInicial.compareTo(BigDecimal.ZERO) <= 0) {
        JOptionPane.showMessageDialog(null, "El saldo inicial debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el ID del cliente basado en su email
    int clienteID = obtenerClienteIDPorEmail(email);

    // Si el cliente no existe, agregar uno nuevo
    if (clienteID == -1) {
        clienteID = agregarCliente(nombre, apellido, email, telefono);
    }

    // Agregar la cuenta asociada al cliente
    try (Connection conexion = ConexionBD.establecerConexion(); PreparedStatement statement = conexion.prepareStatement("INSERT INTO Cuenta (ClienteID, TipoCuenta, Saldo) VALUES (?, ?, ?)")) {
        statement.setInt(1, clienteID);
        statement.setString(2, tipoCuenta);
        statement.setBigDecimal(3, saldoInicial);
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            JOptionPane.showMessageDialog(null, "No se pudo agregar la cuenta, no se afectaron filas.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "La cuenta se agregó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al agregar la cuenta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    //metodos para obtener tipos de cuenta
    public static List<String> obtenerTiposCuenta() throws SQLException {
        List<String> tiposCuenta = new ArrayList<>();
        try (Connection conexion = ConexionBD.establecerConexion(); Statement statement = conexion.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT Nombre FROM TipoCuenta")) {

            while (resultSet.next()) {
                tiposCuenta.add(resultSet.getString("Nombre"));
            }
        }
        return tiposCuenta;
    }
    //metodo para obtener clientes por su email
    public static int obtenerClienteIDPorEmail(String email) throws SQLException {
        int clienteID = -1;
        try (Connection conexion = ConexionBD.establecerConexion(); PreparedStatement statement = conexion.prepareStatement("SELECT ClienteID FROM Cliente WHERE Email = ?")) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clienteID = resultSet.getInt("ClienteID");
                }
            }
        }
        return clienteID;
    }
    //funcion par obtener clientes
public static List<Object[]> obtenerClientes() {
    List<Object[]> clientes = new ArrayList<>();

    try (Connection conexion = ConexionBD.establecerConexion();
         Statement statement = conexion.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT Nombre, Apellido, Email, Telefono FROM Cliente")) {

        while (resultSet.next()) {
            Object[] cliente = new Object[4];
            cliente[0] = resultSet.getString("Nombre");
            cliente[1] = resultSet.getString("Apellido");
            cliente[2] = resultSet.getString("Email");
            cliente[3] = resultSet.getString("Telefono");
            clientes.add(cliente);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener los clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    return clientes;
}
        //funcion para insertar una cuenta 
    private static void insertarCuenta(int clienteID, String tipoCuenta, BigDecimal saldoInicial) throws SQLException {
        String consultaSQL = "INSERT INTO Cuenta (ClienteID, TipoCuenta, Saldo) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionBD.establecerConexion(); PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            statement.setInt(1, clienteID);
            statement.setString(2, tipoCuenta);
            statement.setBigDecimal(3, saldoInicial);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas <= 0) {
                throw new SQLException("Error al insertar la cuenta");
            }
        }
    }
    //eliminar una transaccion
    public static void eliminarTransaccion(int transaccionID) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "DELETE FROM Transaccion WHERE TransaccionID = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, transaccionID);
            statement.executeUpdate();
            System.out.println("Transacción eliminada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la transacción: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    //eliminar cuentas
    public static void eliminarCuenta(int cuentaID) {
        // Obtener el ClienteID antes de eliminar la cuenta
        int clienteID = obtenerClienteID(cuentaID);

        // Eliminar las transacciones asociadas a la cuenta
        eliminarTransaccionesDeCuenta(cuentaID);

        // Una vez que las transacciones asociadas han sido eliminadas, proceder a eliminar la cuenta
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "DELETE FROM Cuenta WHERE CuentaID = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, cuentaID);
            statement.executeUpdate();
            System.out.println("Cuenta eliminada correctamente");

            // Verificar si el cliente asociado a esta cuenta no está asociado a ninguna otra cuenta
            // Si no está asociado, eliminar ese cliente también
            eliminarClienteSiNecesario(clienteID);

        } catch (SQLException e) {
            System.out.println("Error al eliminar la cuenta: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
        //eliminar transacciones de cuenta
    private static void eliminarTransaccionesDeCuenta(int cuentaID) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "DELETE FROM Transaccion WHERE CuentaOrigenID = ? OR CuentaDestinoID = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, cuentaID);
            statement.setInt(2, cuentaID);
            statement.executeUpdate();
            System.out.println("Transacciones asociadas a la cuenta eliminadas correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar las transacciones asociadas a la cuenta: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    //funcion no tan necesaria, se podria omitir pero la dejamos por cualquier cosa
    private static void eliminarClienteSiNecesario(int clienteID) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "SELECT COUNT(*) AS total FROM Cuenta WHERE ClienteID = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, clienteID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int totalCuentas = resultSet.getInt("total");
                if (totalCuentas == 0) {
                    // No hay otras cuentas asociadas a este cliente, por lo tanto, eliminar el cliente
                    query = "DELETE FROM Cliente WHERE ClienteID = ?";
                    statement = conexion.prepareStatement(query);
                    statement.setInt(1, clienteID);
                    statement.executeUpdate();
                    System.out.println("Cliente eliminado correctamente");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion, statement, resultSet);
        }
    }
//obtener el id de los cliente
    private static int obtenerClienteID(int cuentaID) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int clienteID = -1;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "SELECT ClienteID FROM Cuenta WHERE CuentaID = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, cuentaID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                clienteID = resultSet.getInt("ClienteID");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ClienteID: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion, statement, resultSet);
        }

        return clienteID;
    }
//cargar una tabla
    public static void cargarDatosTabla(String consultaSQL, String[] nombresColumnas, JTable tabla) {
        try {
            // Establecer conexión
            Connection conn = ConexionBD.establecerConexion();

            // Crear statement y ejecutar consulta
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(consultaSQL);

            // Limpiar tabla antes de cargar nuevos datos
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0);

            // Agregar filas a la tabla
            while (resultSet.next()) {
                Object[] fila = new Object[nombresColumnas.length];
                for (int i = 0; i < nombresColumnas.length; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                }
                model.addRow(fila);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            // Manejar cualquier excepción que ocurra durante la carga de datos
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar datos en la tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//agregar un cliente por cuenta
    public static int agregarCliente(String nombre, String apellido, String email, String telefono) throws SQLException {
        int clienteID = -1;
        try (Connection conexion = ConexionBD.establecerConexion(); PreparedStatement statement = conexion.prepareStatement("INSERT INTO Cliente (Nombre, Apellido, Email, Telefono) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);
            statement.setString(4, telefono);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo agregar el cliente, no se afectaron filas.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    clienteID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el nuevo cliente.");
                }
            }
        }
        return clienteID;
    }
//cargar las transacciones
   public static void cargarTransacciones(JTable tabla) {
        // Define la consulta SQL para obtener los datos de la tabla de transacciones
        String consultaSQL = "SELECT CuentaOrigenID, CuentaDestinoID, TipoTransaccion, Monto, Fecha FROM Transaccion";

        // Llama al método para cargar los datos en la tabla
        cargarDatosTabla(consultaSQL, new String[]{"Cuenta Origen", "Cuenta Destino", "Tipo de Transacción", "Monto", "Fecha"}, tabla);

        // Actualizar el título de la tabla
        // Suponiendo que lblTitulo es el JLabel donde se muestra el título de la tabla
        // lblTitulo.setText("Últimas Transacciones");
    }
//funcion para validar datos
    private static void validarDatos(String nombre, String apellido, String email, String telefono, String tipoCuenta, BigDecimal saldoInicial) throws SQLException {
        // Validar que todos los campos estén llenos
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || telefono.isEmpty() || tipoCuenta.isEmpty()) {
            throw new SQLException("Todos los campos son obligatorios");
        }

        // Validar el formato del email (puedes usar una expresión regular para una validación más precisa)
        if (!email.contains("@")) {
            throw new SQLException("El formato del email es inválido");
        }

        // Validar que el saldo inicial sea positivo
        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new SQLException("El saldo inicial debe ser un valor positivo");
        }
    }
//funcion para probar
    private static void cerrarRecursos(Connection conexion, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/