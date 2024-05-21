//formulario o jdialog para la logica de transferencias entre cuentas del sistema Ameris Bank
package dbAmeris;
//librerias neceesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FormTransferencia extends javax.swing.JFrame {
    //atributos
    private String tipoTransaccion;
    private JDialog dialog;
    private FormMenuPrincipal menuPrincipal;
    //funcion para cargar cuentas
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

    public FormTransferencia(JDialog dialog, FormMenuPrincipal menuPrincipal) {
        this.setUndecorated(true);
        this.setResizable(false);
        initComponents();
        llenarComboBoxCuentas();
        this.tipoTransaccion = tipoTransaccion;
        setLocationRelativeTo(null);
        this.dialog = dialog;
        this.menuPrincipal = menuPrincipal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMonto = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCuentaTrasnferir = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        cmbCuentaOrigen = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnTransferir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlFondo.setBackground(new java.awt.Color(153, 153, 153));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transferencias");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        txtMonto.setBackground(new java.awt.Color(204, 204, 204));
        txtMonto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtMonto.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(txtMonto);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 230, -1));

        txtCuentaTrasnferir.setBackground(new java.awt.Color(204, 204, 204));
        txtCuentaTrasnferir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCuentaTrasnferir.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtCuentaTrasnferir);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 230, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccionar Cuenta (ID)");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        cmbCuentaOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCuentaOrigen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cmbCuentaOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 150, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cuenta a transferir");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Monto ($)");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        btnTransferir.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnTransferir.setText("TRANSFERIR");
        btnTransferir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });
        jPanel1.add(btnTransferir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 110, 40));

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 110, 40));

        pnlFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
       // Verificar si se ha seleccionado una cuenta de origen
    if (cmbCuentaOrigen.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta de origen", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Detener la ejecución del método si no se ha seleccionado una cuenta
    }

    // Verificar si se ha ingresado una cuenta de destino
    String cuentaDestinoText = txtCuentaTrasnferir.getText().trim();
    if (cuentaDestinoText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese una cuenta de destino", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Detener la ejecución del método si no se ha ingresado una cuenta de destino
    }

    // Verificar si se ha ingresado un monto válido
    String montoText = txtMonto.getText().trim();
    double monto;
    try {
        monto = Double.parseDouble(montoText);
        if (monto <= 0) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto válido y positivo", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Detener la ejecución del método si el monto no es válido
    }

    // Obtener los valores de cuenta de origen, cuenta de destino y monto
    int cuentaOrigenID = Integer.parseInt(cmbCuentaOrigen.getSelectedItem().toString());
    int cuentaDestinoID;
    try {
        cuentaDestinoID = Integer.parseInt(cuentaDestinoText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de cuenta válido", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Detener la ejecución del método si el número de cuenta no es válido
    }

    // Inicializar el tipo de transacción (en este caso, "Transferencia")
    String tipoTransaccion = "Transferencia";

    // Llamar al método realizarTransferencia de GestorTransferencias
    GestorTransferencias.realizarTransferencia(cuentaOrigenID, cuentaDestinoID, monto, tipoTransaccion);

    menuPrincipal.setVisible(true);
    dialog.dispose();
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTransferir;
    private javax.swing.JComboBox<String> cmbCuentaOrigen;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JTextPane txtCuentaTrasnferir;
    private javax.swing.JTextPane txtMonto;
    // End of variables declaration//GEN-END:variables
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/