/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;

import table.tabel;
import static tokokecil.transaksiFrame.jamAmbil;
import static tokokecil.transaksiFrame.tableTransaksi;
import static tokokecil.transaksiFrame.tglAmbil;
import static tokokecil.transaksiFrame.txtBayar;
import static tokokecil.transaksiFrame.txtNamaCust;
import static tokokecil.transaksiFrame.txtNota;
import static tokokecil.transaksiFrame.txtTotal;
import static tokokecil.pengambilan.jamA;
import static tokokecil.pengambilan.tbAmbil;
import static tokokecil.pengambilan.tglAmbilA;
import static tokokecil.pengambilan.tglTrA;
import static tokokecil.pengambilan.txtNamaA;
import static tokokecil.pengambilan.txtNtA;
import static tokokecil.pelunasanFrame.tbTr;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static tokokecil.pelunasanFrame.txtNamaP;
import static tokokecil.pelunasanFrame.tglP;
import static tokokecil.pelunasanFrame.txtKurangP;
import static tokokecil.pelunasanFrame.txtNotaP;
import static tokokecil.pelunasanFrame.txtTotalP;
import java.awt.Color;
import test.failMessage;
import static test.failMessage.judulWarn;
import static test.failMessage.txtWarn;
import static tokokecil.transaksiFrame.txtKekurangan;
import static tokokecil.transaksiFrame.txtKembalian;
import static tokokecil.transaksiFrame.txtStatus;
import static tokokecil.transaksiFrame.txtTelepon;

/**
 *
 * @author VivoBook
 */
public class pengambilanFrame extends javax.swing.JFrame {

    /**
     * Creates new form pengambilanFrame
     */
    
