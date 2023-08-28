/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;

import static tokokecil.pengambilan.jamA;
import static tokokecil.pengambilan.tbAmbil;
import static tokokecil.pengambilan.tglAmbilA;
import static tokokecil.pengambilan.tglTrA;
import static tokokecil.pengambilan.txtNamaA;
import static tokokecil.pengambilan.txtNtA;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import test.Message;
import static test.Message.judulJoption;
import static test.Message.txtJoption;
import test.failMessage;
import static test.failMessage.judulWarn;
import static test.failMessage.txtWarn;
import static tokokecil.transaksiFrame.txtNota;

/**
 *
 * @author VivoBook
 */
public class pelunasanFrame extends javax.swing.JFrame {

    /**
     * Creates new form pelunasanFrame
     */
    
    public void tbDt(){
         DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Barang");
        model.addColumn("Qty");
        tbDetail.setModel(model);
    }
    
    public void toAmbil(){
        String nt = txtNotaP.getText();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("Qty");
        model.addColumn("Total");

         try{
            String sql = "SELECT transaksi.nama_cust, transaksi.tgl_transaksi, pengambilan.tanggal_pengambilan, pengambilan.jam "
                    + "from transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota where transaksi.no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){

                    txtNamaA.setText(res.getString("nama_cust"));
                    tglTrA.setText(res.getString("tgl_transaksi"));
                    tglAmbilA.setText(res.getString("tanggal_pengambilan"));
                    jamA.setText(res.getString("jam"));
                    
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        try{
            String sql = "SELECT detail.id_barang, barang.nama_barang, detail.qty, detail.total FROM detail JOIN barang "
                    + "ON barang.id_barang = detail.id_barang JOIN transaksi ON transaksi.no_nota = detail.no_nota"
                    + " where transaksi.no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            
        }catch(SQLException ex){
            
        }
        
        try{
            String sql = "SELECT detail_paket_tr.identitas_pkt, paket.nama_paket, detail_paket_tr.qty, detail_paket_tr.total FROM detail_paket_tr "
                    + "JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota JOIN paket ON detail_paket_tr.id_paket = paket.id_paket"
                    + " where transaksi.no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tbAmbil.setModel(model);
        }catch(SQLException ex){
            
        }
    }
    
    public void cetakReport(){
          java.sql.Connection conn = null;
         try{
            conn = new config().akses();
         try{
             String report = ("C:\\Users\\fayat\\OneDrive\\Documents\\NetBeansProjects\\tokokecil\\src\\Nota\\newReport.jrxml");
             HashMap hash = new HashMap();
             hash.put("kd", txtNotaP.getText());
             JasperReport JRpt = JasperCompileManager.compileReport(report);
             JasperPrint JPrint = JasperFillManager.fillReport(JRpt,hash,conn);
             //JasperViewer.viewReport(JPrint, false);
             JasperPrintManager.printReport(JPrint, false);
         }catch(Exception rptexcpt){
             JOptionPane.showMessageDialog(this, rptexcpt.getMessage());
         }
         }catch(Exception e){
             System.out.println(e);
         }
     }
    
    public pelunasanFrame() {
        initComponents();
        tbDt();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtKembalian = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tglP = new javax.swing.JTextField();
        txtNotaP = new javax.swing.JTextField();
        txtNamaP = new javax.swing.JTextField();
        bayar = new javax.swing.JButton();
        txtTotalP = new javax.swing.JTextField();
        txtKurangP = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTr = new table.tabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetail = new table.tabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(928, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtKembalian.setBackground(new java.awt.Color(230, 187, 153));
        txtKembalian.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtKembalian.setBorder(null);
        txtKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembalianActionPerformed(evt);
            }
        });
        jPanel1.add(txtKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 900, 210, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back pelunasan.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 5, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/barpelunasan.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tglP.setBackground(new java.awt.Color(237, 224, 209));
        tglP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tglP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(tglP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 300, 40));

