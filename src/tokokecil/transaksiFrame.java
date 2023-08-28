/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;

import java.awt.Color;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
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
public class transaksiFrame extends javax.swing.JFrame {

    /**
     * Creates new form transaksiFrame
     */
    public void simpanUpdatePaket(){
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        
        Date selectedDate = tglAmbil.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = dateFormat.format(selectedDate);
        
        String Nota = txtNota.getText();
        String jam = jamAmbil.getSelectedItem().toString();
        String customer = txtNamaCust.getText();
        String tlp = txtTelepon.getText();
        String totalTr = txtTotal.getText();
        String Kembalian = txtKembalian.getText();
        String kurang = txtKekurangan.getText();
        String bayar = txtBayar.getText();
        String status = txtStatus.getText();
        String statusAmbil = "belum";
        
        try {
             String sql = "UPDATE `transaksi` SET `nama_cust`='"+customer+"',`telepon`='"+tlp+"',`tgl_transaksi`=now(),"
                     + "`grand_total`='"+totalTr+"',`dibayarkan`='"+bayar+"',`kembalian`='"+Kembalian+"',`kurangBayar`='"+kurang+"',"
                     + "`statusTransaksi`='"+status+"' WHERE no_nota = '"+Nota+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            
            //JOptionPane.showMessageDialog(null, "Data disimpan");
            Message me = new Message(this, true);
            judulJoption.setText("Transaksi Berhasil!");
            txtJoption.setText("Nota akan segera dicetak");
            me.showAlert();
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan" + e.getMessage());
        }
        
         try {
            String sql = "DELETE FROM `detail` WHERE no_nota = '"+txtNota.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
            
            //JOptionPane.showMessageDialog(null, "Detail Tr terdelete");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "gagaalll" + e.getMessage());
        }
        
       
        
        String banding = null;
        try{
            String sql = "SELECT no_nota from pengambilan where no_nota = '"+Nota+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    banding = res.getString("no_nota");
                    }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            //if ini untuk menemukan apakah id itu bernilai null atau nilainya ditemukan di database kalo ditemukan maka akan bernilai true dan percabangan tidak dijalankan
            if(!Nota.equals(banding)) {
                try{
                String sql1 = "insert into pengambilan values (?, ?, ?, ?)";
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst1.setString(1, Nota);
                pst1.setString(2, tgl);
                pst1.setString(3, jam);
                pst1.setString(4, statusAmbil);
                pst1.executeUpdate();
                pst1.close();
                }catch(Exception e){}
            }else{
                try{
                String sql1 = "UPDATE `pengambilan` SET `no_nota`='"+Nota+"',"
                        + "`tanggal_pengambilan`='"+tgl+"',`jam`='"+jam+"',"
                        + "`status_pengambilan`='"+statusAmbil+"' WHERE no_nota = '"+Nota+"'";
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                pst1.executeUpdate();
                pst1.close();
                }catch(Exception e){}
            }
       
        
        for (int i = 0; i < model.getRowCount(); i++) { //nyimpan detail transaksi
            String id = model.getValueAt(i, 1).toString();
            String perbandingan = null;      //ini penyimpanan sementara untuk menyimpan id paket
            
            try{
            String sql = "SELECT id_paket from paket where id_paket = '"+id+"'";        //jadi kalo id nya ditemukan maka akan tersimpan
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("id_paket");      //akan tersimpan disini tapi kalo id nya tidak ditemukan maka 
                    }                                               //variable tadi akan bernilai seperti didlarasi sebelumnya yaitu null
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            //if ini untuk menemukan apakah id itu bernilai null atau nilainya ditemukan di database kalo ditemukan maka akan bernilai true dan percabangan tidak dijalankan
            if(!id.equals(perbandingan)) { // Memeriksa jika id tidak sama dengan variable perbandingan
                String nota = txtNota.getText();
                String idBrng = tableTransaksi.getValueAt(i, 1).toString();
                String qty = tableTransaksi.getValueAt(i, 3).toString();
                String totalnya = tableTransaksi.getValueAt(i, 5).toString();

                // Memasukkan data ke dalam database
                try {
                    String sql = "insert into detail (no_nota, id_barang, qty, total) values ('"
                        + nota + "','" + idBrng + "','" + qty + "','" + totalnya+"')";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.executeUpdate();
                    pst.close();
                }catch(Exception e){
                        
                }
            }
        }
    }
    
    
    public void simpanInsertTransaksi(){
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        
        Date selectedDate = tglAmbil.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = dateFormat.format(selectedDate);
        
        String Nota = txtNota.getText();
        String jam = jamAmbil.getSelectedItem().toString();
        String customer = txtNamaCust.getText();
        String tlp = txtTelepon.getText();
        String totalTr = txtTotal.getText();
        String Kembalian = txtKembalian.getText();
        String kurang = txtKekurangan.getText();
        String bayar = txtBayar.getText();
        String status = txtStatus.getText();
        String statusAmbil = "belum";
        
        try {
             String sql = "INSERT INTO `transaksi` values('"+ Nota + "','" + customer + "','" + tlp + "',now(),'"
                     + totalTr + "','" + bayar + "','" + Kembalian + "','"+ kurang + "','" + status+ "')"; 
              String sql1 = "insert into pengambilan values (?, ?, ?, ?)";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);

            pst1.setString(1, Nota);
            pst1.setString(2, tgl);
            pst1.setString(3, jam);
            pst1.setString(4, statusAmbil);
            
            //JOptionPane.showMessageDialog(null, "Data disimpan");
            Message me = new Message(this, true);
            judulJoption.setText("Transaksi Berhasil!");
            txtJoption.setText("Nota akan segera tercetak");
            me.showAlert();
            pst.executeUpdate();
            pst1.executeUpdate();
            pst.close();
            pst1.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan" + e.getMessage());
        }
        
