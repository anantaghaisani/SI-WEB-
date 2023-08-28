/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tokokecil;

import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author VivoBook
 */

public class setoranSupplier extends javax.swing.JFrame {

    /** Creates new form setoranSupplier */
    
    public void cmBoxSup(){
        try{
            String sql = "WITH ttlDt AS ( " 
                    +"SELECT suplier.id_suplier FROM detail " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail.id_suplier "
                    +"JOIN transaksi ON detail.no_nota = transaksi.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY suplier.id_suplier ), " 
                    +"ttlPkt AS ( " 
                    +"SELECT suplier.id_suplier FROM detail_paket " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY suplier.id_suplier " 
                    +") "
                    + "SELECT suplier.id_suplier, suplier.suplier FROM suplier " 
                    +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                    +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                    +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                    +"GROUP BY suplier.id_suplier;";

            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmSup.addItem(res.getString("id_suplier") +" - "+res.getString("suplier"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void tampilSemua(){
        
        try{
            String sql = "WITH ttlDt AS ( " 
                    +"SELECT suplier.id_suplier,detail.no_nota, SUM(detail.qty) AS total_qty_dt, SUM(detail.qty*brngDt.harga_beli) AS setoranDt " 
                    +"FROM detail " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                    +"JOIN transaksi ON detail.no_nota = transaksi.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE DATE (pengambilan.tanggal_pengambilan) = CURDATE() AND detail.id_suplier IS NOT NULL " 
                    +"), " 
                    +"ttlPkt AS ( " 
                    +"SELECT suplier.id_suplier, detail_paket_tr.no_nota, " 
                    +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS total_qty_pkt, SUM(brngDt.harga_beli*(detail_paket.qty*detail_paket_tr.qty)) AS setoranPkt " 
                    +"FROM detail_paket " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE DATE (pengambilan.tanggal_pengambilan) = CURDATE() AND detail_paket.id_suplier IS NOT NULL " 
                    +") " 
                    +"SELECT SUM(total) AS sum_total , SUM(totalSetoran) AS Setorannya " 
                    +"FROM ( " 
                    +"SELECT IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.total_qty_dt + ttlPkt.total_qty_pkt,  COALESCE(ttlDt.total_qty_dt,0) + COALESCE(ttlPkt.total_qty_pkt,0)) AS total, " 
                    +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.setoranDt + ttlPkt.setoranPkt,  COALESCE(ttlDt.setoranDt,0) + COALESCE(ttlPkt.setoranPkt,0)) AS totalSetoran " 
                    +"FROM ttlDt " 
                    +"LEFT JOIN ttlPkt ON ttlDt.id_suplier = ttlPkt.id_suplier " 
                    +"UNION " 
                    +"SELECT IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.total_qty_dt + ttlPkt.total_qty_pkt,  COALESCE(ttlDt.total_qty_dt,0) + COALESCE(ttlPkt.total_qty_pkt,0)) AS total, " 
                    +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.setoranDt + ttlPkt.setoranPkt,  COALESCE(ttlDt.setoranDt,0) + COALESCE(ttlPkt.setoranPkt,0)) AS totalSetoran " 
                    +"FROM ttlDt " 
                    +"RIGHT JOIN ttlPkt ON ttlDt.id_suplier = ttlPkt.id_suplier " 
                    +") AS subquery; ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                qty.setText(res.getString("sum_total"));
                setoran.setText(res.getString("Setorannya"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "error pas nampilin qty dan setoran CmBox Semua");
        }
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Suplier");
        model.addColumn("Suplier");
        model.addColumn("Id Barang");
        model.addColumn("Barang");
        model.addColumn("Qty");
        model.addColumn("Setoran");
        

        try{
            String sql = "SELECT detail.id_suplier, suplier.suplier, detail.id_barang, brngDt.nama_barang, SUM(detail.qty), SUM(detail.qty*brngDt.harga_beli) AS setoranDt " 
                    +"FROM detail " 
                    +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                    +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                    +"JOIN pengambilan ON pengambilan.no_nota = transaksi.no_nota " 
                    +"WHERE detail.id_suplier IS NOT NULL AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY suplier.id_suplier, detail.id_barang " 
                    +"UNION " 
                    +"SELECT detail_paket.id_suplier, suplier.suplier, detail_paket.id_barang, brngDt.nama_barang, SUM(detail_paket.qty)*detail_paket_tr.qty, "
                    + "SUM(brngDt.harga_beli*(detail_paket.qty*detail_paket_tr.qty)) AS setoranPkt " 
                    +"FROM detail_paket " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE detail_paket.id_suplier IS NOT NULL AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY suplier.id_suplier, detail_paket.id_barang";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    model.addRow(new Object[]{res.getString(1),
                        res.getString(2),res.getString(3),res.getString(4),
                        res.getString(5),res.getString(6)
                    });
                }
            tbDt.setModel(model);  
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "error pas nampilin detailnya CmBox Semua");
        }
    }
    
    public setoranSupplier() {
        initComponents();
        cmBoxSup();
        tampilSemua();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        qty = new javax.swing.JTextField();
        setoran = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmSup = new combo_suggestion.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDt = new table.tabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        qty.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(1085, 356, 218, 80));

        setoran.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        setoran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setoran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setoranActionPerformed(evt);
            }
        });
        jPanel1.add(setoran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 356, 408, 80));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BrowniesTitle.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backbtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangleup.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 0, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangledown.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pic.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/army.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/SETORAN SUPPLIER.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, -1));

        cmSup.setBorder(null);
        cmSup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua" }));
        cmSup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cmSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmSupActionPerformed(evt);
            }
        });
        jPanel1.add(cmSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 460, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field 3col.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        tbDt.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jScrollPane3.setViewportView(tbDt);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 1360, 400));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/jumlah setoran text field (1).png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1373, 310, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/qty text field.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1077, 311, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/retur.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 920, -1, -1));

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

    private void cmSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSupActionPerformed
        // TODO add your handling code here:
        String selectedOption = cmSup.getSelectedItem().toString(); // Mengambil pilihan yang dipilih dari combo box sebagai string
        String[] parts = selectedOption.split(" - "); // Memisahkan string berdasarkan pemisah " - "
        String id = parts[0];
        
        if (selectedOption.equals("Semua")) {
            tampilSemua();
        }else{
        try{
            String sql = "WITH ttlDt AS ( " 
                    +"SELECT suplier.id_suplier, COALESCE(SUM(detail.qty),0) AS total_qty_dt, " 
                    +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS setoranDt " 
                    +"FROM detail " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                    +"JOIN transaksi ON detail.no_nota = transaksi.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE suplier.id_suplier = '"+ id +"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY suplier.id_suplier " 
                    +"), " 
                    +"ttlPkt AS ( " 
                    +"SELECT suplier.id_suplier, " 
                    +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS total_qty_pkt, " 
                    +"COALESCE(SUM(brngDt.harga_beli*(detail_paket.qty*detail_paket_tr.qty)),0) AS setoranPkt " 
                    +"FROM detail_paket " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE suplier.id_suplier = '"+ id +"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY suplier.id_suplier " 
                    +") " 
                    +"SELECT suplier.id_suplier, suplier.suplier, IF(ttlDt.id_suplier = ttlPkt.id_suplier,  "
                    +"ttlDt.total_qty_dt + ttlPkt.total_qty_pkt,  COALESCE(ttlDt.total_qty_dt,0)+COALESCE(ttlPkt.total_qty_pkt,0)) AS total, "
                    +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.setoranDt + ttlPkt.setoranPkt,  COALESCE(ttlDt.setoranDt,0)+COALESCE(ttlPkt.setoranPkt,0)) AS totalSetoran " 
                    +"FROM suplier " 
                    +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier "
                    +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier "
                    + "WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                    +"GROUP BY suplier.id_suplier; ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                qty.setText(res.getString("total"));
                setoran.setText(res.getString("totalSetoran"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "error pas nampilin qty dan setoran");
        }
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Barang");
        model.addColumn("Barang");
        model.addColumn("Qty");
        model.addColumn("Setoran");
        

        try{
            String sql = "SELECT detail.id_barang, brngDt.nama_barang, SUM(detail.qty), SUM(detail.qty*brngDt.harga_beli) AS setoranDt " 
                    +"FROM detail " 
                    +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                    +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                    +"JOIN pengambilan ON pengambilan.no_nota = transaksi.no_nota " 
                    +"WHERE suplier.id_suplier = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY detail.id_barang " 
                    +"UNION " 
                    +"SELECT detail_paket.id_barang, brngDt.nama_barang, SUM(detail_paket.qty)*detail_paket_tr.qty, "
                    + "SUM(brngDt.harga_beli*(detail_paket.qty*detail_paket_tr.qty)) AS setoranPkt " 
                    +"FROM detail_paket " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                    +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE suplier.id_suplier = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() " 
                    +"GROUP BY detail_paket.id_barang";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    model.addRow(new Object[]{res.getString(1),
                        res.getString(2),res.getString(3),res.getString(4)
                    });
                }
            tbDt.setModel(model);                                              
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "error pas nampilin detailnya");
        }
        }
    }//GEN-LAST:event_cmSupActionPerformed

    private void setoranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setoranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setoranActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new homeOwner().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int br = tbDt.getSelectedRow();
        String nt = tbDt.getValueAt(br, 0).toString();
        String id = tbDt.getValueAt(br, 1).toString();
        String perbandingan = null;
        
        try{
            String sql = "SELECT keterangan FROM barang WHERE id_barang = '"+id+"' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("keterangan");
                    }
        }catch(Exception e){}
        
        if(perbandingan.equals("normal")){
            try{
                String sql = "DELETE FROM detail WHERE detail.no_nota = '"+nt+"' AND detail.id_barang = '"+id+"' ";       
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();

                JOptionPane.showMessageDialog(this, "Barang Direturn");

                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
        }else if(perbandingan.equals("paket")){
            JOptionPane.showMessageDialog(this, "Barang Paket tidak bisa Direturn");
    }//GEN-LAST:event_jButton2ActionPerformed
    }
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
            java.util.logging.Logger.getLogger(setoranSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(setoranSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(setoranSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(setoranSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new setoranSupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combo_suggestion.ComboBoxSuggestion cmSup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField setoran;
    private table.tabel tbDt;
    // End of variables declaration//GEN-END:variables

}