        txtNotaP.setBackground(new java.awt.Color(237, 224, 209));
        txtNotaP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtNotaP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtNotaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 300, 40));

        txtNamaP.setBackground(new java.awt.Color(237, 224, 209));
        txtNamaP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtNamaP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtNamaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 300, 40));

        bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/prosespelunasan.png"))); // NOI18N
        bayar.setBorder(null);
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        jPanel1.add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 960, -1, -1));

        txtTotalP.setBackground(new java.awt.Color(230, 187, 153));
        txtTotalP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTotalP.setBorder(null);
        txtTotalP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalP, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 670, 210, 40));

        txtKurangP.setBackground(new java.awt.Color(230, 187, 153));
        txtKurangP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtKurangP.setBorder(null);
        txtKurangP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKurangPActionPerformed(evt);
            }
        });
        jPanel1.add(txtKurangP, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 745, 210, 40));

        txtBayar.setBackground(new java.awt.Color(230, 187, 153));
        txtBayar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtBayar.setBorder(null);
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        jPanel1.add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 823, 210, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pelunasantxt.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 670, -1, -1));

        tbTr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTr.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tbTr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTrMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbTr);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, 330));

        tbDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDetail.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jScrollPane1.setViewportView(tbDetail);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 310, 200));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBounds(0, 0, 928, 1080);
    }// </editor-fold>//GEN-END:initComponents

    private void txtKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianActionPerformed

    private void txtTotalPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPActionPerformed

    private void txtKurangPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKurangPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKurangPActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
        int kurang, bayar, kembalian;

        kurang = Integer.valueOf(txtKurangP.getText());
        bayar = Integer.valueOf(txtBayar.getText());

        if(kurang <= bayar) {
            kembalian = bayar-kurang;
            txtKembalian.setText(String.valueOf(kembalian));
        } else {
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Pelunasan Gagal!");
            txtWarn.setText("Nominal yang anda masukkan kurang");
            me.showAlert();
            //JOptionPane.showMessageDialog(this, "Uang Kurang");
        }
    }//GEN-LAST:event_txtBayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
        int kurang, bayar, kembalian;

        kurang = Integer.valueOf(txtKurangP.getText());
        bayar = Integer.valueOf(txtBayar.getText());
        kembalian = bayar-kurang;

        if(kurang <= bayar) {
            try {
            String sql = "UPDATE `transaksi` SET dibayarkan = (dibayarkan + '"+bayar+"'), kurangBayar = '0', kembalian = '"+kembalian+"',"
                    + "`statusTransaksi`='Lunas' WHERE no_nota = '"+txtNotaP.getText()+"'";
            
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
            //JOptionPane.showMessageDialog(null, "Transaksi Lunas");
            Message me = new Message(this, true);
            judulJoption.setText("Pelunasan Berhasil");
            txtJoption.setText("Akan Dilanjutkan ke Pengambilan");
            me.showAlert();
            
            cetakReport();
            new pengambilan().setVisible(true);
            txtNtA.setText(txtNotaP.getText());
            toAmbil();
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Status Transaksi Gagal Diubah" + e.getMessage());
        }
        } else {
            //JOptionPane.showMessageDialog(this, "Lunasi Sejumlah Uang yang Kurang");
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Pelunasan Gagal!");
            txtWarn.setText("Lunasi sejumlah uang yang kurang!");
            me.showAlert();
        }
    }//GEN-LAST:event_bayarActionPerformed

    private void tbTrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTrMouseClicked
        // TODO add your handling code here:
        tbDt();
        int baris = tbTr.rowAtPoint(evt.getPoint());
        String id = tbTr.getValueAt(baris, 0).toString();
        String perbandingan = null;
        
        try{
            String sql = "SELECT identitas_pkt from detail_paket_tr where identitas_pkt = '"+id+"'";        
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("identitas_pkt");      
                    }                                              
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

            
            if(id.equals(perbandingan)) {
                DefaultTableModel model = (DefaultTableModel) tbDetail.getModel();
                try{
            String sql = "SELECT detail_paket.id_barang, barang.nama_barang, detail_paket.qty FROM detail_paket_tr "
                    + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN barang ON detail_paket.id_barang = barang.id_barang"
                    + " where detail_paket_tr.identitas_pkt = '"+id+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3)
                });
            }
            tbDetail.setModel(model);
        }catch(SQLException ex){
            
        }
            } 
    }//GEN-LAST:event_tbTrMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pelunasanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pelunasanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pelunasanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pelunasanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pelunasanFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bayar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private table.tabel tbDetail;
    public static table.tabel tbTr;
    public static javax.swing.JTextField tglP;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtKembalian;
    public static javax.swing.JTextField txtKurangP;
    public static javax.swing.JTextField txtNamaP;
    public static javax.swing.JTextField txtNotaP;
    public static javax.swing.JTextField txtTotalP;
    // End of variables declaration//GEN-END:variables
}