        for (int i = 0; i < model.getRowCount(); i++) { //nyimpan detail transaksi
            String id = model.getValueAt(i, 1).toString();
            String perbandingan = null;      //ini penyimpanan sementara untuk menyimpan id paket
            
            try{
            String sql = "SELECT id_paket from paket where id_paket = '"+id+"'";        //jadi kalo id nya ditemukan maka akan tersimpan
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("id_paket");      //akan tersimpan disini tapi kalo id nya tidak ditemukan maka 
                    }                                               //variable tadi akan bernilai seperti didlarasi sebelumnya yaitu null
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            //if ini untuk menemukan apakah id itu bernilai null atau nilainya ditemukan di database kalo ditemukan maka akan bernilai true dan percabangan tidak dijalankan
            if(!id.equals(perbandingan)) { // Memeriksa jika id tidak sama dengan variable perbandingan
                String nota = txtNota.getText();
                String idBrng = tableTransaksi.getValueAt(i, 1).toString();
                String qty = tableTransaksi.getValueAt(i, 3).toString();
                String totalnya = tableTransaksi.getValueAt(i, 5).toString();

                // Memasukkan data ke dalam database
                try {
                    String sql = "insert into detail (no_nota, id_barang, qty, total) values ('"
                        + nota + "','" + idBrng + "','" + qty + "','" + totalnya+"')";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.executeUpdate();
                    pst.close();
                }catch(Exception e){
                        
                }
            }
        }
    }
    
    public void simpanKemasan(){
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        
         try {
            String sql = "DELETE FROM `detail_kemasan` WHERE no_nota = '"+txtNota.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        for (int i = 0; i < model.getRowCount(); i++) { 
            String id = model.getValueAt(i, 1).toString();
            String perbandingan = null;      
            
            try{
            String sql = "SELECT id_kemasan from kemasan where id_kemasan = '"+id+"'";       
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("id_kemasan");
                    }                                               
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            if(id.equals(perbandingan)) {
                String nota = txtNota.getText();
                String idBrng = tableTransaksi.getValueAt(i, 1).toString();
                String qty = tableTransaksi.getValueAt(i, 3).toString();
                String totalnya = tableTransaksi.getValueAt(i, 5).toString();

                // Memasukkan data ke dalam database
                try {
                    String sql = "insert into detail_kemasan (no_nota, id_kemasan, qty, total) values ('"
                        + nota + "','" + idBrng + "','" + qty + "','" + totalnya+"')";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.executeUpdate();
                    pst.close();
                }catch(Exception e){
                        
                }
            }
        }
    }
    
    
    public int total;
     public void totalbiaya() {
        int jumlahBaris = tableTransaksi.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for(int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(tableTransaksi.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(tableTransaksi.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txtTotal.setText(String.valueOf(totalBiaya));
    }
    
//    public String qtyKemasan;             ini ngga jadi cuma untuk mengetahui jumlah QTY dalam table transaksi
//    public void totalQty() {
//        int jumlahBaris = tableTransaksi.getRowCount();
//        int totalQty = 0;
//        int jmlQty;
//        for(int i = 0; i < jumlahBaris; i++) {
//            jmlQty = Integer.parseInt(tableTransaksi.getValueAt(i, 3).toString());
//            totalQty = totalQty + jmlQty ;
//        }
//        
//        qtyKemasan = String.valueOf(totalQty);
//    }
     
     public void cetakReport(){
          java.sql.Connection conn = null;
         try{
            conn = new config().akses();
         try{
             String report = ("C:\\Users\\fayat\\OneDrive\\Documents\\NetBeansProjects\\tokokecil\\src\\Nota\\newReport.jrxml");
             HashMap hash = new HashMap();
             hash.put("kd", txtNota.getText());
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
     
    public void autonumber() {
        try {
            String sql = "select * from transaksi order by no_nota desc";
            java.sql.Connection conn = new config().akses();
            Statement pst = conn.createStatement();
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()) {
                String no_nota = rs.getString("no_nota").substring(2);
                String NT = "" + (Integer.parseInt(no_nota) +1);
                String No1 = "";

                if(NT.length()== 1)
                {No1 = "000";}
                else if(NT.length()== 2)
                {No1 = "00";}
                else if(NT.length()== 3)
                {No1 = "0";}
                else if(NT.length()== 4)
                {No1 = "";}
                txtNota.setText("NT" + No1 + NT);
            } else {
                txtNota.setText("NT0001");
            }
//            txtNota.disable();
            rs.close();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    public void loadData() {
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        model.addRow(new Object[]{
            txtNota.getText(),
            txtId.getText(),
            txtNama.getText(),
            txtJml.getValue(),
            txtHarga.getText(),
            txtTotal.getText(),
        });
    }
    public void kosong() {
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
     public void  utama() {
        txtNota.setText("");
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtJml.setValue(0);
        autonumber();
    }
    public void clear() {
        txtNamaCust.setText("");
        txtBayar.setText("0");
        txtTotal.setText("0");
        txtKembalian.setText("0");
        txtKekurangan.setText("0");
        txtTelepon.setText("");
        txtStatus.setText("");
        jamAmbil.setSelectedItem("");
        tglAmbil.setDate(null);
    }
    public void clear2() {
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtJml.setValue(0);
    }
    public void tambahTransaksi() {
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(txtJml.getValue());
        harga = Integer.valueOf(txtHarga.getText());
        total = jumlah * harga;
        
        txtTotal.setText(String.valueOf(total));
        
        loadData();
        totalbiaya();
        clear2();
        txtId.requestFocus();
    }
   
    
    private void tableBarang(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");

        try{
            String sql = "SELECT id_barang, nama_barang, harga_jual FROM barang where keterangan = 'normal'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3)
                });
            }
            tableBarang.setModel(model);
        }catch(SQLException ex){
            
        }
     }
    

    public void kemasan(){
         DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Kemasan");
        model.addColumn("Muat");
        model.addColumn("Harga");

        try{
            String sql = "SELECT id_kemasan, jenis_kemasan, muat, harga FROM kemasan";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tableKemasan.setModel(model);
        }catch(SQLException ex){
            
        }
    }
    
    
    public transaksiFrame() {
        initComponents();
        txtTelepon.setDocument(new setTxtField(13)); //untuk ngebatasin isi dari TxtField
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, 1);
        tglAmbil.setMinSelectableDate(today.getTime());
        tableBarang();
        kemasan();
        DefaultTableModel model = new DefaultTableModel();
        
        tableTransaksi.setModel(model);
        model.addColumn("No Nota");
        model.addColumn("Id Barang");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah");
        model.addColumn("Harga Produk");
        model.addColumn("Total");
        utama();


//        txtNota.disable();
//        txtId.disable();
//        txtNama.disable();
//        txtHarga.disable();
        txtTotal.setText("0");
        txtBayar.setText("0");
        txtKembalian.setText("0");
        txtKekurangan.setText("0");
        txtJml.setMinimum(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        txtNota = new javax.swing.JTextField();
        pilihJenis = new combo_suggestion.ComboBoxSuggestion();
        jLabel2 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        txtHarga = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtNamaCust = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtKekurangan = new javax.swing.JTextField();
        txtJml = new com.toedter.components.JSpinField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        prosesbtn = new javax.swing.JPanel();
        tambah = new javax.swing.JLabel();
        batalbtn = new javax.swing.JPanel();
        batal = new javax.swing.JLabel();
        jamAmbil = new combo_suggestion.ComboBoxSuggestion();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tglAmbil = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cetakbtn = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableBarang = new table.tabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKemasan = new table.tabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksi = new table.tabel();
        jLabel35 = new javax.swing.JLabel();
        kembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNota.setBackground(new java.awt.Color(220, 193, 151));
        txtNota.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtNota.setToolTipText("");
        txtNota.setBorder(null);
        txtNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotaActionPerformed(evt);
            }
        });
        jPanel1.add(txtNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 90, 310, 40));

        pilihJenis.setBorder(null);
        pilihJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Manis", "Gurih" }));
        pilihJenis.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pilihJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihJenisActionPerformed(evt);
            }
        });
        jPanel1.add(pilihJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 270, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/combomid.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        txtCari.setBackground(new java.awt.Color(220, 193, 151));
        txtCari.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCari.setBorder(null);
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 65, 280, 40));

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kcik.png"))); // NOI18N
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(767, 69, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/searctrns.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/resetrns.png"))); // NOI18N
        reset.setBorder(null);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, -1, -1));

        txtHarga.setBackground(new java.awt.Color(220, 193, 151));
        txtHarga.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtHarga.setBorder(null);
        jPanel1.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 890, 170, 40));

        txtId.setBackground(new java.awt.Color(220, 193, 151));
        txtId.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtId.setBorder(null);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 890, 175, 40));

        txtNama.setBackground(new java.awt.Color(220, 193, 151));
        txtNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNama.setBorder(null);
        jPanel1.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 890, 180, 40));

        txtTelepon.setBackground(new java.awt.Color(220, 193, 151));
        txtTelepon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTelepon.setToolTipText("");
        txtTelepon.setBorder(null);
        jPanel1.add(txtTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 220, 310, 40));

        txtTotal.setBackground(new java.awt.Color(220, 193, 151));
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTotal.setToolTipText("");
        txtTotal.setBorder(null);
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 740, 250, 40));

        txtNamaCust.setBackground(new java.awt.Color(220, 193, 151));
        txtNamaCust.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtNamaCust.setToolTipText("");
        txtNamaCust.setBorder(null);
        jPanel1.add(txtNamaCust, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 155, 310, 40));

        txtBayar.setBackground(new java.awt.Color(220, 193, 151));
        txtBayar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtBayar.setToolTipText("");
        txtBayar.setBorder(null);
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        jPanel1.add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 810, 250, 40));

        txtKembalian.setBackground(new java.awt.Color(220, 193, 151));
        txtKembalian.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtKembalian.setToolTipText("");
        txtKembalian.setBorder(null);
        jPanel1.add(txtKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 880, 250, 40));

        txtStatus.setBackground(new java.awt.Color(220, 193, 151));
        txtStatus.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtStatus.setToolTipText("");
        txtStatus.setBorder(null);
        jPanel1.add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 740, 250, 40));

        txtKekurangan.setBackground(new java.awt.Color(220, 193, 151));
        txtKekurangan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtKekurangan.setToolTipText("");
        txtKekurangan.setBorder(null);
        jPanel1.add(txtKekurangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 950, 250, 40));

        txtJml.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(txtJml, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 890, 80, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldtrnsmid.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 890, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldtrnsmid.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 890, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldqty.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 890, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldtrnsmid.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 890, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Harga.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 860, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Barang.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 860, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Barang.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 860, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Qty.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 860, -1, -1));

        prosesbtn.setBackground(new java.awt.Color(71, 160, 40));
        prosesbtn.setPreferredSize(new java.awt.Dimension(123, 40));
        prosesbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prosesbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prosesbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prosesbtnMouseExited(evt);
            }
        });

        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PROSES.png"))); // NOI18N
        tambah.setToolTipText("");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout prosesbtnLayout = new javax.swing.GroupLayout(prosesbtn);
        prosesbtn.setLayout(prosesbtnLayout);
        prosesbtnLayout.setHorizontalGroup(
            prosesbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
            .addGroup(prosesbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(prosesbtnLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tambah)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        prosesbtnLayout.setVerticalGroup(
            prosesbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(prosesbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(prosesbtnLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tambah)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(prosesbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 970, -1, -1));

        batalbtn.setBackground(new java.awt.Color(160, 40, 40));
        batalbtn.setPreferredSize(new java.awt.Dimension(123, 40));
        batalbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batalbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                batalbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                batalbtnMouseExited(evt);
            }
        });

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BATAL.png"))); // NOI18N
        batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout batalbtnLayout = new javax.swing.GroupLayout(batalbtn);
        batalbtn.setLayout(batalbtnLayout);
        batalbtnLayout.setHorizontalGroup(
            batalbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
            .addGroup(batalbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(batalbtnLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(batal)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        batalbtnLayout.setVerticalGroup(
            batalbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(batalbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(batalbtnLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(batal)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(batalbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 970, -1, 40));

        jamAmbil.setBorder(null);
        jamAmbil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05.00-06.59", "07.00-09.59", "10.00-13.59", "14.00-16.00", " " }));
        jamAmbil.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(jamAmbil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1525, 190, 270, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pktbtn.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1437, 0, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldlong.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 90, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldlong.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 155, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldmid.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 950, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/jamcombo.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 185, -1, -1));

        tglAmbil.setBackground(new java.awt.Color(220, 193, 151));
        tglAmbil.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(tglAmbil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 120, 260, 40));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldmid.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 120, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldlong.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 220, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldmid.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 740, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldmid.png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 810, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldmid.png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 880, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/txtfldmid.png"))); // NOI18N
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 740, -1, -1));

        cetakbtn.setBackground(new java.awt.Color(112, 63, 25));
        cetakbtn.setPreferredSize(new java.awt.Dimension(205, 70));
        cetakbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cetakbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cetakbtnMouseExited(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/CETAK BUTTON.png"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cetakbtnLayout = new javax.swing.GroupLayout(cetakbtn);
        cetakbtn.setLayout(cetakbtnLayout);
        cetakbtnLayout.setHorizontalGroup(
            cetakbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
            .addGroup(cetakbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cetakbtnLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        cetakbtnLayout.setVerticalGroup(
            cetakbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(cetakbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cetakbtnLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(cetakbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 960, -1, 50));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Kekurangan.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 920, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Jam Pengambilan.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 163, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Customer.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 135, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No. Nota.png"))); // NOI18N
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 70, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Tgl. Pengambilan.png"))); // NOI18N
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 95, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Telepon.png"))); // NOI18N
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 200, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Status.png"))); // NOI18N
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 720, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bayar.png"))); // NOI18N
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 790, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Kembalian.png"))); // NOI18N
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 850, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Total.png"))); // NOI18N
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 720, -1, -1));

        jScrollPane4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tableBarang.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableBarang);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 760, -1));

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        tableKemasan.setModel(new javax.swing.table.DefaultTableModel(
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
        tableKemasan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tableKemasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKemasanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKemasan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 710, 220));

        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTransaksi.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jScrollPane2.setViewportView(tableTransaksi);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 290, 840, 360));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, -1, -1));

        kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backtransact.png"))); // NOI18N
        kembali.setBorder(null);
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        jPanel1.add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        setBounds(0, 0, 1920, 1080);
    }// </editor-fold>//GEN-END:initComponents

    private void pilihJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihJenisActionPerformed
        // TODO add your handling code here:
        String pilih =pilihJenis.getSelectedItem().toString();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        
        switch (pilih) {
            case "Semua" :
                try{
                    String sql = "SELECT id_barang, nama_barang, harga_jual FROM barang where keterangan = 'normal'";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3)
                        });
                    }
                    tableBarang.setModel(model);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
            case "Manis" :
                try{
                    String sql = "SELECT id_barang, nama_barang, harga_jual FROM barang where keterangan = 'normal' AND jenis_kue = '"+pilih+"'";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3)
                        });
                    }
                    tableBarang.setModel(model);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
            case "Gurih" :
                try{
                    String sql = "SELECT id_barang, nama_barang, harga_jual FROM barang where keterangan = 'normal' AND jenis_kue = '"+pilih+"'";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3)
                        });
                    }
                    tableBarang.setModel(model);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
        }
    }//GEN-LAST:event_pilihJenisActionPerformed

    private void batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        int row = tableTransaksi.getSelectedRow();
        
        String id_paket = tableTransaksi.getValueAt(row, 1).toString();
        String id = id_paket;
        
            String perbandingan = null;      
            
            try{
            String sql = "SELECT id_paket from paket where id_paket = '"+id+"'";       
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("id_paket");
                    }                                               
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            if(id.equals(perbandingan)) {
                String nota = txtNota.getText();

                try {
                    String sql = "delete from detail_paket_tr where no_nota = '"+nota+"'";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.executeUpdate();
                    pst.close();
                }catch(Exception e){
                        
                }
                model.removeRow(row);
                totalbiaya();
                txtBayar.setText("0");
                txtKembalian.setText("0");
            }else{
                model.removeRow(row);
                totalbiaya();
                txtBayar.setText("0");
                txtKembalian.setText("0");
            }
        
    }//GEN-LAST:event_batalMouseClicked

    private void batalbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalbtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_batalbtnMouseClicked

    private void batalbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalbtnMouseEntered
        // TODO add your handling code here:
        batalbtn.setBackground(new Color(102,0,0));
        batalbtn.setOpaque(true);
    }//GEN-LAST:event_batalbtnMouseEntered

    private void batalbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalbtnMouseExited
        // TODO add your handling code here:
        batalbtn.setBackground(new Color(160,40,40));
    }//GEN-LAST:event_batalbtnMouseExited

    private void prosesbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesbtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_prosesbtnMouseClicked

    private void prosesbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesbtnMouseEntered
        // TODO add your handling code here:
        prosesbtn.setBackground(new Color(22,85,0));
        prosesbtn.setOpaque(true);
    }//GEN-LAST:event_prosesbtnMouseEntered

    private void prosesbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesbtnMouseExited
        // TODO add your handling code here:
        prosesbtn.setBackground(new Color(71,160,40));
    }//GEN-LAST:event_prosesbtnMouseExited

    private void cetakbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakbtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cetakbtnMouseClicked

    private void cetakbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakbtnMouseEntered
        // TODO add your handling code here:
        cetakbtn.setBackground(new Color(177,113,62));
        cetakbtn.setOpaque(true);
    }//GEN-LAST:event_cetakbtnMouseEntered

    private void cetakbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakbtnMouseExited
        // TODO add your handling code here:
        cetakbtn.setBackground(new Color(112,63,25));
    }//GEN-LAST:event_cetakbtnMouseExited

    private void txtNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");

        try{
            String sql = "SELECT id_barang, nama_barang, harga_jual FROM barang where keterangan = 'normal' and nama_barang LIKE '%"+txtCari.getText()+"%'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3)
                });
            }
            tableBarang.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_cariActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
        int total, bayar, Kembalian, kekurangan;

        total = Integer.valueOf(txtTotal.getText());
        bayar = Integer.valueOf(txtBayar.getText());
        double dp = total * 0.4;

        if(total <= bayar) {
            Kembalian = bayar - total;
            txtKembalian.setText(String.valueOf(Kembalian));
            txtStatus.setText("Lunas");
            
        } else {
            if (bayar > dp) {
                kekurangan = total-bayar;
            txtKekurangan.setText(String.valueOf(kekurangan));
            txtStatus.setText("DP");
            }else{
                //JOptionPane.showMessageDialog(this, "DP harus lebih dari 40% dari harga total");
                failMessage me = new failMessage(this, true);  
            judulWarn.setText("Transaksi Gagal Diproses!");
            txtWarn.setText("Pembayaran harus lebih dari 50%");
            me.showAlert();
            }
            
        }
    }//GEN-LAST:event_txtBayarActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_tambahMouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        tableBarang();
    }//GEN-LAST:event_resetActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new paketFrame().setVisible(true);
