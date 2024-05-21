//formulario o jdialog para la logica de depositar dinero en un cuenta del sistema Ameris Bank
package dbAmeris;
//librerias necesarias
import dbAmeris.GestorOperaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FormDepositar extends javax.swing.JFrame {

    private String tipoTransaccion; // Nuevo campo para almacenar el tipo de transacción
    private JDialog dialog;
    private FormMenuPrincipal menuPrincipal;

    private void llenarComboBoxCuentas() {
        try {
            // Establece la conexión con la base de datos
            Connection conn = ConexionBD.establecerConexion();
            // Consulta SQL para obtener todas las cuentas disponibles
            String query = "SELECT CuentaID FROM Cuenta";
            // Prepara la consulta
            PreparedStatement statement = conn.prepareStatement(query);
            // Ejecuta la consulta
            ResultSet resultSet = statement.executeQuery();
            // Limpia el JComboBox antes de agregar nuevas entradas
            cmbCuentaOrigen.removeAllItems();
            // Itera sobre el resultado y agrega cada CuentaID al JComboBox
            while (resultSet.next()) {
                int cuentaID = resultSet.getInt("CuentaID");
                cmbCuentaOrigen.addItem(String.valueOf(cuentaID));
            }
            // Cierra la conexión
            conn.close();
        } catch (SQLException e) {
            // Manejo de errores
            e.printStackTrace();
        }
    }
    //constructcor
    public FormDepositar(JDialog dialog, FormMenuPrincipal menuPrincipal, String tipoTransaccion) {
    this.setUndecorated(true);
    initComponents();
    llenarComboBoxCuentas();
    setLocationRelativeTo(null);
    this.tipoTransaccion = tipoTransaccion; // Almacena el tipo de transacción recibido
    this.dialog = dialog; // Almacena el JDialog recibido
    this.menuPrincipal = menuPrincipal; // Almacena el FormMenuPrincipal recibido
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDepositar = new javax.swing.JTextPane();
        cmbCuentaOrigen = new javax.swing.JComboBox<>();
        lblSeleccionarCuenta = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        lblDepositos = new javax.swing.JLabel();
        btnDepositar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlFondo.setBackground(new java.awt.Color(153, 153, 153));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(txtDepositar);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 190, -1));

        cmbCuentaOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCuentaOrigen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cmbCuentaOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 190, -1));

        lblSeleccionarCuenta.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblSeleccionarCuenta.setForeground(new java.awt.Color(255, 255, 255));
        lblSeleccionarCuenta.setText("Selecciona una cuenta");
        jPanel1.add(lblSeleccionarCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        lblMonto.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(255, 255, 255));
        lblMonto.setText("Monto ($)");
        jPanel1.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        lblDepositos.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        lblDepositos.setForeground(new java.awt.Color(255, 255, 255));
        lblDepositos.setText("Depositos");
        jPanel1.add(lblDepositos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        btnDepositar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnDepositar.setText("DEPOSITAR");
        btnDepositar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });
        jPanel1.add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 100, 40));

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 100, 40));

        pnlFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
     // Verificar si se ha ingresado un monto
    String montoText = txtDepositar.getText().trim();
    if (montoText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Detener la ejecución del método si no se ha ingresado un monto
    }

    // Obtener los valores de cuenta y monto
    int cuentaID = Integer.parseInt(cmbCuentaOrigen.getSelectedItem().toString());
    double monto;

    try {
        monto = Double.parseDouble(montoText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El monto a depositar debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar si el monto es positivo
    if (monto <= 0) {
        JOptionPane.showMessageDialog(null, "El monto a depositar debe ser un valor positivo", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Definir el tipo de transacción
    String tipoTransaccion = "Depósito"; // Cambiar según corresponda

    // Llamar al método realizarDeposito de GestorOperaciones
    try {
        GestorOperaciones.realizarDeposito(cuentaID, monto, tipoTransaccion);
        // Mostrar un mensaje de confirmación
        JOptionPane.showMessageDialog(null, "Depósito realizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IllegalArgumentException ex) {
        // Capturar y manejar las excepciones de validación
        JOptionPane.showMessageDialog(null, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Opcional: Limpiar los campos después del depósito
    cmbCuentaOrigen.setSelectedIndex(0);
    txtDepositar.setText("");

    // Hacer visible el FormMenuPrincipal que pasaste al constructor
    this.menuPrincipal.setVisible(true);

    // Cerrar el JDialog actual
    this.dialog.dispose();
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDepositar;
    private javax.swing.JComboBox<String> cmbCuentaOrigen;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDepositos;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblSeleccionarCuenta;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JTextPane txtDepositar;
    // End of variables declaration//GEN-END:variables
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/