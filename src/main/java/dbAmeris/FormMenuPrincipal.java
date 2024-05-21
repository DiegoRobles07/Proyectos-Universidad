package dbAmeris;

import dbAmeris.GestorCuentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public class FormMenuPrincipal extends javax.swing.JFrame {

    private boolean mostrandoCuentas = true; // Inicialmente mostrando cuentas

    public FormMenuPrincipal() {
        // Quitar botones de cerrar y minimizar
        this.setUndecorated(true);

        // Hacer la ventana no redimensionable
        this.setResizable(false);

        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        cargarTransacciones();
        redondearBotones();
        setLocationRelativeTo(null);
    }

    private void cargarTransacciones() {
    DefaultTableModel model = new DefaultTableModel(new String[]{"Origen", "Destino", "Tipo", "Monto($)", "Fecha"}, 0);
    
    Connection con = ConexionBD.establecerConexion();
    if (con != null) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cuentaorigenid, cuentadestinoid, tipotransaccion, monto, fecha FROM transaccion");

            DecimalFormat df = new DecimalFormat("#.00");

            while(rs.next()) {
                int cuentaorigenid = rs.getInt("cuentaorigenid");
                int cuentadestinoid = rs.getInt("cuentadestinoid");
                String tipotransaccion = rs.getString("tipotransaccion");
                double monto = rs.getDouble("monto");
                String montoFormateado = df.format(monto);
                Timestamp fecha = rs.getTimestamp("fecha");
                model.addRow(new Object[]{cuentaorigenid, cuentadestinoid, tipotransaccion, montoFormateado, fecha});
            }
            
            tblGeneral.setModel(model);
            
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las transacciones.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión a la base de datos.");
    }
}

   
    private void cargarClientesEnTabla() {
        // Llamar al método en GestorCuentas para cargar los clientes en la tabla
        List<Object[]> clientes = GestorCuentas.obtenerClientes();
        // Cargar los clientes en la tabla
        DefaultTableModel model = (DefaultTableModel) tblGeneral.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
        for (Object[] cliente : clientes) {
            model.addRow(cliente);
        }
    }

    private void redondearBotones() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("Button.arc", 10); // Cambiar el radio de los bordes
                    SwingUtilities.updateComponentTreeUI(this); // Actualizar todos los componentes UI de esta ventana
                    break;
                }
            }
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new javax.swing.JPanel();
        lblTituloBanco = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();
        btnAgregarCuenta1 = new javax.swing.JButton();
        btnVerCuentas = new javax.swing.JButton();
        btnVerClientes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnTrasnferir = new javax.swing.JButton();
        btnDepositar1 = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGeneral = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AMERIS BANK");

        pnlContainer.setBackground(new java.awt.Color(204, 255, 153));
        pnlContainer.setForeground(new java.awt.Color(0, 0, 0));
        pnlContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloBanco.setFont(new java.awt.Font("Trebuchet MS", 0, 68)); // NOI18N
        lblTituloBanco.setForeground(new java.awt.Color(0, 0, 0));
        lblTituloBanco.setText("A M E R I S   B A N K");
        pnlContainer.add(lblTituloBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 680, 80));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setBorder(null);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 130, 60));

        btnTransacciones.setBackground(new java.awt.Color(51, 51, 51));
        btnTransacciones.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnTransacciones.setForeground(new java.awt.Color(255, 255, 255));
        btnTransacciones.setText("Transacciones");
        btnTransacciones.setBorder(null);
        btnTransacciones.setBorderPainted(false);
        btnTransacciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransacciones.setFocusable(false);
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, 46));

        btnAgregarCuenta1.setBackground(new java.awt.Color(51, 51, 51));
        btnAgregarCuenta1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnAgregarCuenta1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCuenta1.setText("Agregar Cuenta");
        btnAgregarCuenta1.setBorder(null);
        btnAgregarCuenta1.setBorderPainted(false);
        btnAgregarCuenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCuenta1.setFocusable(false);
        btnAgregarCuenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCuenta1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarCuenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 130, 46));

        btnVerCuentas.setBackground(new java.awt.Color(51, 51, 51));
        btnVerCuentas.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnVerCuentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVerCuentas.setText("Ver Cuentas");
        btnVerCuentas.setBorder(null);
        btnVerCuentas.setBorderPainted(false);
        btnVerCuentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerCuentas.setFocusable(false);
        btnVerCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCuentasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerCuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 46));

        btnVerClientes.setBackground(new java.awt.Color(51, 51, 51));
        btnVerClientes.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnVerClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnVerClientes.setText("Ver Clientes");
        btnVerClientes.setBorder(null);
        btnVerClientes.setBorderPainted(false);
        btnVerClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerClientes.setFocusable(false);
        btnVerClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, 46));

        pnlContainer.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 170, 420));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Últimas Transacciones");
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 50));

        btnTrasnferir.setBackground(new java.awt.Color(51, 51, 51));
        btnTrasnferir.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnTrasnferir.setForeground(new java.awt.Color(255, 255, 255));
        btnTrasnferir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/money.png"))); // NOI18N
        btnTrasnferir.setText("TRANSFERIR");
        btnTrasnferir.setBorder(null);
        btnTrasnferir.setBorderPainted(false);
        btnTrasnferir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTrasnferir.setFocusable(false);
        btnTrasnferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrasnferirActionPerformed(evt);
            }
        });
        jPanel2.add(btnTrasnferir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 180, 60));

        btnDepositar1.setBackground(new java.awt.Color(51, 51, 51));
        btnDepositar1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnDepositar1.setForeground(new java.awt.Color(255, 255, 255));
        btnDepositar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pay.png"))); // NOI18N
        btnDepositar1.setText("DEPOSITAR");
        btnDepositar1.setBorder(null);
        btnDepositar1.setBorderPainted(false);
        btnDepositar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDepositar1.setFocusable(false);
        btnDepositar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnDepositar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 180, 60));

        btnRetirar.setBackground(new java.awt.Color(51, 51, 51));
        btnRetirar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRetirar.setForeground(new java.awt.Color(255, 255, 255));
        btnRetirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atm.png"))); // NOI18N
        btnRetirar.setText("RETIRAR");
        btnRetirar.setBorder(null);
        btnRetirar.setBorderPainted(false);
        btnRetirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRetirar.setFocusable(false);
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRetirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 180, 60));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(0, 204, 255));
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblGeneral.setBackground(new java.awt.Color(255, 255, 255));
        tblGeneral.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        tblGeneral.setForeground(new java.awt.Color(0, 0, 0));
        tblGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cuenta Origen", "Cuenta Destino", "Tipo Transacción", "Monto"
            }
        ));
        jScrollPane2.setViewportView(tblGeneral);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 71, 605, 236));

        pnlContainer.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 640, 420));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/30pxcerrar.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        pnlContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 30, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/30pxmin.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        pnlContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 30, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCuentasActionPerformed
        GestorTabla.cargarCuentas(tblGeneral);
        lblTitulo.setText("Cuentas Registradas");
        mostrandoCuentas = true;
        btnEliminar.setEnabled(true);
    }//GEN-LAST:event_btnVerCuentasActionPerformed

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        cargarTransacciones();
        lblTitulo.setText("Últimas Transacciones");
        btnEliminar.setEnabled(true);
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    private void btnVerClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerClientesActionPerformed
        // Llamar al método para obtener los clientes desde la base de datos
        List<Object[]> clientes = GestorCuentas.obtenerClientes();

        // Definir los nombres de las columnas
        String[] nombresColumnas = {"Nombre", "Apellido", "Email", "Teléfono"};

        // Cargar los clientes en la tabla
        GestorTabla.cargarClientes(clientes, nombresColumnas, tblGeneral);

        // Actualizar el título de la tabla
        lblTitulo.setText("Clientes Registrados");
        mostrandoCuentas = false;
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnVerClientesActionPerformed

    private void btnAgregarCuenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCuenta1ActionPerformed
    // Crea un nuevo JDialog
    JDialog dialog = new JDialog(this, "Agregar Cuenta", true);
    dialog.setUndecorated(true);

    // Crea una nueva instancia de FormAgregarCuenta
    FormAgregarCuenta formAgregarCuenta = new FormAgregarCuenta(dialog);

    // Agrega el formulario de agregar cuenta al JDialog
    dialog.getContentPane().add(formAgregarCuenta.getContentPane());

    // Configura el tamaño del JDialog para que coincida con el de FormAgregarCuenta
    dialog.setSize(formAgregarCuenta.getSize());

    // Centra el JDialog en la pantalla
    dialog.setLocationRelativeTo(null);

    // Muestra el JDialog (esto bloqueará la interacción con el JFrame hasta que el JDialog se cierre)
    dialog.setVisible(true);
    }//GEN-LAST:event_btnAgregarCuenta1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Obtener el índice de la fila seleccionada en la tabla
        int filaSeleccionada = tblGeneral.getSelectedRow();

        // Verificar si se ha seleccionado alguna fila
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un registro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el modelo de la tabla para trabajar con los datos
        DefaultTableModel model = (DefaultTableModel) tblGeneral.getModel();

        // Obtener el tipo de registro que se está mostrando en la tabla
        String tipoRegistro = mostrandoCuentas ? "cuenta" : "transacción";

        // Obtener el ID del registro seleccionado (el primer valor de la fila en la tabla)
        int idRegistro = (int) model.getValueAt(filaSeleccionada, 0);

        // Preguntar al usuario si está seguro de eliminar el registro
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este " + tipoRegistro + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llamar al método correspondiente en GestorCuentas para eliminar el registro
            if (mostrandoCuentas) {
                // Si se están mostrando cuentas, llamar al método para eliminar cuenta
                GestorCuentas.eliminarCuenta(idRegistro);
                JOptionPane.showMessageDialog(this, "La cuenta ha sido eliminada correctamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                // Actualizar la tabla después de la eliminación
                btnVerCuentasActionPerformed(evt);
            } else {
                // Si se están mostrando transacciones, llamar al método para eliminar transacción
                GestorCuentas.eliminarTransaccion(idRegistro);
                JOptionPane.showMessageDialog(this, "La transacción ha sido eliminada correctamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                // Actualizar la tabla después de la eliminación
                btnTransaccionesActionPerformed(evt);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnTrasnferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrasnferirActionPerformed
         // Crea un nuevo JDialog
    JDialog dialog = new JDialog(this, "Transferencias", true);
    dialog.setUndecorated(true);

    // Crea una nueva instancia de FormTransferencia
    FormTransferencia formTransferencia = new FormTransferencia(dialog, this);

        // Agrega el formulario de transferencia al JDialog
        dialog.getContentPane().add(formTransferencia.getContentPane());

        // Configura el tamaño del JDialog para que coincida con el de FormTransferencia
        dialog.setSize(formTransferencia.getSize());

        // Centra el JDialog en la pantalla
        dialog.setLocationRelativeTo(null);

        // Muestra el JDialog (esto bloqueará la interacción con el JFrame hasta que el JDialog se cierre)
        dialog.setVisible(true);
    }//GEN-LAST:event_btnTrasnferirActionPerformed

    private void btnDepositar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositar1ActionPerformed
   // Crear un JDialog
    JDialog dialog = new JDialog(this, "Mi JDialog", true);
    dialog.setModal(true); // Hacer que el JDialog sea modal
    dialog.setUndecorated(true); // Quitar el marco y los botones del JDialog

    // Aquí puedes abrir un formulario de depósito y manejar la lógica directamente en ese formulario
    FormDepositar formDeposito = new FormDepositar(dialog, this, "Depositar");
    dialog.setContentPane(formDeposito.getContentPane());
    dialog.pack();
    dialog.setLocationRelativeTo(this); // Hacer que el JDialog aparezca en el centro de la pantalla
    dialog.setVisible(true);
    }//GEN-LAST:event_btnDepositar1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        setState(FormMenuPrincipal.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        // Crear un JDialog
    JDialog dialog = new JDialog(this, "Mi JDialog", true);
    dialog.setModal(true); // Hacer que el JDialog sea modal
    dialog.setUndecorated(true); // Quitar el marco y los botones del JDialog

    // Aquí puedes abrir un formulario de retiros y manejar la lógica directamente en ese formulario
    FormRetiros formRetiros = new FormRetiros(dialog, this, "Retirar");
    dialog.setContentPane(formRetiros.getContentPane());
    dialog.pack();
    dialog.setLocationRelativeTo(this); // Hacer que el JDialog aparezca en el centro de la pantalla
    dialog.setVisible(true);

    }//GEN-LAST:event_btnRetirarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenuPrincipal().setVisible(true);
                ConexionBD conexion = new ConexionBD();
                conexion.establecerConexion();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCuenta1;
    private javax.swing.JButton btnDepositar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JButton btnTrasnferir;
    private javax.swing.JButton btnVerClientes;
    private javax.swing.JButton btnVerCuentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloBanco;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JTable tblGeneral;
    // End of variables declaration//GEN-END:variables
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 3 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