//        this.setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        boolean cekSimpan = false;
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        String perbandingan = null;      //ini penyimpanan sementara untuk menyimpan id paket
        String id = txtNota.getText();
            
        try{
            String sql = "SELECT no_nota from transaksi where no_nota = '"+id+"'";        //jadi kalo id nya ditemukan maka akan tersimpan
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
                while(res.next()){
                    perbandingan = res.getString("no_nota");      //akan tersimpan disini tapi kalo id nya tidak ditemukan maka 
                    }                                               //variable tadi akan bernilai seperti didlarasi sebelumnya yaitu null
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            //if ini untuk menemukan apakah id itu bernilai null atau nilainya ditemukan di database kalo ditemukan maka akan bernilai true dan percabangan tidak dijalankan
            if(id.equals(perbandingan)) { // Memeriksa jika id tidak sama dengan variable perbandingan
                cekSimpan = true;
            }
             
        if (cekSimpan==true) {
            simpanUpdatePaket();
            simpanKemasan();
        }else{
            simpanInsertTransaksi();
            simpanKemasan();
        }
        cetakReport();
        clear(); 
        utama();
        autonumber();
        kosong();
    }//GEN-LAST:event_jLabel25MouseClicked

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
         int baris = tableBarang.rowAtPoint(evt.getPoint());
        
        String id_barang = tableBarang.getValueAt(baris, 0).toString();
        txtId.setText(id_barang);
        
        String nama_barang = tableBarang.getValueAt(baris, 1).toString();
        txtNama.setText(nama_barang);
        
        String harga_jual = tableBarang.getValueAt(baris, 2).toString();
        txtHarga.setText(harga_jual);
    }//GEN-LAST:event_tableBarangMouseClicked

    private void tableKemasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKemasanMouseClicked
        // TODO add your handling code here:
         int baris = tableKemasan.rowAtPoint(evt.getPoint());
        
        String id_kemasan = tableKemasan.getValueAt(baris, 0).toString();
        txtId.setText(id_kemasan);
        
        String jenis_kemasan = tableKemasan.getValueAt(baris, 1).toString();
        txtNama.setText(jenis_kemasan);
        
        String harga = tableKemasan.getValueAt(baris, 3).toString();
        txtHarga.setText(harga);
    }//GEN-LAST:event_tableKemasanMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        String akks = login.akses;
        String blk = pengambilanFrame.balik;
        if (blk != null) {
            this.dispose();
            pengambilanFrame.balik = null;
        }else{
            if(akks.equals("admin")){
            this.dispose();
            new homeAdmin().setVisible(true);
            
        }else if(akks.equals("owner")){
            this.dispose();
            new homeOwner().setVisible(true);
        }
        }
        
        
        
    }//GEN-LAST:event_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(transaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksiFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel batal;
    private javax.swing.JPanel batalbtn;
    private javax.swing.JButton cari;
    private javax.swing.JPanel cetakbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static combo_suggestion.ComboBoxSuggestion jamAmbil;
    private javax.swing.JButton kembali;
    private combo_suggestion.ComboBoxSuggestion pilihJenis;
    private javax.swing.JPanel prosesbtn;
    private javax.swing.JButton reset;
    private table.tabel tableBarang;
    private table.tabel tableKemasan;
    public static table.tabel tableTransaksi;
    private javax.swing.JLabel tambah;
    public static com.toedter.calendar.JDateChooser tglAmbil;
    public static javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtId;
    private com.toedter.components.JSpinField txtJml;
    public static javax.swing.JTextField txtKekurangan;
    public static javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtNama;
    public static javax.swing.JTextField txtNamaCust;
    public static javax.swing.JTextField txtNota;
    public static javax.swing.JTextField txtStatus;
    public static javax.swing.JTextField txtTelepon;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
