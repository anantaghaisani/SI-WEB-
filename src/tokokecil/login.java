/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;

import java.sql.SQLException;
import javaswingdev.message.MessageDialog;
//import javaswingdev.message.MessageDialog;
import javax.swing.JOptionPane;
import test.Message;
import static test.Message.judulJoption;
import static test.Message.txtJoption;
import test.failMessage;
import static test.failMessage.judulWarn;
import static test.failMessage.txtWarn;

/**
 *
 * @author VivoBook
 */
public class login extends java.awt.Frame {

    private int wrongPasswordCount;
    public static String akses;

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtPass = new textfield.PasswordField();
        txtUsrnm = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(234, 204, 154));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Lupa Password_ Klik disini.png"))); // NOI18N
        jPanel1.add(jLabel2);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 810, 400, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginbtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1203, 712, -1, -1));

        txtPass.setBackground(new java.awt.Color(240, 223, 194));
        txtPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPass.setLabelText("Password");
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 510, 370, 70));

        txtUsrnm.setBackground(new java.awt.Color(240, 223, 194));
        txtUsrnm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsrnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsrnmActionPerformed(evt);
            }
        });
        add(txtUsrnm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 380, 370, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login (1).png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, -1));

        setSize(new java.awt.Dimension(1932, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int maxWrongPasswordCount = 3;

    try {
    String username = txtUsrnm.getText();
    String password = txtPass.getText();

    if (username.isEmpty() || password.isEmpty()) {
        failMessage me = new failMessage(this, true);
            judulWarn.setText("Login Gagal!");
            txtWarn.setText("Username dan Password harus diisi");
            me.showAlert();
        //JOptionPane.showMessageDialog(this, "Username dan password harus diisi", "Login Gagal", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String sql = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";
    java.sql.Connection conn = new config().akses();
    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
    java.sql.ResultSet rs = pst.executeQuery(sql);

    boolean loggedIn = false; // Menambahkan variabel untuk melacak status login

    while (rs.next()) {
        String hak = rs.getString("hak");
        if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && hak.equals("admin")) {
        
            akses = "admin";
        Message me = new Message(this, true);
        judulJoption.setText("Login Berhasil!");
        txtJoption.setText("Masuk Sebagai Admin");
        me.showAlert();
        
        try{
            String sql1 = "INSERT INTO `history_login` (`admin`) VALUES ('"+username+"')";
            java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
            pst1.executeUpdate();
            pst1.close();
        }catch(Exception e){}
        
            new homeAdmin().setVisible(true);
            txtUsrnm.setText("");
            txtPass.setText("");
            this.dispose();
            loggedIn = true; // Mengatur status login sebagai berhasil
        } else if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")) && hak.equals("owner")) {
            
            akses = "owner";
            Message me = new Message(this, true);
        judulJoption.setText("Login Berhasil!");
        txtJoption.setText("Masuk Sebagai Owner");
        me.showAlert();
                try{
            String sql1 = "INSERT INTO `history_login` (`admin`) VALUES ('"+username+"')";
            java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
            pst1.executeUpdate();
            pst1.close();
        }catch(Exception e){}
            this.setVisible(false);
            new homeOwner().setVisible(true);
            loggedIn = true; // Mengatur status login sebagai berhasil
        }
    }

    if (!loggedIn) { // Jika status login masih false
        wrongPasswordCount++; // Increment wrongPasswordCount
        int remainingAttempts = maxWrongPasswordCount - wrongPasswordCount;

        if (remainingAttempts > 0) {
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Username atau Password Salah!");
            txtWarn.setText("Sisa Percobaan: "+ remainingAttempts);
            me.showAlert();
            //JOptionPane.showMessageDialog(this, "Username atau Password salah. Sisa percobaan: " + remainingAttempts, "Login Gagal", JOptionPane.WARNING_MESSAGE);
            txtUsrnm.setText("");
            txtPass.setText("");
        } else {
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Anda Melampaui Batas Percobaan");
            txtWarn.setText("Silahkan menjawab pertanyaan jika lupa password");
            me.showAlert();
            //JOptionPane.showMessageDialog(this, "Anda telah melebihi batas percobaan. Silahkan menjawab pertanyaan jika lupa password.", "Login Gagal", JOptionPane.WARNING_MESSAGE);
            new lupaPassword().setVisible(true); // Mengarahkan ke frame lupa password
            this.dispose();
        }
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}
          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUsrnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsrnmActionPerformed

        String rf = txtUsrnm.getText().toString();
        String usrnm = null;
        String pass = null;

        try{
            String sql = "SELECT username, password from login where rf_id = '"+rf+"'";        //jadi kalo id nya ditemukan maka akan tersimpan
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                usrnm = res.getString("username");      //akan tersimpan disini tapi kalo id nya tidak ditemukan maka
                pass = res.getString("password");
            }                                               //variable tadi akan bernilai seperti didlarasi sebelumnya yaitu null
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
                 try{
                        String sql = "SELECT * FROM login WHERE username='"+ usrnm
                                +"'AND password='"+pass+"'";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet rs = pst.executeQuery(sql);
            
            
                        if(rs.next()){
                            String hak = rs.getString("hak");
                            if(usrnm.equals(rs.getString("username"))
                                   && pass.equals(rs.getString("password"))&& hak.equals("admin")){
                                    akses = "admin";
                                    Message me = new Message(this, true);
                                    judulJoption.setText("Login Berhasil!");
                                    txtJoption.setText("Masuk Sebagai Admin");
                                    me.showAlert();
                                            try{
                                                String sql1 = "INSERT INTO `history_login` (`admin`) VALUES ('"+usrnm+"')";
                                                java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
                                                pst1.executeUpdate();
                                                pst1.close();
                                            }catch(Exception e){}
                                    new homeAdmin().setVisible(true);
                                    txtUsrnm.setText("");
                                    txtPass.setText("");
                                    this.dispose();
                                }else if(usrnm.equals(rs.getString("username"))
                                   && pass.equals(rs.getString("password"))&& hak.equals("owner")){
                                    akses = "owner";
                                    Message me = new Message(this, true);
                                    judulJoption.setText("Login Berhasil!");
                                    txtJoption.setText("Masuk Sebagai Owner");
                                    me.showAlert();
                                            try{
                                                String sql1 = "INSERT INTO `history_login` (`admin`) VALUES ('"+usrnm+"')";
                                                java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
                                                pst1.executeUpdate();
                                                pst1.close();
                                            }catch(Exception e){}
                                    this.setVisible(false);
                                    new homeOwner().setVisible(true);
                                }
                            } else {
                                //JOptionPane.showMessageDialog(this, "RF Id Tidak Terdaftar");
                                failMessage me = new failMessage(this, true);
            judulWarn.setText("Login Gagal!");
            txtWarn.setText("Kartu anda belum terdaftar");
            me.showAlert();
                            }
            
                    } catch (SQLException ex) {
                    }
    }//GEN-LAST:event_txtUsrnmActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
 new lupaPassword().setVisible(true);
  this.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private textfield.PasswordField txtPass;
    private textfield.TextField txtUsrnm;
    // End of variables declaration//GEN-END:variables
}