    public void tablenya(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Nota");
        model.addColumn("Cust");
        model.addColumn("Tanggal Pengambilan");
        model.addColumn("Jam Pengambilan");
        model.addColumn("Status Transaksi");

        try{
            String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.tanggal_pengambilan, pengambilan.jam, transaksi.statusTransaksi "
                    + "FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota where DATE (pengambilan.tanggal_pengambilan) = CURDATE()";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5)
                });
            }
            tb.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    String nt;
    private void tbPelunasanBrng(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("Qty");
        model.addColumn("Total");

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
            tbTr.setModel(model);
        }catch(SQLException ex){
            
        }
     }
    
    public void toAmbil(){
        int baris = tb.getSelectedRow();
        nt = tb.getValueAt(baris, 0).toString();

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
    
    public void toTr(){
        int baris = tb.getSelectedRow();
        nt = tb.getValueAt(baris, 0).toString();
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Nota");
        model.addColumn("Id Barang");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Harga Produk");
        model.addColumn("Total");

         try{
            String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, "
                    + "pengambilan.tanggal_pengambilan, pengambilan.jam, transaksi.telepon, transaksi.grand_total, "
                    + "transaksi.dibayarkan, transaksi.kembalian, transaksi.kurangBayar, transaksi.statusTransaksi " 
                    + "from transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota where transaksi.no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){

                    txtNota.setText(res.getString("no_nota"));
                    txtNamaCust.setText(res.getString("nama_cust"));
                    tglAmbil.setDate(res.getDate("tanggal_pengambilan"));
                    jamAmbil.setSelectedItem(res.getString("jam"));
                    txtTelepon.setText(res.getString("telepon"));
                    txtTotal.setText(res.getString("grand_total"));
                    txtBayar.setText(res.getString("dibayarkan"));
                    txtKembalian.setText(res.getString("kembalian"));
                    txtKekurangan.setText(res.getString("kurangBayar"));
                    txtStatus.setText(res.getString("statusTransaksi"));
                    
                }
        }catch(Exception e){
             System.err.println(e.getMessage());
        }
        
        try{
            String sql = "SELECT transaksi.no_nota, detail.id_barang, barang.nama_barang, detail.qty, barang.harga_jual, detail.total FROM detail JOIN barang "
                    + "ON barang.id_barang = detail.id_barang JOIN transaksi ON transaksi.no_nota = detail.no_nota"
                    + " where transaksi.no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6)
                });
            }
            
        }catch(SQLException ex){
            
        }
        
        try{
            String sql = "SELECT transaksi.no_nota, detail_paket_tr.identitas_pkt, paket.nama_paket, detail_paket_tr.qty, paket.harga, detail_paket_tr.total FROM detail_paket_tr "
                    + "JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota JOIN paket ON detail_paket_tr.id_paket = paket.id_paket"
                    + " where transaksi.no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6)
                });
            }
            tableTransaksi.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public static String balik;
    
    public pengambilanFrame() {
        initComponents();
        tablenya();
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
        cari = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cmPilih = new combo_suggestion.ComboBoxSuggestion();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ubah = new javax.swing.JButton();
        Proses = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new table.tabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup big.png"))); // NOI18N
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 335, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backbtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        cmPilih.setBorder(null);
        cmPilih.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hari Ini", "Besok", "Semua" }));
        cmPilih.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cmPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmPilihActionPerformed(evt);
            }
        });
        jPanel1.add(cmPilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 338, 480, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BrowniesTitle.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangleup.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 0, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangledown.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pic.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/army.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/DAFTAR PENGAMBILAN.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 182, -1, -1));

        txtCari.setBackground(new java.awt.Color(220, 193, 151));
        txtCari.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCari.setBorder(null);
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 320, 410, 70));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field seach big.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 320, -1, -1));

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RESET BUTTON.png"))); // NOI18N
        reset.setBorder(null);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 320, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field 3col.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editpengambilan.png"))); // NOI18N
        ubah.setBorder(null);
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });
        jPanel1.add(ubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 900, -1, -1));

        Proses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/prosespengambilan.png"))); // NOI18N
        Proses.setBorder(null);
        Proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProsesActionPerformed(evt);
            }
        });
        jPanel1.add(Proses, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 900, 160, -1));

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/btlbtnpengambilan.png"))); // NOI18N
        batal.setBorder(null);
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 900, 160, -1));

        tb.setModel(new javax.swing.table.DefaultTableModel(
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
        tb.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jScrollPane1.setViewportView(tb);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 1470, 350));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1920, 1080);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String akks = login.akses;
        if(akks.equals("admin")){
            this.dispose();
            new homeAdmin().setVisible(true);
            
        }else if(akks.equals("owner")){
            this.dispose();
            new homeOwner().setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
        nt = txtCari.getText();
        String perbandingan = null;
        try{
            String sql = "SELECT statusTransaksi FROM transaksi WHERE no_nota = '"+nt+"' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("statusTransaksi");
                    }
        }catch(Exception e){}
        
        if (perbandingan.equals("DP")) {
            this.setVisible(true);
            new pelunasanFrame().setVisible(true);
            
            try{
            String sql = "SELECT transaksi.nama_cust, transaksi.tgl_transaksi, transaksi.kurangBayar, transaksi.grand_total "
                    + "from transaksi where no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){

                    txtNamaP.setText(res.getString("nama_cust"));
                    tglP.setText(res.getString("tgl_transaksi"));
                    txtKurangP.setText(res.getString("kurangBayar"));
                    txtTotalP.setText(res.getString("grand_total"));
                    
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        txtNotaP.setText(nt);
        tbPelunasanBrng();
            
        }else if (perbandingan.equals("Lunas")) {
            this.setVisible(true);
            new pengambilan().setVisible(true);
            
            txtNtA.setText(nt);
            toAmbil();
        }
    }//GEN-LAST:event_txtCariActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        tablenya();
    }//GEN-LAST:event_resetActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Nota");
        model.addColumn("Cust");
        model.addColumn("Tanggal Pengambilan");
        model.addColumn("Jam Pengambilan");
        model.addColumn("Status Transaksi");

        try{
            String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.tanggal_pengambilan, pengambilan.jam, transaksi.statusTransaksi "
                    + "FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota where transaksi.no_nota = '"+txtCari.getText()+"' OR "
                    + "transaksi.nama_cust LIKE '%"+txtCari.getText()+"%' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5)
                });
            }
            tb.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_cariActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        int baris = tb.getSelectedRow();
        nt = tb.getValueAt(baris, 0).toString();
        String per = null;
        try{
            String sql = "Select IF(pengambilan.tanggal_pengambilan > CURDATE(), 'true','false') AS param FROM pengambilan "
                    + "JOIN transaksi ON transaksi.no_nota = pengambilan.no_nota WHERE transaksi.no_nota = '"+nt+"' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                per = res.getString("param");
            }
        }catch(Exception e){}
        if(per.equals("true")){
        this.setVisible(true);
        new transaksiFrame().setVisible(true);
        toTr();
        balik = "ambilFrame";
        }else if(per.equals("false")){
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Pesanan Tidak Bisa Diedit");
            txtWarn.setText("Tanggal Pengambilan Harus 2 hari sebelum Pengambilan");
            me.showAlert();
        }
    }//GEN-LAST:event_ubahActionPerformed

    private void ProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProsesActionPerformed
        // TODO add your handling code here:
        int baris = tb.getSelectedRow();
        
        nt = tb.getValueAt(baris, 0).toString();
        String status = tb.getValueAt(baris, 4).toString();
        
        if (status.equals("DP")) {
            this.setVisible(true);
            new pelunasanFrame().setVisible(true);
            
            try{
            String sql = "SELECT transaksi.nama_cust, transaksi.tgl_transaksi, transaksi.kurangBayar, transaksi.grand_total "
                    + "from transaksi where no_nota = '"+nt+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){

                    txtNamaP.setText(res.getString("nama_cust"));
                    tglP.setText(res.getString("tgl_transaksi"));
                    txtKurangP.setText(res.getString("kurangBayar"));
                    txtTotalP.setText(res.getString("grand_total"));
                    
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        txtNotaP.setText(nt);
        tbPelunasanBrng();
        
        }else if (status.equals("Lunas")) {
            this.setVisible(true);
            new pengambilan().setVisible(true);
            
            txtNtA.setText(nt);
            toAmbil();
        }
    }//GEN-LAST:event_ProsesActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        int baris = tb.getSelectedRow();
        String dl = tb.getValueAt(baris, 0).toString();
        try{
            String sql = "DELETE FROM transaksi WHERE no_nota = '"+dl+"'";       
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
            
            //JOptionPane.showMessageDialog(this, "Transaksi Dibatalkan");
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Pesanan Dibatalkan!");
            txtWarn.setText("Pesanan berhasil dibatalkan");
            me.showAlert();
                                              
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
    }//GEN-LAST:event_batalActionPerformed

    private void cmPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmPilihActionPerformed
        // TODO add your handling code here:
        String pilih = cmPilih.getSelectedItem().toString();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Nota");
        model.addColumn("Cust");
        model.addColumn("Tanggal Pengambilan");
        model.addColumn("Jam Pengambilan");
        model.addColumn("Status Transaksi");
        
        switch (pilih) {
            case "Hari Ini" : 
                tablenya();
            break;
            case "Besok" :
                try{
                    String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.tanggal_pengambilan, pengambilan.jam, transaksi.statusTransaksi "
                            + "FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota where DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4),
                            res.getString(5)
                        });
                    }
                    tb.setModel(model);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
            case "Semua" : 
                try{
                    String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.tanggal_pengambilan, pengambilan.jam, transaksi.statusTransaksi "
                            + "FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota ORDER BY  pengambilan.tanggal_pengambilan ASC, pengambilan.jam ASC";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4),
                            res.getString(5)
                        });
                    }
                    tb.setModel(model);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
        }
    }//GEN-LAST:event_cmPilihActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(pengambilanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pengambilanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pengambilanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pengambilanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pengambilanFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Proses;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private combo_suggestion.ComboBoxSuggestion cmPilih;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reset;
    private table.tabel tb;
    private javax.swing.JTextField txtCari;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
