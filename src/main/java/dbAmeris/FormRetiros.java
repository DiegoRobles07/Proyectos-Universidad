//formulario o jdialog para la logica de retirar de una cuenta del sistema Ameris Bank
package dbAmeris;
//librerias neceesarias
import dbAmeris.GestorOperaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FormRetiros extends javax.swing.JFrame {
    //atributos
    private String tipoTransaccion;
    private JDialog dialog;
    private FormMenuPrincipal menuPrincipal;
    //funcion para cargar lac cuentas
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
    //constructor
   public FormRetiros(JDialog dialog, FormMenuPrincipal menuPrincipal, String tipoTransaccion) {
        this.setUndecorated(true);
        this.setResizable(false);
        initComponents();
        llenarComboBoxCuentas();
        setLocationRelativeTo(null);
        this.tipoTransaccion = tipoTransaccion;
        this.dialog = dialog;
        this.menuPrincipal = menuPrincipal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        cmbCuentaOrigen = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlFondo.setBackground(new java.awt.Color(153, 153, 153));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("RETIRAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 100, 40));

        jScrollPane1.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 180, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto ($)");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        cmbCuentaOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCuentaOrigen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbCuentaOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCuentaOrigenActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCuentaOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 180, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccione su cuenta");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Retiros");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 100, 40));

        pnlFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 340));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCuentaOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCuentaOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCuentaOrigenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     // Obtener los valores de cuenta y monto
    int cuentaID = Integer.parseInt(cmbCuentaOrigen.getSelectedItem().toString());
    double monto;

    try {
        monto = Double.parseDouble(jTextPane1.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir el monto a String
    String montoStr = String.valueOf(monto);
     String tipoTransaccion = "Retiro";
    // Llamar al método realizarRetiro de GestorOperaciones
    try {
        GestorOperaciones.realizarRetiro(cuentaID, montoStr, tipoTransaccion);
    } catch (IllegalArgumentException ex) {
        // Capturar y manejar las excepciones de validación
        JOptionPane.showMessageDialog(null, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Hacer visible el FormMenuPrincipal que pasaste al constructor
    this.menuPrincipal.setVisible(true);

    // Cerrar el JDialog actual
    this.dialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dialog.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbCuentaOrigen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel pnlFondo;
    // End of variables declaration//GEN-END:variables
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/