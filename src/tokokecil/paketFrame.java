/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tokokecil;

import static tokokecil.transaksiFrame.tableTransaksi;
import static tokokecil.transaksiFrame.txtNota;
import static tokokecil.transaksiFrame.txtTotal;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class paketFrame extends javax.swing.JFrame {

    /** Creates new form paketFrame */
    
    public String idntts;
    public void autonumber() {
        try {
            String sql = "select * from detail_paket_tr order by identitas_pkt desc";
            java.sql.Connection conn = new config().akses();
            Statement pst = conn.createStatement();
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()) {
                String idntt = rs.getString("identitas_pkt").substring(2);
                String NT = "" + (Integer.parseInt(idntt) +1);
                String No1 = "";

                if(NT.length()== 1)
                {No1 = "000";}
                else if(NT.length()== 2)
                {No1 = "00";}
                else if(NT.length()== 3)
                {No1 = "0";}
                else if(NT.length()== 4)
                {No1 = "";}
                idntts = "IP" + No1 + NT;
            } else {
                idntts = "IP0001";
            }
            txtNota.disable();
            rs.close();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void cmbox(){
        try{
            String sql = "SELECT nama_paket from paket ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                pilihSize.addItem(res.getString("nama_paket"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void tableBarang(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Barang");

        try{
            String sql = "SELECT id_barang, nama_barang FROM barang where keterangan = 'paket'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2)
                });
            } 
            tbBarang.setModel(model);
        }catch(SQLException ex){
            
        }
     }
    
    private void tableTransaksi(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Barang");
        tbTransaksi.setModel(model);

     }
    
    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        model.addRow(new Object[]{
            id.getText(),
            nama.getText(),
        });
    }
    
    public void tbTransaksi(){
         DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("No Nota");
        model.addColumn("Id Paket");
        model.addColumn("Paket");
        model.addColumn("Jumlah");
        model.addColumn("Harga Paket");
        model.addColumn("Total");
       tableTransaksi.setModel(model);
    }
    
    public int isian = 0;
    public int mcmKue = 0;
    
    public String idPaket;
    public void comboBoxSwitch(){
        String size = pilihSize.getSelectedItem().toString();
        try{
            String sql = "SELECT id_paket, nama_paket, jml_kue, harga, macam_kue from paket where nama_paket = '"+size+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    idPaket = res.getString("id_paket");
                    sizePkt.setText(res.getString("nama_paket"));
                    lbl.setText(res.getString("jml_kue"));
                    hrg.setText(res.getString("harga"));
                    macamKue.setText(res.getString("macam_kue"));
                    
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
                
        isian = Integer.valueOf(lbl.getText());
        mcmKue = Integer.valueOf(macamKue.getText());
        
    }
    
    
    public paketFrame() {
        initComponents();
        cmbox();
        autonumber();
        tableBarang();
        tableTransaksi();
        String nota = txtNota.getText();
        notanya.setText(nota);
        
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
        txtCari = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        pilihSize = new combo_suggestion.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        macamKue = new javax.swing.JTextField();
        notanya = new javax.swing.JTextField();
        sizePkt = new javax.swing.JTextField();
        lbl = new javax.swing.JTextField();
        hrg = new javax.swing.JTextField();
        pilih = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        pesan = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        qty = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBarang = new table.tabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTransaksi = new table.tabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(1020, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCari.setBackground(new java.awt.Color(220, 193, 151));
        txtCari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCari.setBorder(null);
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 190, 30));

        id.setBackground(new java.awt.Color(220, 193, 151));
        id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        id.setBorder(null);
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 613, 120, 30));

        nama.setBackground(new java.awt.Color(220, 193, 151));
        nama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nama.setBorder(null);
        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        jPanel1.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 615, 120, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back pp.png"))); // NOI18N
        jButton1.setBorder(null);
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        pilihSize.setBorder(null);
        pilihSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Select---" }));
        pilihSize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pilihSize.setMinimumSize(new java.awt.Dimension(166, 30));
        pilihSize.setPreferredSize(new java.awt.Dimension(166, 31));
        pilihSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihSizeActionPerformed(evt);
            }
        });
        jPanel1.add(pilihSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 210, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bar.png"))); // NOI18N
        jLabel8.setToolTipText("");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/jamcombopp.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 135, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/searctrns PP.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 135, -1, -1));

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/resetrns PP.png"))); // NOI18N
        reset.setBorder(null);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 135, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldtrnsmidpp.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 610, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldtrnsmidpp.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, -1, -1));

        macamKue.setBackground(new java.awt.Color(220, 193, 151));
        macamKue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        macamKue.setBorder(null);
        macamKue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                macamKueActionPerformed(evt);
            }
        });
        jPanel1.add(macamKue, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 283, 170, 25));

        notanya.setBackground(new java.awt.Color(220, 193, 151));
        notanya.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        notanya.setBorder(null);
        notanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notanyaActionPerformed(evt);
            }
        });
        jPanel1.add(notanya, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, 170, 30));

        sizePkt.setBackground(new java.awt.Color(220, 193, 151));
        sizePkt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sizePkt.setBorder(null);
        jPanel1.add(sizePkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 150, 170, 25));

        lbl.setBackground(new java.awt.Color(220, 193, 151));
        lbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl.setBorder(null);
        jPanel1.add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 217, 170, 25));

        hrg.setBackground(new java.awt.Color(220, 193, 151));
        hrg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hrg.setBorder(null);
        jPanel1.add(hrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 160, 30));

        pilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tambahpkt.png"))); // NOI18N
        pilih.setBorder(null);
        pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihActionPerformed(evt);
            }
        });
        jPanel1.add(pilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 700, -1, 30));

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BTLBTNPP.png"))); // NOI18N
        batal.setBorder(null);
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 703, -1, -1));

        pesan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PESANBTNPP.png"))); // NOI18N
        pesan.setBorder(null);
        pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesanActionPerformed(evt);
            }
        });
        jPanel1.add(pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 690, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kcik PP.png"))); // NOI18N
        jButton6.setBorder(null);
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, -1, -1));

        qty.setBackground(new java.awt.Color(220, 193, 151));
        qty.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        qty.setBorder(null);
        jPanel1.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 605, 55, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Qty PP.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1049, 615, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldqtypp.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1095, 600, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldpp.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 247, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldpp.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 278, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Barang PP.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 620, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Barang PP.png"))); // NOI18N
        jLabel11.setText("\n");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 620, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Hargapp.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 222, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No. Nota PP.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 150, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldpp.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 177, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldpp.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 145, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldpp.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 211, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Jumlah Macam PP.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 255, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Kemasan PP.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 120, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Jumlah Isi PP.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 190, -1, -1));

        tbBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBarang.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tbBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBarangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbBarang);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 450, 330));

        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTransaksi.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jScrollPane1.setViewportView(tbTransaksi);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, -1, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1272, 856);
    }// </editor-fold>//GEN-END:initComponents

    private void pilihSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihSizeActionPerformed
        // TODO add your handling code here:
        comboBoxSwitch();
        int row = tbTransaksi.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        
        if (row>=mcmKue) {
            for (mcmKue = mcmKue; row > mcmKue; mcmKue++) {
                model.removeRow(model.getRowCount() - 1);
            }
            comboBoxSwitch();
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Paket terisi Berlebihan!");
            txtWarn.setText("Kue terakhir akan otomatis dibatalkan");
            me.showAlert();
            //JOptionPane.showMessageDialog(this, "Paket Berisi Berlebihan Kue Terakhir otomatis batal");
        }
        txtCari.setText(String.valueOf(mcmKue));
    }//GEN-LAST:event_pilihSizeActionPerformed

    private void macamKueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macamKueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_macamKueActionPerformed

    private void pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihActionPerformed
        // TODO add your handling code here:
         int row = tbTransaksi.getRowCount();
                if (row<mcmKue) {
                    loadData();
                }else{
                    failMessage me = new failMessage(this, true);
            judulWarn.setText("Gagal Menambahkan!");
            txtWarn.setText("Paket telah terisi penuh");
            me.showAlert();
                    //JOptionPane.showMessageDialog(this, "Paket Telah Terisi");
                }
    }//GEN-LAST:event_pilihActionPerformed

    private void pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesanActionPerformed
        // TODO add your handling code here:
        int row = tbTransaksi.getRowCount();
        
        if (row==mcmKue) {
        int harga = Integer.valueOf(hrg.getText());
        int banyak = Integer.valueOf(qty.getText());
        int total = harga*banyak;
        int tmbh = Integer.valueOf(txtTotal.getText());
        int totalAkhir = total + tmbh;
        int qtyBrng = isian/mcmKue;
        txtTotal.setText(String.valueOf(totalAkhir));
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();        //udh bisa tapi kalo pesan paket lagi malah ketimpa dan cuma bisa menyimpan 1 paket
        
        model.addRow(new Object[]{notanya.getText(), idPaket, sizePkt.getText(),
                    qty.getText(),hrg.getText(),String.valueOf(total)});

        String Nota = notanya.getText();
        String bandingNt=null;
        try{
            String sql = "SELECT no_nota from transaksi where no_nota = '"+Nota+"'";        //jadi kalo id nya ditemukan maka akan tersimpan
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    bandingNt = res.getString("no_nota");      //akan tersimpan disini tapi kalo id nya tidak ditemukan maka 
                    }                                               //variable tadi akan bernilai seperti didlarasi sebelumnya yaitu null
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        if (!Nota.equals(bandingNt)) {
            try {
             String sql = "insert into transaksi values ('"+Nota+"','',null,null,null,null,null,null,'')";
            
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            
            //JOptionPane.showMessageDialog(null, "Table transaksi tersimpan");
            pst.executeUpdate();
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Transaksi gagal disimpan" + e.getMessage());
        }
        }
        
        
        try {
             String sql = "insert into detail_paket_tr values ('"+idntts+"','"+Nota+"','"+idPaket+"','"+qty.getText()+"','"+total+"')";
            
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            
            //JOptionPane.showMessageDialog(null, "Data Detail Tr disimpan");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil Ditambahkan!");
            txtJoption.setText("Lanjutkan ke Transaksi");
            me.showAlert();
            pst.executeUpdate();
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data detail TR gagal disimpan" + e.getMessage());
        }
        
        try {
            java.sql.Connection conn = new config().akses();
            int baris = tbTransaksi.getRowCount();
            
            for(int i = 0; i < baris; i++) {
                String sql = "insert into detail_paket (identitas_pkt, id_barang, qty) values ('"
                        + idntts + "','" + tbTransaksi.getValueAt(i, 0) + "','" + qtyBrng +"')";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Penyimpanan detail paket Gagal" + e.getMessage());
        }
        
        this.setVisible(false);
        
        }else{
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Gagal Memesan!");
            txtWarn.setText("Lengkapi isi kue terlebih dahulu");
            me.showAlert();
            //JOptionPane.showMessageDialog(this, "Lengkapi Isi Kue Terlebih Dahulu");
        }
    }//GEN-LAST:event_pesanActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbTransaksi.getModel();
        int row = tbTransaksi.getSelectedRow();
        model.removeRow(row);
    }//GEN-LAST:event_batalActionPerformed

    private void notanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notanyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notanyaActionPerformed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Barang");

        try{
            String sql = "SELECT id_barang, nama_barang, harga_jual FROM barang where keterangan = 'paket' and nama_barang LIKE '%"+txtCari.getText()+"%'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2)
                });
            }
            tbBarang.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_txtCariActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        tableBarang();
        txtCari.setText("");
    }//GEN-LAST:event_resetActionPerformed

    private void tbBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBarangMouseClicked
        // TODO add your handling code here:
        int baris = tbBarang.rowAtPoint(evt.getPoint());
        
        String id_barang = tbBarang.getValueAt(baris, 0).toString();
        id.setText(id_barang);
        
        String nama_barang = tbBarang.getValueAt(baris, 1).toString();
        nama.setText(nama_barang);
    }//GEN-LAST:event_tbBarangMouseClicked

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
            java.util.logging.Logger.getLogger(paketFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(paketFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(paketFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(paketFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new paketFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JTextField hrg;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lbl;
    private javax.swing.JTextField macamKue;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField notanya;
    private javax.swing.JButton pesan;
    private javax.swing.JButton pilih;
    private combo_suggestion.ComboBoxSuggestion pilihSize;
    private javax.swing.JTextField qty;
    private javax.swing.JButton reset;
    private javax.swing.JTextField sizePkt;
    private table.tabel tbBarang;
    private table.tabel tbTransaksi;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables

}
