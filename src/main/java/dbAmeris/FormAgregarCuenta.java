//formulario o jdialog para la logica de agregar una cuenta a la base de datos ed AmerisBank
package dbAmeris;

//librerias neceesarias
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

public class FormAgregarCuenta extends javax.swing.JFrame {

    private JDialog dialog;

    public FormAgregarCuenta(JDialog dialog) {
        this.dialog = dialog; // Configura la operación de cierre del diálogo
        this.dialog.setModal(true); // Establece el diálogo como modal
        getContentPane().setBackground(new Color(0, 0, 0));
        initComponents();
        cargarTiposCuenta();
        setLocationRelativeTo(null);
    }

    private void cargarTiposCuenta() {
        cmbTipoCuenta.removeAllItems(); // Limpiar combo box

        // Realizar consulta a la base de datos para obtener los tipos de cuenta
        try {
            List<String> tiposCuenta = GestorCuentas.obtenerTiposCuenta();

            // Agregar los tipos de cuenta al combo box
            for (String tipo : tiposCuenta) {
                cmbTipoCuenta.addItem(tipo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los tipos de cuenta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblAgregarUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarUsuario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        lblEmail1 = new javax.swing.JLabel();
        lblSaldoInicial = new javax.swing.JLabel();
        txtSaldoInicial = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        cmbTipoCuenta = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNombre1 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAgregarUsuario.setFont(new java.awt.Font("Trebuchet MS", 0, 43)); // NOI18N
        lblAgregarUsuario.setForeground(new java.awt.Color(0, 0, 0));
        lblAgregarUsuario.setText("Nueva Cuenta");
        jPanel1.add(lblAgregarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 40, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnAgregarUsuario.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarUsuario.setText("Confirmar");
        btnAgregarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 50));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Cancelar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 120, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 260, 70));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        jPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 130, -1));

        lblEmail1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblEmail1.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail1.setText("Teléfono:");
        jPanel3.add(lblEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, -1));

        lblSaldoInicial.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblSaldoInicial.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldoInicial.setText("Saldo Inicial:  ($)");
        jPanel3.add(lblSaldoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 130, -1));
        jPanel3.add(txtSaldoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 140, -1));
        jPanel3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 130, -1));

        lblEmail.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email:");
        jPanel3.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 60, -1));

        cmbTipoCuenta.setBackground(new java.awt.Color(0, 102, 0));
        cmbTipoCuenta.setForeground(new java.awt.Color(255, 255, 255));
        cmbTipoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(cmbTipoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 140, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tipo de cuenta:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 110, -1));

        lblNombre.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombres:");
        jPanel3.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, -1));
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, -1));

        lblNombre1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre1.setText("Apellidos:");
        jPanel3.add(lblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 70, -1));
        jPanel3.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 140, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 320, 290));

        getContentPane().add(jPanel1, "card16");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
        // Obtener los datos ingresados por el usuario
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellidos.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String tipoCuenta = (String) cmbTipoCuenta.getSelectedItem();
        BigDecimal saldoInicial;

        try {
            // Convertir el saldo inicial a BigDecimal
            saldoInicial = new BigDecimal(txtSaldoInicial.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un saldo inicial válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método para agregar la cuenta
        try {
            GestorCuentas.agregarCuenta(nombre, apellido, email, telefono, tipoCuenta, saldoInicial);
            // Mostrar mensaje de éxito

            // Cerrar el JDialog
            dialog.dispose();
        } catch (IllegalArgumentException ex) {
            // Capturar y manejar las excepciones de validación
            JOptionPane.showMessageDialog(null, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Capturar y manejar las excepciones de SQL
            JOptionPane.showMessageDialog(null, "Error al agregar la cuenta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dialog.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    //metodo para agregar un espacio tipo guion en text field telefono
    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        if (txtTelefono.getText().length() == 4) {
            txtTelefono.setText(txtTelefono.getText() + "-");
        }
    }//GEN-LAST:event_txtTelefonoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JComboBox<String> cmbTipoCuenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAgregarUsuario;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblSaldoInicial;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSaldoInicial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/