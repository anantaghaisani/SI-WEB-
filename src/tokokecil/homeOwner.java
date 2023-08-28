
package tokokecil;


import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.Statement;
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
public class homeOwner extends javax.swing.JFrame {
 
    /**
     * Creates new form homeOwner
     */
    public homeOwner() {
        initComponents();
        table(); //admin
        table1(); //barang
        table2(); //paket
        table3(); // supplier
        table4(); //kemasan
        cmBoxKemasan();
        mainKonten();
        setCmSup();
        setCmBrng();
        tbDtSup();
    }
    public void masterAdmPanel(){
        initComponents();
        table();
    }
    private void reset(){
    usernameTxt.setText(null);
    txtRf_id.setText(null);
    txtAlamat.setText(null);
    txtTelepon.setText(null);
    passwordAdmTxt.setText(null);
    hak.setSelectedItem(null);
    pertanyaanTxt.setText(null);
    jawabanTxt.setText(null);
    
}
    private void reset1(){
    txtId.setText(null);
    txtNama.setText(null);
    txtJual.setText(null);
    txtBeli.setText(null);
    txtKet.setSelectedIndex(0);
    comboBoxJenis.setSelectedItem(null);
}
    private void reset2(){
    txtIdpaket.setText(null);
    txtNamaPaket.setText(null);
    txtHargapaket.setText(null);
    txtJumlahpaket.setText(null);
    txtMacampaket.setText(null);
    txtJumlahpaket.setText(null);
    cmKemasan.setSelectedItem(null);
}
    private void reset3(){
    txtIdsup.setText(null);
    txtNamasup.setText(null);
    txtAlamatsup.setText(null);
    txtTeleponsup.setText(null);
}
     private void reset4(){
    txtIdkem.setText(null);
    txtJeniskem.setText(null);
    txtMuat.setText(null);
    txtHargakem.setText(null);
}
    
    private void table(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Username");
        model.addColumn("rf_id");
        model.addColumn("alamat");
        model.addColumn("telepon");
        model.addColumn("Password");
        model.addColumn("Hak");
        model.addColumn("Pertanyaan");
        model.addColumn("Jawaban");
        
        try{
            int no=1;
            String sql = "SELECT * FROM login";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),
                    res.getString(9),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6),res.getString(7),res.getString(8)
                });
            }
            tableAdmin.setModel(model);
        }catch(SQLException ex){
            
        }
    }
    private void table1(){
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("No");
        model1.addColumn("ID Barang");
        model1.addColumn("Nama Barang");
        model1.addColumn("Harga Jual");
        model1.addColumn("Harga Beli");
        model1.addColumn("Keterangan");
        model1.addColumn("Jenis");
        
        
        try{
            int no=1;
            String sql = "SELECT * FROM barang";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model1.addRow(new Object[]{no++,res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6)
                });
            }
            tableBarang.setModel(model1);
        }catch(SQLException ex){
            
        }
        
    }
    private void table2(){
         DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("No. ");
        model2.addColumn("ID Paket");
        model2.addColumn("Nama Paket");
        model2.addColumn("Harga");
        model2.addColumn("Jumlah Kue");
        model2.addColumn("Macam Kue");
        model2.addColumn("ID Kemasan");
        
        try{
            int no=1;
            String sql = "SELECT * FROM paket";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model2.addRow(new Object[]{no++,res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6) 
                });
            }
            tablePaket.setModel(model2);
        }catch(SQLException ex){
            
        }
    }
    private void table3(){
        DefaultTableModel model3 = new DefaultTableModel();
        model3.addColumn("No. ");
        model3.addColumn("ID Supplier");
        model3.addColumn("Nama Supplier");
        model3.addColumn("Alamat");
        model3.addColumn("Telepon");
        
        try{
            int no=1;
            String sql = "SELECT * FROM suplier";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model3.addRow(new Object[]{no++,res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tableSupplier.setModel(model3);
        }catch(SQLException ex){
            
        }
    }
    private void table4(){
        DefaultTableModel model4 = new DefaultTableModel();
        model4.addColumn("No. ");
        model4.addColumn("ID Kemasan");
        model4.addColumn("Nama Kemasan");
        model4.addColumn("Kapasitas");
        model4.addColumn("Harga");
        
        try{
            int no=1;
            String sql = "SELECT * FROM kemasan";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model4.addRow(new Object[]{no++,res.getString(1),
                    res.getString(2),res.getString(3), res.getString(4)
                });
            }
            tableKemasan.setModel(model4);
        }catch(SQLException ex){
            
        }

    }
   
    public void cmBoxKemasan(){
        try{
            String sql = "SELECT id_kemasan, jenis_kemasan from kemasan ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmKemasan.addItem(res.getString("id_kemasan")+"-"+res.getString("jenis_kemasan"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void mainKonten(){
        try{
            String sql = "SELECT COALESCE(COUNT(transaksi.no_nota),0) AS selesai FROM transaksi JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota "
                    + "WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE (transaksi.tgl_transaksi) = CURDATE() ";
            String sql1 = "SELECT COALESCE(COUNT(transaksi.no_nota),0) AS berlangsung FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + "WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() ";
            String sql2 = "SELECT COALESCE(COUNT(transaksi.no_nota),0) AS mendatang FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + "WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE() + INTERVAL 1 DAY ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
            java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
            java.sql.ResultSet res2 = pst2.executeQuery(sql2);
            while(res.next()){
                txtSelesai.setText(res.getString("selesai"));
                }
            while(res1.next()){
                txtBerlangsung.setText(res1.getString("berlangsung"));
                }
            while(res2.next()){
                txtMendatang.setText(res2.getString("mendatang"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        DefaultTableModel modelT = new DefaultTableModel();
        modelT.addColumn("No Nota");
        modelT.addColumn("Customer");
        modelT.addColumn("Tanggal Ambil");
        modelT.addColumn("Jam Ambil");
        modelT.addColumn("Status Pembayaran");
        
        try{
            String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.tanggal_pengambilan, pengambilan.jam, transaksi.statusTransaksi FROM transaksi "
                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota WHERE DATE (transaksi.tgl_transaksi) = CURDATE() ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                modelT.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3), res.getString(4)
                        , res.getString(5)
                });
            }
            tbRiwayatTr.setModel(modelT);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        DefaultTableModel modelL = new DefaultTableModel();
        modelL.addColumn("Nama");
        modelL.addColumn("Waktu Login");
        try{
            String sql = "SELECT admin, jam FROM history_login WHERE date(jam) = CURDATE() ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                modelL.addRow(new Object[]{res.getString(1),
                    res.getString(2)
                });
            }
            tbRiwayatLog.setModel(modelL);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
     public void autonumberPkt() {
        try {
            String sql = "select * from paket order by id_paket desc";
            java.sql.Connection conn = new config().akses();
            Statement pst = conn.createStatement();
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()) {
                String no_nota = rs.getString("id_paket").substring(2);
                String NT = "" + (Integer.parseInt(no_nota) +1);
                String No1 = "";

                if(NT.length()== 1)
                {No1 = "00";}
                else if(NT.length()== 2)
                {No1 = "0";}
                else if(NT.length()== 3)
                {No1 = "";}
                txtIdpaket.setText("p" + No1 + NT);
            } else {
                txtIdpaket.setText("p001");
            }
//            txtNota.disable();
            rs.close();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
     public void autonumberBrng() {
        try {
            String sql = "select * from barang order by id_barang desc";
            java.sql.Connection conn = new config().akses();
            Statement pst = conn.createStatement();
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()) {
                String no_nota = rs.getString("id_barang").substring(2);
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
                txtId.setText("a" + No1 + NT);
            } else {
                txtId.setText("a0001");
            }
//            txtNota.disable();
            rs.close();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
     public void autonumberSup() {
        try {
            String sql = "select * from suplier order by id_suplier desc";
            java.sql.Connection conn = new config().akses();
            Statement pst = conn.createStatement();
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()) {
                String no_nota = rs.getString("id_suplier").substring(2);
                String NT = "" + (Integer.parseInt(no_nota) +1);
                String No1 = "";

                if(NT.length()== 1)
                {No1 = "00";}
                else if(NT.length()== 2)
                {No1 = "0";}
                else if(NT.length()== 3)
                {No1 = "";}
                txtIdsup.setText("sp" + No1 + NT);
            } else {
                txtIdsup.setText("sp001");
            }
//            txtNota.disable();
            rs.close();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
     
     public void setCmSup(){
         try{
            String sql = "SELECT suplier from suplier ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmSup.addItem(res.getString("suplier"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
     }
     public void setCmBrng(){
         try{
            String sql = "SELECT nama_barang from barang ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmBrng.addItem(res.getString("nama_barang"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
     }
     public void tbDtSup(){
         DefaultTableModel modelT = new DefaultTableModel();
        modelT.addColumn("Suplier");
        modelT.addColumn("Barang");
        modelT.addColumn("Kapasitas");
        
        try{
            String sql = "SELECT suplier.suplier, barang.nama_barang, detail_suplier.stok_barang FROM detail_suplier "
                    + "JOIN suplier ON detail_suplier.id_suplier = suplier.id_suplier "
                    + "JOIN barang ON barang.id_barang = detail_suplier.id_barang ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                modelT.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3)
                });
            }
            tbDt.setModel(modelT);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyy = new javax.swing.JPanel();
        menuBarr = new javax.swing.JPanel();
        setoranSpl = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        master = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        transact = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        laporanPenj = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pengambilan = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbRiwayatLog = new table.tabel();
        txtBerlangsung = new javax.swing.JLabel();
        txtSelesai = new javax.swing.JLabel();
        txtMendatang = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbRiwayatTr = new table.tabel();
        masterPktPanel = new javax.swing.JPanel();
        PktTitle = new javax.swing.JLabel();
        searchBtnPkt = new javax.swing.JButton();
        txtSearchPkt = new javax.swing.JTextField();
        txtJumlahpaket = new javax.swing.JTextField();
        txtHargapaket = new javax.swing.JTextField();
        txtIdpaket = new javax.swing.JTextField();
        txtMacampaket = new javax.swing.JTextField();
        txtNamaPaket = new javax.swing.JTextField();
        cmKemasan = new combo_suggestion.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        hapusBtnPkt = new javax.swing.JButton();
        resetBtnPkt = new javax.swing.JButton();
        editBtnPkt = new javax.swing.JButton();
        tambahBtnPkt = new javax.swing.JButton();
        masterSupplierPkt = new javax.swing.JLabel();
        masterKemasanPkt = new javax.swing.JLabel();
        masterBarangPkt = new javax.swing.JLabel();
        masterAdminPkt = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablePaket = new table.tabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        masterAdmPanel = new javax.swing.JPanel();
        AdmTitle = new javax.swing.JLabel();
        usernameTxt = new javax.swing.JTextField();
        txtRf_id = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        passwordAdmTxt = new javax.swing.JTextField();
        pertanyaanTxt = new javax.swing.JTextField();
        jawabanTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hak = new combo_suggestion.ComboBoxSuggestion();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        editButtonAdm = new javax.swing.JButton();
        hapusButtonAdm = new javax.swing.JButton();
        tambahButtonAdm = new javax.swing.JButton();
        resetButtonAdm = new javax.swing.JButton();
        masterBarang = new javax.swing.JLabel();
        masterPaket = new javax.swing.JLabel();
        masterSupplier = new javax.swing.JLabel();
        masterKemasan = new javax.swing.JLabel();
        txtSearchAdm = new javax.swing.JTextField();
        searchBtnAdm = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAdmin = new table.tabel();
        border = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        masterBrgPanel = new javax.swing.JPanel();
        brgTitle = new javax.swing.JLabel();
        txtKet = new combo_suggestion.ComboBoxSuggestion();
        txtNama = new javax.swing.JTextField();
        txtBeli = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        comboBoxJenis = new combo_suggestion.ComboBoxSuggestion();
        txtJual = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        hapusButtonBrg = new javax.swing.JButton();
        editButtonBrg = new javax.swing.JButton();
        resetButtonBrg = new javax.swing.JButton();
        tambahButtonBrg = new javax.swing.JButton();
        masterAdminBrg = new javax.swing.JLabel();
        masterSupplierbrg = new javax.swing.JLabel();
        masterPaketBrg = new javax.swing.JLabel();
        masterKemasanBrg = new javax.swing.JLabel();
        txtSearchBrg = new javax.swing.JTextField();
        searchBtnBrg = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new table.tabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        addIdBrng = new javax.swing.JButton();
        masterSplPanel = new javax.swing.JPanel();
        txtTeleponsup = new javax.swing.JTextField();
        txtIdsup = new javax.swing.JTextField();
        txtNamasup = new javax.swing.JTextField();
        txtAlamatsup = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        hapusBtnSpl = new javax.swing.JButton();
        resetBtnSpl = new javax.swing.JButton();
        editBtnSpl = new javax.swing.JButton();
        tambahBtnSpl = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        masterPaketSpl = new javax.swing.JLabel();
        masterAdminSpl = new javax.swing.JLabel();
        masterBarangSpl = new javax.swing.JLabel();
        masterKemasanSpl = new javax.swing.JLabel();
        searchBtnSpl = new javax.swing.JButton();
        txtSearchSpl = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtKapasitas = new com.toedter.components.JSpinField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSupplier = new table.tabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbDt = new table.tabel();
        addDetailSup = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        addIdSup = new javax.swing.JButton();
        cmSup = new combo_suggestion.ComboBoxSuggestion();
        cmBrng = new combo_suggestion.ComboBoxSuggestion();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        masterKmsPanel = new javax.swing.JPanel();
        txtHargakem = new javax.swing.JTextField();
        txtIdkem = new javax.swing.JTextField();
        txtJeniskem = new javax.swing.JTextField();
        txtMuat = new javax.swing.JTextField();
        kmsTitle = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        hapusBtnKms = new javax.swing.JButton();
        resetBtnKms = new javax.swing.JButton();
        editBtnKms = new javax.swing.JButton();
        tambahBtnKms = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        masterSupplierKms = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtSearchKms = new javax.swing.JTextField();
        searchBtnKms = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableKemasan = new table.tabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bodyy.setBackground(new java.awt.Color(237, 224, 209));

        menuBarr.setBackground(new java.awt.Color(57, 72, 12));
        menuBarr.setPreferredSize(new java.awt.Dimension(470, 1080));
        menuBarr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setoranSpl.setBackground(new java.awt.Color(234, 204, 154));
        setoranSpl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setoranSpl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setoranSplMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setoranSplMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setoranSplMouseExited(evt);
            }
        });

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/SETORAN SPL.png"))); // NOI18N

        javax.swing.GroupLayout setoranSplLayout = new javax.swing.GroupLayout(setoranSpl);
        setoranSpl.setLayout(setoranSplLayout);
        setoranSplLayout.setHorizontalGroup(
            setoranSplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(setoranSplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(setoranSplLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        setoranSplLayout.setVerticalGroup(
            setoranSplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
            .addGroup(setoranSplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(setoranSplLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel51)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        menuBarr.add(setoranSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 780, 430, 75));

        master.setBackground(new java.awt.Color(234, 204, 154));
        master.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        master.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                masterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                masterMouseExited(evt);
            }
        });

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MASTER.png"))); // NOI18N

        javax.swing.GroupLayout masterLayout = new javax.swing.GroupLayout(master);
        master.setLayout(masterLayout);
        masterLayout.setHorizontalGroup(
            masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(masterLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        masterLayout.setVerticalGroup(
            masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
            .addGroup(masterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(masterLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        menuBarr.add(master, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 430, 75));

        transact.setBackground(new java.awt.Color(234, 204, 154));
        transact.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        transact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transactMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                transactMouseExited(evt);
            }
        });

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TRANSAKSI.png"))); // NOI18N

        javax.swing.GroupLayout transactLayout = new javax.swing.GroupLayout(transact);
        transact.setLayout(transactLayout);
        transactLayout.setHorizontalGroup(
            transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(transactLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        transactLayout.setVerticalGroup(
            transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
            .addGroup(transactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(transactLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel49)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        menuBarr.add(transact, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 430, 75));

        laporanPenj.setBackground(new java.awt.Color(234, 204, 154));
        laporanPenj.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        laporanPenj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanPenjMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laporanPenjMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laporanPenjMouseExited(evt);
            }
        });

        jLabel39.setBackground(new java.awt.Color(234, 204, 154));
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LAPORAN PENJ.png"))); // NOI18N

        javax.swing.GroupLayout laporanPenjLayout = new javax.swing.GroupLayout(laporanPenj);
        laporanPenj.setLayout(laporanPenjLayout);
        laporanPenjLayout.setHorizontalGroup(
            laporanPenjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(laporanPenjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, laporanPenjLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        laporanPenjLayout.setVerticalGroup(
            laporanPenjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
            .addGroup(laporanPenjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
        );

        menuBarr.add(laporanPenj, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 430, 75));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Group 47 (1).png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        menuBarr.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 930, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Rectangle 43.png"))); // NOI18N
        menuBarr.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Ellipse 2.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        menuBarr.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 63, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Rectangle 42.png"))); // NOI18N
        menuBarr.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 508, -1, -1));

        pengambilan.setBackground(new java.awt.Color(234, 204, 154));
        pengambilan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pengambilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pengambilanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pengambilanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pengambilanMouseExited(evt);
            }
        });

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PENGAMBILAN.png"))); // NOI18N

        javax.swing.GroupLayout pengambilanLayout = new javax.swing.GroupLayout(pengambilan);
        pengambilan.setLayout(pengambilanLayout);
        pengambilanLayout.setHorizontalGroup(
            pengambilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(pengambilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pengambilanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pengambilanLayout.setVerticalGroup(
            pengambilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
            .addGroup(pengambilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pengambilanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel50)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        menuBarr.add(pengambilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 430, 75));

        content.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(237, 224, 209));
        home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbRiwayatLog.setModel(new javax.swing.table.DefaultTableModel(
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
        tbRiwayatLog.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jScrollPane9.setViewportView(tbRiwayatLog);

        home.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 550, 410, -1));

        txtBerlangsung.setBackground(new java.awt.Color(234, 204, 154));
        txtBerlangsung.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        txtBerlangsung.setForeground(new java.awt.Color(57, 72, 12));
        txtBerlangsung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBerlangsung.setOpaque(true);
        home.add(txtBerlangsung, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 240, 120));

        txtSelesai.setBackground(new java.awt.Color(234, 204, 154));
        txtSelesai.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        txtSelesai.setForeground(new java.awt.Color(57, 72, 12));
        txtSelesai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSelesai.setOpaque(true);
        home.add(txtSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 240, 120));

        txtMendatang.setBackground(new java.awt.Color(234, 204, 154));
        txtMendatang.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        txtMendatang.setForeground(new java.awt.Color(57, 72, 12));
        txtMendatang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMendatang.setOpaque(true);
        txtMendatang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMendatangMouseClicked(evt);
            }
        });
        home.add(txtMendatang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 230, 240, 120));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RIWAYAT LOGIN.png"))); // NOI18N
        home.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 490, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PESANAN HARI INI.png"))); // NOI18N
        home.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/selesaiHomeAdm.png"))); // NOI18N
        home.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/berlangsungHomeAdm.png"))); // NOI18N
        home.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mendatangHomeAdm.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        home.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RIWAYAT TRANSAKSI.png"))); // NOI18N
        home.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        home.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        tbRiwayatTr.setModel(new javax.swing.table.DefaultTableModel(
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
        tbRiwayatTr.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jScrollPane8.setViewportView(tbRiwayatTr);

        home.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 830, -1));

        content.add(home, "card7");

        masterPktPanel.setBackground(new java.awt.Color(237, 224, 209));
        masterPktPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PktTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MASTER PAKET.png"))); // NOI18N
        masterPktPanel.add(PktTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        searchBtnPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kecil.png"))); // NOI18N
        searchBtnPkt.setBorder(null);
        searchBtnPkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnPktActionPerformed(evt);
            }
        });
        masterPktPanel.add(searchBtnPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 130, -1, -1));

        txtSearchPkt.setBackground(new java.awt.Color(220, 193, 151));
        txtSearchPkt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSearchPkt.setBorder(null);
        masterPktPanel.add(txtSearchPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 127, 270, 30));

        txtJumlahpaket.setBackground(new java.awt.Color(203, 161, 93));
        txtJumlahpaket.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtJumlahpaket.setBorder(null);
        masterPktPanel.add(txtJumlahpaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 530, 560, 50));

        txtHargapaket.setBackground(new java.awt.Color(203, 161, 93));
        txtHargapaket.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtHargapaket.setBorder(null);
        masterPktPanel.add(txtHargapaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 710, 560, 50));

        txtIdpaket.setBackground(new java.awt.Color(203, 161, 93));
        txtIdpaket.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtIdpaket.setBorder(null);
        masterPktPanel.add(txtIdpaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 560, 50));

        txtMacampaket.setBackground(new java.awt.Color(203, 161, 93));
        txtMacampaket.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMacampaket.setBorder(null);
        masterPktPanel.add(txtMacampaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 620, 560, 50));

        txtNamaPaket.setBackground(new java.awt.Color(203, 161, 93));
        txtNamaPaket.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNamaPaket.setBorder(null);
        masterPktPanel.add(txtNamaPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 620, 560, 50));

        cmKemasan.setBorder(null);
        cmKemasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Pilih---" }));
        cmKemasan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        masterPktPanel.add(cmKemasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 720, 540, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterPktPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 620, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterPktPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterPktPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterPktPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterPktPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxmstr.png"))); // NOI18N
        masterPktPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 710, -1, -1));

        hapusBtnPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON HAPUS.png"))); // NOI18N
        hapusBtnPkt.setBorder(null);
        hapusBtnPkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnPktActionPerformed(evt);
            }
        });
        masterPktPanel.add(hapusBtnPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 860, -1, -1));

        resetBtnPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON RESET.png"))); // NOI18N
        resetBtnPkt.setBorder(null);
        resetBtnPkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnPktActionPerformed(evt);
            }
        });
        masterPktPanel.add(resetBtnPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 860, -1, -1));

        editBtnPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON EDIT.png"))); // NOI18N
        editBtnPkt.setBorder(null);
        editBtnPkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnPktActionPerformed(evt);
            }
        });
        masterPktPanel.add(editBtnPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 860, -1, -1));

        tambahBtnPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON TAMBAH.png"))); // NOI18N
        tambahBtnPkt.setBorder(null);
        tambahBtnPkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBtnPktActionPerformed(evt);
            }
        });
        masterPktPanel.add(tambahBtnPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 860, -1, -1));

        masterSupplierPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON SUPPLIER.png"))); // NOI18N
        masterSupplierPkt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterSupplierPktMouseClicked(evt);
            }
        });
        masterPktPanel.add(masterSupplierPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 950, -1, -1));

        masterKemasanPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON KEMASAN.png"))); // NOI18N
        masterKemasanPkt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterKemasanPktMouseClicked(evt);
            }
        });
        masterPktPanel.add(masterKemasanPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 950, -1, -1));

        masterBarangPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON BARANG.png"))); // NOI18N
        masterBarangPkt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterBarangPktMouseClicked(evt);
            }
        });
        masterPktPanel.add(masterBarangPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 950, -1, -1));

        masterAdminPkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON ADMIN.png"))); // NOI18N
        masterAdminPkt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterAdminPktMouseClicked(evt);
            }
        });
        masterPktPanel.add(masterAdminPkt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 950, -1, -1));

        tablePaket.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePaket.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tablePaket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePaketMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablePaket);

        masterPktPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 1250, 270));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/textfieldmstr.png"))); // NOI18N
        masterPktPanel.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        masterPktPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Macam Kue.png"))); // NOI18N
        masterPktPanel.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 590, -1, -1));

        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Kemasan.png"))); // NOI18N
        masterPktPanel.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 690, -1, -1));

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Harga.png"))); // NOI18N
        masterPktPanel.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 680, -1, -1));

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Paket.png"))); // NOI18N
        masterPktPanel.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 590, -1, -1));

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Paket.png"))); // NOI18N
        masterPktPanel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 505, -1, -1));

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Isi Paket.png"))); // NOI18N
        masterPktPanel.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, -1, -1));

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/autonumber.png"))); // NOI18N
        add.setToolTipText("");
        add.setBorder(null);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        masterPktPanel.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 40, 40));

        content.add(masterPktPanel, "card3");

        masterAdmPanel.setBackground(new java.awt.Color(237, 224, 209));
        masterAdmPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdmTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MASTER ADMIN.png"))); // NOI18N
        masterAdmPanel.add(AdmTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        usernameTxt.setBackground(new java.awt.Color(203, 161, 93));
        usernameTxt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        usernameTxt.setBorder(null);
        usernameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTxtActionPerformed(evt);
            }
        });
        masterAdmPanel.add(usernameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 550, 50));

        txtRf_id.setBackground(new java.awt.Color(203, 161, 93));
        txtRf_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtRf_id.setBorder(null);
        txtRf_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRf_idActionPerformed(evt);
            }
        });
        masterAdmPanel.add(txtRf_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 560, 50));

        txtAlamat.setBackground(new java.awt.Color(203, 161, 93));
        txtAlamat.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtAlamat.setBorder(null);
        txtAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatActionPerformed(evt);
            }
        });
        masterAdmPanel.add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 670, 560, 50));

        txtTelepon.setBackground(new java.awt.Color(203, 161, 93));
        txtTelepon.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTelepon.setBorder(null);
        txtTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeleponActionPerformed(evt);
            }
        });
        masterAdmPanel.add(txtTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 760, 560, 50));

        passwordAdmTxt.setBackground(new java.awt.Color(203, 161, 93));
        passwordAdmTxt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        passwordAdmTxt.setBorder(null);
        masterAdmPanel.add(passwordAdmTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 560, 50));

        pertanyaanTxt.setBackground(new java.awt.Color(203, 161, 93));
        pertanyaanTxt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        pertanyaanTxt.setBorder(null);
        masterAdmPanel.add(pertanyaanTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 670, 560, 50));

        jawabanTxt.setBackground(new java.awt.Color(203, 161, 93));
        jawabanTxt.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jawabanTxt.setBorder(null);
        masterAdmPanel.add(jawabanTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 760, 560, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, -1, -1));

        hak.setBorder(null);
        hak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Pilih---", "admin", "owner" }));
        hak.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        masterAdmPanel.add(hak, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 595, 560, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 760, -1, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxmstr.png"))); // NOI18N
        masterAdmPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 580, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 670, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterAdmPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 760, -1, -1));

        editButtonAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON EDIT.png"))); // NOI18N
        editButtonAdm.setBorder(null);
        editButtonAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonAdmActionPerformed(evt);
            }
        });
        masterAdmPanel.add(editButtonAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 860, -1, -1));

        hapusButtonAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON HAPUS.png"))); // NOI18N
        hapusButtonAdm.setBorder(null);
        hapusButtonAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonAdmActionPerformed(evt);
            }
        });
        masterAdmPanel.add(hapusButtonAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 860, -1, -1));

        tambahButtonAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON TAMBAH.png"))); // NOI18N
        tambahButtonAdm.setBorder(null);
        tambahButtonAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonAdmActionPerformed(evt);
            }
        });
        masterAdmPanel.add(tambahButtonAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 860, -1, -1));

        resetButtonAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON RESET.png"))); // NOI18N
        resetButtonAdm.setToolTipText("");
        resetButtonAdm.setBorder(null);
        resetButtonAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonAdmActionPerformed(evt);
            }
        });
        masterAdmPanel.add(resetButtonAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 860, -1, -1));

        masterBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON BARANG.png"))); // NOI18N
        masterBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterBarangMouseClicked(evt);
            }
        });
        masterAdmPanel.add(masterBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 950, -1, -1));

        masterPaket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON PAKET.png"))); // NOI18N
        masterPaket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterPaketMouseClicked(evt);
            }
        });
        masterAdmPanel.add(masterPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 950, -1, -1));

        masterSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON SUPPLIER.png"))); // NOI18N
        masterSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterSupplierMouseClicked(evt);
            }
        });
        masterAdmPanel.add(masterSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 950, -1, -1));

        masterKemasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON KEMASAN.png"))); // NOI18N
        masterKemasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterKemasanMouseClicked(evt);
            }
        });
        masterAdmPanel.add(masterKemasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 950, -1, -1));

        txtSearchAdm.setBackground(new java.awt.Color(220, 193, 151));
        txtSearchAdm.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSearchAdm.setBorder(null);
        txtSearchAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchAdmActionPerformed(evt);
            }
        });
        masterAdmPanel.add(txtSearchAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 117, 270, 30));

        searchBtnAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kecil.png"))); // NOI18N
        searchBtnAdm.setBorder(null);
        searchBtnAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnAdmActionPerformed(evt);
            }
        });
        masterAdmPanel.add(searchBtnAdm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 120, -1, -1));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/textfieldmstr.png"))); // NOI18N
        masterAdmPanel.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, -1, -1));

        jScrollPane3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        tableAdmin.setModel(new javax.swing.table.DefaultTableModel(
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
        tableAdmin.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tableAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAdminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableAdmin);

        masterAdmPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 1300, 260));

        border.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        masterAdmPanel.add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Alamat.png"))); // NOI18N
        masterAdmPanel.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 645, -1, -1));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Username.png"))); // NOI18N
        masterAdmPanel.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 465, -1, -1));

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RFID.png"))); // NOI18N
        masterAdmPanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, -1));

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Telepon.png"))); // NOI18N
        masterAdmPanel.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, -1, -1));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Hak.png"))); // NOI18N
        masterAdmPanel.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, -1, -1));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Password.png"))); // NOI18N
        masterAdmPanel.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 465, -1, -1));

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pertanyaan.png"))); // NOI18N
        masterAdmPanel.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 645, -1, -1));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Jawaban.png"))); // NOI18N
        masterAdmPanel.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 730, -1, -1));

        content.add(masterAdmPanel, "card2");

        masterBrgPanel.setBackground(new java.awt.Color(237, 224, 209));
        masterBrgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        brgTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MASTER BARANG.png"))); // NOI18N
        brgTitle.setPreferredSize(new java.awt.Dimension(284, 30));
        masterBrgPanel.add(brgTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 341, -1));

        txtKet.setBorder(null);
        txtKet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Pilih---", "Manis", "Gurih" }));
        txtKet.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        masterBrgPanel.add(txtKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 750, 540, 30));

        txtNama.setBackground(new java.awt.Color(203, 161, 93));
        txtNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNama.setBorder(null);
        masterBrgPanel.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 640, 550, 50));

        txtBeli.setBackground(new java.awt.Color(203, 161, 93));
        txtBeli.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtBeli.setBorder(null);
        masterBrgPanel.add(txtBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 550, 50));

        txtId.setBackground(new java.awt.Color(203, 161, 93));
        txtId.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtId.setBorder(null);
        masterBrgPanel.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, 550, 50));

        comboBoxJenis.setBorder(null);
        comboBoxJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Pilih---", "normal", "paket", " ", " " }));
        comboBoxJenis.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        masterBrgPanel.add(comboBoxJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 653, 540, 20));

        txtJual.setBackground(new java.awt.Color(203, 161, 93));
        txtJual.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtJual.setBorder(null);
        masterBrgPanel.add(txtJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 740, 550, 50));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterBrgPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterBrgPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterBrgPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 740, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterBrgPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxmstr.png"))); // NOI18N
        masterBrgPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 640, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxmstr.png"))); // NOI18N
        masterBrgPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 740, -1, -1));

        hapusButtonBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON HAPUS.png"))); // NOI18N
        hapusButtonBrg.setBorder(null);
        hapusButtonBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonBrgActionPerformed(evt);
            }
        });
        masterBrgPanel.add(hapusButtonBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 860, -1, -1));

        editButtonBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON EDIT.png"))); // NOI18N
        editButtonBrg.setBorder(null);
        editButtonBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonBrgActionPerformed(evt);
            }
        });
        masterBrgPanel.add(editButtonBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 860, -1, -1));

        resetButtonBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON RESET.png"))); // NOI18N
        resetButtonBrg.setBorder(null);
        resetButtonBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonBrgActionPerformed(evt);
            }
        });
        masterBrgPanel.add(resetButtonBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 860, -1, -1));

        tambahButtonBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON TAMBAH.png"))); // NOI18N
        tambahButtonBrg.setBorder(null);
        tambahButtonBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonBrgActionPerformed(evt);
            }
        });
        masterBrgPanel.add(tambahButtonBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 860, -1, -1));

        masterAdminBrg.setIcon(new javax.swing.ImageIcon("C:\\Users\\fayat\\Downloads\\warna lain (6)\\BUTTON ADMIN.png")); // NOI18N
        masterAdminBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterAdminBrgMouseClicked(evt);
            }
        });
        masterBrgPanel.add(masterAdminBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 950, -1, -1));

        masterSupplierbrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON SUPPLIER.png"))); // NOI18N
        masterSupplierbrg.setPreferredSize(new java.awt.Dimension(339, 79));
        masterSupplierbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterSupplierbrgMouseClicked(evt);
            }
        });
        masterBrgPanel.add(masterSupplierbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 950, -1, -1));

        masterPaketBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON PAKET.png"))); // NOI18N
        masterPaketBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterPaketBrgMouseClicked(evt);
            }
        });
        masterBrgPanel.add(masterPaketBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 950, -1, -1));

        masterKemasanBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON KEMASAN.png"))); // NOI18N
        masterKemasanBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterKemasanBrgMouseClicked(evt);
            }
        });
        masterBrgPanel.add(masterKemasanBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 945, -1, 80));

        txtSearchBrg.setBackground(new java.awt.Color(220, 193, 151));
        txtSearchBrg.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSearchBrg.setBorder(null);
        txtSearchBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBrgActionPerformed(evt);
            }
        });
        masterBrgPanel.add(txtSearchBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 115, 270, 30));

        searchBtnBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kecil.png"))); // NOI18N
        searchBtnBrg.setBorder(null);
        searchBtnBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnBrgActionPerformed(evt);
            }
        });
        masterBrgPanel.add(searchBtnBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 120, -1, -1));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/textfieldmstr.png"))); // NOI18N
        masterBrgPanel.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 110, -1, -1));

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

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
        jScrollPane1.setViewportView(tableBarang);

        masterBrgPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 1290, 300));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        masterBrgPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Jenis Kue.png"))); // NOI18N
        masterBrgPanel.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 710, -1, -1));

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Barang Label.png"))); // NOI18N
        masterBrgPanel.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Barang.png"))); // NOI18N
        masterBrgPanel.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, -1, -1));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Harga Jual.png"))); // NOI18N
        masterBrgPanel.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, -1, -1));

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Harga Beli.png"))); // NOI18N
        masterBrgPanel.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 510, -1, -1));

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Keterangan.png"))); // NOI18N
        masterBrgPanel.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 610, -1, -1));

        addIdBrng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/autonumber.png"))); // NOI18N
        addIdBrng.setBorder(null);
        addIdBrng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIdBrngActionPerformed(evt);
            }
        });
        masterBrgPanel.add(addIdBrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 40, 40));

        content.add(masterBrgPanel, "card4");

        masterSplPanel.setBackground(new java.awt.Color(237, 224, 209));
        masterSplPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTeleponsup.setBackground(new java.awt.Color(203, 161, 93));
        txtTeleponsup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTeleponsup.setBorder(null);
        masterSplPanel.add(txtTeleponsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 620, 560, 50));

        txtIdsup.setBackground(new java.awt.Color(203, 161, 93));
        txtIdsup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtIdsup.setBorder(null);
        masterSplPanel.add(txtIdsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 560, 50));

        txtNamasup.setBackground(new java.awt.Color(203, 161, 93));
        txtNamasup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtNamasup.setBorder(null);
        masterSplPanel.add(txtNamasup, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 560, 50));

        txtAlamatsup.setBackground(new java.awt.Color(203, 161, 93));
        txtAlamatsup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAlamatsup.setBorder(null);
        masterSplPanel.add(txtAlamatsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, 560, 50));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MASTER SUPPLIER.png"))); // NOI18N
        masterSplPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterSplPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterSplPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 620, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterSplPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterSplPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 620, -1, -1));

        hapusBtnSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON HAPUS.png"))); // NOI18N
        hapusBtnSpl.setBorder(null);
        hapusBtnSpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnSplActionPerformed(evt);
            }
        });
        masterSplPanel.add(hapusBtnSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 730, -1, -1));

        resetBtnSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON RESET.png"))); // NOI18N
        resetBtnSpl.setBorder(null);
        resetBtnSpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnSplActionPerformed(evt);
            }
        });
        masterSplPanel.add(resetBtnSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 730, -1, -1));

        editBtnSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON EDIT.png"))); // NOI18N
        editBtnSpl.setBorder(null);
        editBtnSpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnSplActionPerformed(evt);
            }
        });
        masterSplPanel.add(editBtnSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 730, -1, -1));

        tambahBtnSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON TAMBAH.png"))); // NOI18N
        tambahBtnSpl.setBorder(null);
        tambahBtnSpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBtnSplActionPerformed(evt);
            }
        });
        masterSplPanel.add(tambahBtnSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 730, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/deletesupdet.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        masterSplPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 860, -1, -1));

        masterPaketSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON PAKET.png"))); // NOI18N
        masterPaketSpl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterPaketSplMouseClicked(evt);
            }
        });
        masterSplPanel.add(masterPaketSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 950, -1, -1));

        masterAdminSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON ADMIN.png"))); // NOI18N
        masterAdminSpl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterAdminSplMouseClicked(evt);
            }
        });
        masterSplPanel.add(masterAdminSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 950, -1, -1));

        masterBarangSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON BARANG.png"))); // NOI18N
        masterBarangSpl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterBarangSplMouseClicked(evt);
            }
        });
        masterSplPanel.add(masterBarangSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 950, -1, -1));

        masterKemasanSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON KEMASAN.png"))); // NOI18N
        masterKemasanSpl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterKemasanSplMouseClicked(evt);
            }
        });
        masterSplPanel.add(masterKemasanSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 950, -1, -1));

        searchBtnSpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kecil.png"))); // NOI18N
        searchBtnSpl.setBorder(null);
        searchBtnSpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnSplActionPerformed(evt);
            }
        });
        masterSplPanel.add(searchBtnSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 60, -1, -1));

        txtSearchSpl.setBackground(new java.awt.Color(220, 193, 151));
        txtSearchSpl.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSearchSpl.setBorder(null);
        txtSearchSpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSplActionPerformed(evt);
            }
        });
        masterSplPanel.add(txtSearchSpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 50, 270, 40));

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/textfieldmstr.png"))); // NOI18N
        masterSplPanel.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, -1, -1));
        masterSplPanel.add(txtKapasitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 865, 70, 40));

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSupplier.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tableSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSupplierMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSupplier);

        masterSplPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 890, 340));

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
        tbDt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDtMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbDt);

        masterSplPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 130, -1, 340));

        addDetailSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plusijo.png"))); // NOI18N
        addDetailSup.setBorder(null);
        addDetailSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDetailSupActionPerformed(evt);
            }
        });
        masterSplPanel.add(addDetailSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 860, -1, -1));

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Alamat.png"))); // NOI18N
        masterSplPanel.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, -1, -1));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Supplier.png"))); // NOI18N
        masterSplPanel.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Supplier.png"))); // NOI18N
        masterSplPanel.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 830, -1, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Telepon label.png"))); // NOI18N
        masterSplPanel.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 590, -1, -1));

        addIdSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/autonumber.png"))); // NOI18N
        addIdSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIdSupActionPerformed(evt);
            }
        });
        masterSplPanel.add(addIdSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 525, 40, 40));

        cmSup.setBorder(null);
        cmSup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Select---" }));
        cmSup.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        masterSplPanel.add(cmSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 865, 330, 40));

        cmBrng.setBorder(null);
        cmBrng.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Select---" }));
        cmBrng.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        masterSplPanel.add(cmBrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 865, 340, 40));

        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Supplier.png"))); // NOI18N
        masterSplPanel.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, -1, -1));

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/kpsts.png"))); // NOI18N
        masterSplPanel.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 860, 130, 50));

        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Kapasitas label.png"))); // NOI18N
        masterSplPanel.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 830, -1, -1));

        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxmstrclk.png"))); // NOI18N
        masterSplPanel.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 860, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        masterSplPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxmstrclk.png"))); // NOI18N
        masterSplPanel.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 860, -1, -1));

        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Barang Label.png"))); // NOI18N
        masterSplPanel.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 830, -1, -1));

        content.add(masterSplPanel, "card5");

        masterKmsPanel.setBackground(new java.awt.Color(237, 224, 209));
        masterKmsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtHargakem.setBackground(new java.awt.Color(203, 161, 93));
        txtHargakem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtHargakem.setBorder(null);
        masterKmsPanel.add(txtHargakem, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 600, 550, 50));

        txtIdkem.setBackground(new java.awt.Color(203, 161, 93));
        txtIdkem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtIdkem.setBorder(null);
        masterKmsPanel.add(txtIdkem, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, 550, 50));

        txtJeniskem.setBackground(new java.awt.Color(203, 161, 93));
        txtJeniskem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtJeniskem.setBorder(null);
        masterKmsPanel.add(txtJeniskem, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 400, 550, 50));

        txtMuat.setBackground(new java.awt.Color(203, 161, 93));
        txtMuat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtMuat.setBorder(null);
        masterKmsPanel.add(txtMuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 500, 550, 50));

        kmsTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MASTER KEMASAN.png"))); // NOI18N
        masterKmsPanel.add(kmsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterKmsPanel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterKmsPanel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, -1, -1));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterKmsPanel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, -1, -1));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TEXT FIELD KECIl.png"))); // NOI18N
        masterKmsPanel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, -1, -1));

        hapusBtnKms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON HAPUS.png"))); // NOI18N
        hapusBtnKms.setBorder(null);
        hapusBtnKms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnKmsActionPerformed(evt);
            }
        });
        masterKmsPanel.add(hapusBtnKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 830, -1, -1));

        resetBtnKms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON RESET.png"))); // NOI18N
        resetBtnKms.setBorder(null);
        resetBtnKms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnKmsActionPerformed(evt);
            }
        });
        masterKmsPanel.add(resetBtnKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 830, -1, -1));

        editBtnKms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON EDIT.png"))); // NOI18N
        editBtnKms.setBorder(null);
        editBtnKms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnKmsActionPerformed(evt);
            }
        });
        masterKmsPanel.add(editBtnKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 830, -1, -1));

        tambahBtnKms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON TAMBAH.png"))); // NOI18N
        tambahBtnKms.setBorder(null);
        tambahBtnKms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBtnKmsActionPerformed(evt);
            }
        });
        masterKmsPanel.add(tambahBtnKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 830, -1, -1));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON PAKET.png"))); // NOI18N
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel44MouseClicked(evt);
            }
        });
        masterKmsPanel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 950, -1, -1));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON ADMIN.png"))); // NOI18N
        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel45MouseClicked(evt);
            }
        });
        masterKmsPanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 950, -1, -1));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON BARANG.png"))); // NOI18N
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
        });
        masterKmsPanel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 950, -1, -1));

        masterSupplierKms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BUTTON SUPPLIER.png"))); // NOI18N
        masterSupplierKms.setToolTipText("");
        masterSupplierKms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterSupplierKmsMouseClicked(evt);
            }
        });
        masterKmsPanel.add(masterSupplierKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 950, -1, -1));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borderrr.png"))); // NOI18N
        masterKmsPanel.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        txtSearchKms.setBackground(new java.awt.Color(220, 193, 151));
        txtSearchKms.setBorder(null);
        masterKmsPanel.add(txtSearchKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 186, 270, 30));

        searchBtnKms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup kecil.png"))); // NOI18N
        searchBtnKms.setBorder(null);
        searchBtnKms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnKmsActionPerformed(evt);
            }
        });
        masterKmsPanel.add(searchBtnKms, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, -1, -1));

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/textfieldmstr.png"))); // NOI18N
        masterKmsPanel.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jScrollPane4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

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
        jScrollPane4.setViewportView(tableKemasan);

        masterKmsPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 640, -1));

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ID Kemasan.png"))); // NOI18N
        masterKmsPanel.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, -1, -1));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Nama Kemasan.png"))); // NOI18N
        masterKmsPanel.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Kapasitas label.png"))); // NOI18N
        masterKmsPanel.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, -1, -1));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Harga label.png"))); // NOI18N
        masterKmsPanel.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 570, -1, -1));

        content.add(masterKmsPanel, "card6");

        javax.swing.GroupLayout bodyyLayout = new javax.swing.GroupLayout(bodyy);
        bodyy.setLayout(bodyyLayout);
        bodyyLayout.setHorizontalGroup(
            bodyyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyyLayout.createSequentialGroup()
                .addComponent(menuBarr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyyLayout.setVerticalGroup(
            bodyyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBarr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void masterBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterBarangMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterBrgPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterBarangMouseClicked

    private void usernameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTxtActionPerformed

    private void resetButtonAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonAdmActionPerformed
    reset();
        // TODO add your handling code here:
    }//GEN-LAST:event_resetButtonAdmActionPerformed

    private void txtRf_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRf_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRf_idActionPerformed

    private void tambahButtonBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonBrgActionPerformed
 try{
             String sql = "INSERT INTO barang VALUES('"+txtId.getText()
                    +"','"+txtNama.getText()+"','"+txtJual.getText()
                    +"','"+txtBeli.getText()+"','"+ comboBoxJenis.getSelectedItem()+"','"+txtKet.getSelectedItem()+"')";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            //JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil!");
            txtJoption.setText("Data Berhasil DiTambah");
            me.showAlert();
            table1();
            reset1();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahButtonBrgActionPerformed

    private void jLabel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterPktPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_jLabel44MouseClicked

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterAdmPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_jLabel45MouseClicked

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterBrgPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_jLabel46MouseClicked

    private void masterSupplierKmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterSupplierKmsMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterSplPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterSupplierKmsMouseClicked

    private void masterPaketSplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterPaketSplMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterPktPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterPaketSplMouseClicked

    private void masterAdminSplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterAdminSplMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterAdmPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterAdminSplMouseClicked

    private void masterBarangSplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterBarangSplMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterBrgPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterBarangSplMouseClicked

    private void masterKemasanSplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterKemasanSplMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterKmsPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterKemasanSplMouseClicked

    private void masterPaketBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterPaketBrgMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterPktPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterPaketBrgMouseClicked

    private void masterAdminBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterAdminBrgMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterAdmPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterAdminBrgMouseClicked

    private void masterSupplierbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterSupplierbrgMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterSplPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterSupplierbrgMouseClicked

    private void masterKemasanBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterKemasanBrgMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterKmsPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterKemasanBrgMouseClicked

    private void masterPaketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterPaketMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterPktPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterPaketMouseClicked

    private void masterSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterSupplierMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterSplPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterSupplierMouseClicked

    private void masterKemasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterKemasanMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterKmsPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterKemasanMouseClicked

    private void masterAdminPktMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterAdminPktMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterAdmPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterAdminPktMouseClicked

    private void masterBarangPktMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterBarangPktMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterBrgPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterBarangPktMouseClicked

    private void masterSupplierPktMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterSupplierPktMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterSplPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterSupplierPktMouseClicked

    private void masterKemasanPktMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterKemasanPktMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterKmsPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterKemasanPktMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(home);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void laporanPenjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanPenjMouseClicked
        // TODO add your handling code here:
        setVisible(false);
        new laporanPenjualan().setVisible(true);
    }//GEN-LAST:event_laporanPenjMouseClicked

    private void laporanPenjMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanPenjMouseEntered
        // TODO add your handling code here:
        laporanPenj.setBackground(new Color(177,113,62));
        laporanPenj.setOpaque(true);
    }//GEN-LAST:event_laporanPenjMouseEntered

    private void laporanPenjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanPenjMouseExited
        // TODO add your handling code here:
        laporanPenj.setBackground(new Color(234,204,154));
    }//GEN-LAST:event_laporanPenjMouseExited

    private void masterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterMouseClicked
        // TODO add your handling code here:
        content.removeAll();
        content.repaint();
        content.revalidate();
        
        content.add(masterAdmPanel);
        content.repaint();
        content.revalidate();
    }//GEN-LAST:event_masterMouseClicked

    private void masterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterMouseEntered
        // TODO add your handling code here:
        master.setBackground(new Color(177,113,62));
        master.setOpaque(true);
    }//GEN-LAST:event_masterMouseEntered

    private void masterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterMouseExited
        // TODO add your handling code here:
        master.setBackground(new Color(234,204,154));
    }//GEN-LAST:event_masterMouseExited

    private void transactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactMouseClicked
        // TODO add your handling code here:
        new transaksiFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_transactMouseClicked

    private void transactMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactMouseEntered
        // TODO add your handling code here:
        transact.setBackground(new Color(177,113,62));
        transact.setOpaque(true);
    }//GEN-LAST:event_transactMouseEntered

    private void transactMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactMouseExited
        // TODO add your handling code here:
        transact.setBackground(new Color(234,204,154));
    }//GEN-LAST:event_transactMouseExited

    private void pengambilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengambilanMouseClicked
        // TODO add your handling code here:
        new pengambilanFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pengambilanMouseClicked

    private void pengambilanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengambilanMouseEntered
        // TODO add your handling code here:
        pengambilan.setBackground(new Color(177,113,62));
        pengambilan.setOpaque(true);
    }//GEN-LAST:event_pengambilanMouseEntered

    private void pengambilanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengambilanMouseExited
        // TODO add your handling code here:
        pengambilan.setBackground(new Color(234,204,154));
    }//GEN-LAST:event_pengambilanMouseExited

    private void setoranSplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setoranSplMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new setoranSupplier().setVisible(true);
    }//GEN-LAST:event_setoranSplMouseClicked

    private void setoranSplMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setoranSplMouseEntered
        // TODO add your handling code here:
        setoranSpl.setBackground(new Color(177,113,62));
        setoranSpl.setOpaque(true);
    }//GEN-LAST:event_setoranSplMouseEntered

    private void setoranSplMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setoranSplMouseExited
        // TODO add your handling code here:
        setoranSpl.setBackground(new Color(234,204,154));
    }//GEN-LAST:event_setoranSplMouseExited

    private void searchBtnPktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnPktActionPerformed
    DefaultTableModel model2 = new DefaultTableModel();
model2.addColumn("No");
model2.addColumn("ID Paket");
model2.addColumn("Nama Paket");
model2.addColumn("Harga");
model2.addColumn("Jumlah Kue");
model2.addColumn("Macam Kue");
model2.addColumn("ID Kemasan");

try {
    String searchKeyword = txtSearchPkt.getText().trim(); // Mengambil teks pencarian dan menghapus spasi di awal dan akhir

    // Jika teks pencarian tidak kosong
    if (!searchKeyword.isEmpty()) {
        String sql = "SELECT id_paket, nama_paket, harga, jml_kue, macam_kue, id_kemasan FROM paket WHERE id_paket = ? OR nama_paket = ?";
        java.sql.Connection conn = new config().akses();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, searchKeyword);
        pst.setString(2, searchKeyword);
        java.sql.ResultSet res = pst.executeQuery();

        int rowNum = 1; // Variabel untuk nomor urut
        while (res.next()) {
            model2.addRow(new Object[]{
                rowNum, // Menambahkan nomor urut pada setiap baris
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6)
            });
            rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
        }
    } else {
        // Jika teks pencarian kosong, mengosongkan model
        model2.setRowCount(0);
        try {
            String sql = "SELECT id_paket, nama_paket, harga, jml_kue, macam_kue, id_kemasan FROM paket";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery();

            int rowNum = 1; // Variabel untuk nomor urut
            while (res.next()) {
                model2.addRow(new Object[]{
                    rowNum, // Menambahkan nomor urut pada setiap baris
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)
                });
                rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    tablePaket.setModel(model2);
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}
    }//GEN-LAST:event_searchBtnPktActionPerformed

    private void searchBtnAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnAdmActionPerformed
 DefaultTableModel model = new DefaultTableModel();
model.addColumn("no");
model.addColumn("Username");
model.addColumn("rf_id");
model.addColumn("alamat");
model.addColumn("telepon");
model.addColumn("Password");
model.addColumn("Hak");
model.addColumn("Pertanyaan");
model.addColumn("Jawaban");

try {
    String cariNama = txtSearchAdm.getText();
    String sql = "SELECT * FROM login WHERE username LIKE ?";
    java.sql.Connection conn = new config().akses();
    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, cariNama + "%");
    java.sql.ResultSet res = pst.executeQuery();

    int no = 1;
    while (res.next()) {
        model.addRow(new Object[]{
            no++,
            res.getString("username"),
            res.getString("rf_id"),
            res.getString("alamat"),
            res.getString("telepon"),
            res.getString("password"),
            res.getString("hak"),
            res.getString("Pertanyaan"),
            res.getString("Jawaban")
        });
    }

    tableAdmin.setModel(model);
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}

    }//GEN-LAST:event_searchBtnAdmActionPerformed

    private void searchBtnBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnBrgActionPerformed
    DefaultTableModel model1 = new DefaultTableModel();
model1.addColumn("No");
model1.addColumn("ID Barang");
model1.addColumn("Nama Barang");
model1.addColumn("Harga Jual");
model1.addColumn("Harga Beli");
model1.addColumn("Keterangan");
model1.addColumn("Jenis");

try {
    String searchKeyword = txtSearchBrg.getText().trim(); // Mengambil teks pencarian dan menghapus spasi di awal dan akhir

    // Jika teks pencarian tidak kosong
    if (!searchKeyword.isEmpty()) {
        String sql = "SELECT id_barang, nama_barang, harga_jual, harga_beli, keterangan, jenis_kue FROM barang WHERE id_barang = ? OR nama_barang = ?";
        java.sql.Connection conn = new config().akses();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, searchKeyword);
        pst.setString(2, searchKeyword);
        java.sql.ResultSet res = pst.executeQuery();

        int rowNum = 1; // Variabel untuk nomor urut
        while (res.next()) {
            model1.addRow(new Object[]{
                rowNum, // Menambahkan nomor urut pada setiap baris
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6)
            });
            rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
        }
    } else {
        // Jika teks pencarian kosong, mengosongkan model
        model1.setRowCount(0);
        try {
            String sql = "SELECT id_barang, nama_barang, harga_jual, harga_beli, keterangan, jenis_kue FROM barang";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery();

            int rowNum = 1; // Variabel untuk nomor urut
            while (res.next()) {
                model1.addRow(new Object[]{
                    rowNum, // Menambahkan nomor urut pada setiap baris
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)
                });
                rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    tableBarang.setModel(model1);
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}
    }//GEN-LAST:event_searchBtnBrgActionPerformed

    private void searchBtnSplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnSplActionPerformed
    DefaultTableModel model3 = new DefaultTableModel();
model3.addColumn("No");
model3.addColumn("ID Supplier");
model3.addColumn("Nama Supplier");
model3.addColumn("Alamat");
model3.addColumn("Telepon");

try {
    String searchKeyword = txtSearchSpl.getText().trim(); // Mengambil teks pencarian dan menghapus spasi di awal dan akhir

    String sql;
    java.sql.Connection conn = new config().akses();
    java.sql.PreparedStatement pst;

    // Jika teks pencarian tidak kosong
    if (!searchKeyword.isEmpty()) {
        sql = "SELECT id_suplier, suplier, alamat_suplier, telepon_suplier FROM suplier WHERE id_suplier = ? OR suplier = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, searchKeyword);
        pst.setString(2, searchKeyword);
    } else {
        sql = "SELECT id_suplier, suplier, alamat_suplier, telepon_suplier FROM suplier";
        pst = conn.prepareStatement(sql);
    }

    java.sql.ResultSet res = pst.executeQuery();

    int rowNum = 1; // Variabel untuk nomor urut
    while (res.next()) {
        model3.addRow(new Object[]{
            rowNum, // Menambahkan nomor urut pada setiap baris
            res.getString(1),
            res.getString(2),
            res.getString(3),
            res.getString(4)
        });
        rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
    }

    tableSupplier.setModel(model3);
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}

// Event handling saat teksfield dikosongkan
if (txtSearchSpl.getText().isEmpty()) {
    // Menghapus semua baris yang ada dalam model
    model3.setRowCount(0);
    try {
        String sql = "SELECT id_suplier, suplier, alamat_suplier, telepon_suplier FROM suplier";
        java.sql.Connection conn = new config().akses();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet res = pst.executeQuery();

        int rowNum = 1; // Variabel untuk nomor urut
        while (res.next()) {
            model3.addRow(new Object[]{
                rowNum, // Menambahkan nomor urut pada setiap baris
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4)
            });
            rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
        }

        tableSupplier.setModel(model3);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
}
    }//GEN-LAST:event_searchBtnSplActionPerformed

    private void searchBtnKmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnKmsActionPerformed
    DefaultTableModel model4 = new DefaultTableModel();
model4.addColumn("No");
model4.addColumn("ID Kemasan");
model4.addColumn("Jenis Kemasan");
model4.addColumn("Muat");

try {
    String searchKeyword = txtSearchKms.getText().trim(); // Mengambil teks pencarian dan menghapus spasi di awal dan akhir

    // Jika teks pencarian tidak kosong
    if (!searchKeyword.isEmpty()) {
        String sql = "SELECT id_kemasan, jenis_kemasan, muat FROM kemasan WHERE id_kemasan = ? OR jenis_kemasan = ?";
        java.sql.Connection conn = new config().akses();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, searchKeyword);
        pst.setString(2, searchKeyword);
        java.sql.ResultSet res = pst.executeQuery();

        int rowNum = 1; // Variabel untuk nomor urut
        while (res.next()) {
            model4.addRow(new Object[]{
                rowNum, // Menambahkan nomor urut pada setiap baris
                res.getString(1),
                res.getString(2),
                res.getString(3)
            });
            rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
        }
    } else {
        // Jika teks pencarian kosong, mengosongkan model
        model4.setRowCount(0);
        try {
            String sql = "SELECT id_kemasan, jenis_kemasan, muat FROM kemasan";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery();

            int rowNum = 1; // Variabel untuk nomor urut
            while (res.next()) {
                model4.addRow(new Object[]{
                    rowNum, // Menambahkan nomor urut pada setiap baris
                    res.getString(1),
                    res.getString(2),
                    res.getString(3)
                });
                rowNum++; // Menambahkan nilai nomor urut setiap kali baris ditambahkan
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    tableKemasan.setModel(model4);
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}
    }//GEN-LAST:event_searchBtnKmsActionPerformed

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void hapusButtonAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonAdmActionPerformed
  try{
        String sql = "DELETE FROM login WHERE username ='"+usernameTxt.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Terhapus!");
            txtWarn.setText("Data Berhasil Dihapus");
            me.showAlert();
            //JOptionPane.showMessageDialog(null, "Hapus Data Berhasil...");
            table();
            reset();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }     
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusButtonAdmActionPerformed

    private void editButtonAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonAdmActionPerformed
 try{
            String sql = "UPDATE login SET username = '"+usernameTxt.getText()+"',rf_id='"+txtRf_id.getText()+"',alamat='"+txtAlamat.getText()+
                    "',telepon='"+txtTelepon.getText()+"',password='"+passwordAdmTxt.getText()+"',hak='"+hak.getSelectedItem()+"',Pertanyaan='"+pertanyaanTxt.getText()+"',Jawaban='"+jawabanTxt.getText()+"' WHERE username = '"
                    +usernameTxt.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Edit Data Berhasil...");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil!");
            txtJoption.setText("Data Berhasil Diedit");
            me.showAlert();
            table();
            reset();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonAdmActionPerformed

    private void tambahButtonAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonAdmActionPerformed
    try {
    String sql = "INSERT INTO login (username, rf_id, alamat, telepon, password, hak, pertanyaan, jawaban) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    java.sql.Connection conn = new config().akses();
    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
    
    // Mengisi parameter pada pernyataan prepared statement
    pst.setString(1, usernameTxt.getText());
    pst.setString(2, txtRf_id.getText());
    pst.setString(3, txtAlamat.getText());
    pst.setString(4, txtTelepon.getText());
    pst.setString(5, passwordAdmTxt.getText());
    pst.setString(6, hak.getSelectedItem().toString());
    pst.setString(7, pertanyaanTxt.getText());
    pst.setString(8, jawabanTxt.getText());
    
    pst.execute();
    
    Message me = new Message(this, true);
    judulJoption.setText("Berhasil!");
    txtJoption.setText("Data Berhasil Ditambahkan");
    me.showAlert();
    table();
    reset();
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage());
}
    }//GEN-LAST:event_tambahButtonAdmActionPerformed

    private void editButtonBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonBrgActionPerformed
 try{
            String sql = "UPDATE barang SET id_barang = '"+txtId.getText()+"',nama_barang='"+txtNama.getText()+"',harga_jual='"+txtJual.getText()+
                    "',harga_beli='"+txtBeli.getText()+"',keterangan='"+ comboBoxJenis.getSelectedItem()+"', jenis_kue='"+txtKet.getSelectedItem()+"' WHERE id_barang = '"
                    +txtId.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Edit Data Berhasil...");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil!");
            txtJoption.setText("Data Berhasil Diedit");
            me.showAlert();
            table1();
            reset1();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonBrgActionPerformed

    private void resetButtonBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonBrgActionPerformed
    reset1();
        // TODO add your handling code here:
    }//GEN-LAST:event_resetButtonBrgActionPerformed

    private void hapusButtonBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonBrgActionPerformed
 try{
        String sql = "DELETE FROM barang WHERE id_barang ='"+txtId.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Terhapus!");
            txtWarn.setText("Data Berhasil Dihapus");
            me.showAlert();
            table1();
            reset1();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusButtonBrgActionPerformed

    private void tambahBtnKmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBtnKmsActionPerformed
 try{
             String sql = "INSERT INTO kemasan VALUES('"+txtIdkem.getText()
                    +"','"+txtJeniskem.getText()+"','"+txtMuat.getText()+"','"+txtHargakem.getText()
                    +"')";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            Message me = new Message(this, true);
        judulJoption.setText("Berhasil!");
        txtJoption.setText("Data Berhasil Ditambahkan");
        me.showAlert();
            table4();
            reset4();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahBtnKmsActionPerformed

    private void editBtnKmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnKmsActionPerformed
try{
            String sql = "UPDATE kemasan SET id_kemasan  = '"+txtIdkem.getText()+"',jenis_kemasan='"+txtJeniskem.getText()+"',muat='"+txtMuat.getText()+"',harga='"+txtHargakem.getText()+
                    "' WHERE id_kemasan = '"
                    +txtIdkem.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Edit Data Berhasil...");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil!");
            txtJoption.setText("Data Berhasil Diedit");
            me.showAlert();
            table4();
            reset4();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        // TODO add your handling code here:
        
    }//GEN-LAST:event_editBtnKmsActionPerformed

    private void tambahBtnSplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBtnSplActionPerformed
 try{
             String sql = "INSERT INTO suplier VALUES('"+txtIdsup.getText()
                    +"','"+txtNamasup.getText()+"','"+txtAlamatsup.getText()
                    +"','"+txtTeleponsup.getText()+"',null,null,null)";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            Message me = new Message(this, true);
        judulJoption.setText("Berhasil!");
        txtJoption.setText("Data Berhasil Ditambahkan");
        me.showAlert();
            table3();
            reset3();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        setCmSup();
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahBtnSplActionPerformed

    private void editBtnSplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnSplActionPerformed
try{
            String sql = "UPDATE suplier SET id_suplier = '"+txtId.getText()+"',suplier='"+txtNamasup.getText()+"',alamat_suplier='"+txtAlamatsup.getText()+
                    "',telepon_suplier='"+txtTeleponsup.getText()+"' WHERE id_suplier = '"
                    +txtId.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Edit Data Berhasil...");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil!");
            txtJoption.setText("Data Berhasil Diedit");
            me.showAlert();
            table3();
            reset3();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_editBtnSplActionPerformed

    private void hapusBtnSplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnSplActionPerformed
         try{
            String sql = "DELETE FROM suplier WHERE id_suplier ='"+txtIdsup.getText()+"'";
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                failMessage me = new failMessage(this, true);
                judulWarn.setText("Terhapus!");
                txtWarn.setText("Data Berhasil Dihapus");
                me.showAlert();
                table3();
                reset3();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusBtnSplActionPerformed

    private void editBtnPktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnPktActionPerformed
        String kemen = cmKemasan.getSelectedItem().toString();
        String[] parts = kemen.split("-");
        String kem = parts[0];
        try{
            String sql = "UPDATE paket SET id_paket  = '"+txtIdpaket.getText()+"',nama_paket='"+txtNamaPaket.getText()+"',"
                    + "harga='"+txtHargapaket.getText()+"',jml_kue='"+txtJumlahpaket.getText()+"',macam_kue='"+txtMacampaket.getText()+"',id_kemasan='"+kem+
                   
                    "' WHERE id_paket = '"+txtIdpaket.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Edit Data Berhasil...");
            Message me = new Message(this, true);
            judulJoption.setText("Berhasil!");
            txtJoption.setText("Data Berhasil Diedit");
            me.showAlert();
            table2();
            reset2();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_editBtnPktActionPerformed

    private void tambahBtnPktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBtnPktActionPerformed
        String kemen = cmKemasan.getSelectedItem().toString();
        String[] parts = kemen.split("-");
        String kem = parts[0];
        try{
             String sql = "INSERT INTO paket VALUES('"+txtIdpaket.getText()
                    +"','"+txtNamaPaket.getText()+"','"+txtHargapaket.getText()
                    +"','"+txtJumlahpaket.getText()+"','"+txtMacampaket.getText()+"','"+kem+"')";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            Message me = new Message(this, true);
        judulJoption.setText("Berhasil!");
        txtJoption.setText("Data Berhasil Ditambahkan");
        me.showAlert();
            table2();
            reset2();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahBtnPktActionPerformed

    private void hapusBtnPktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnPktActionPerformed
try{
        String sql = "DELETE FROM paket WHERE id_paket ='"+txtIdpaket.getText()+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            failMessage me = new failMessage(this, true);
            judulWarn.setText("Terhapus!");
            txtWarn.setText("Data Berhasil Dihapus");
            me.showAlert();
            table2();
            reset2();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        // TODO add your handling code here:
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusBtnPktActionPerformed

    private void resetBtnKmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnKmsActionPerformed
        // TODO add your handling code here:
        reset4();
    }//GEN-LAST:event_resetBtnKmsActionPerformed

    private void resetBtnPktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnPktActionPerformed
        // TODO add your handling code here:
        reset2();
    }//GEN-LAST:event_resetBtnPktActionPerformed

    private void tablePaketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePaketMouseClicked
        // TODO add your handling code here:
         int baris = tablePaket.rowAtPoint(evt.getPoint());
        String id_paket = tablePaket.getValueAt(baris, 1).toString();
        txtIdpaket.setText(id_paket);

        String nama_paket = tablePaket.getValueAt(baris, 2).toString();
        txtNamaPaket.setText(nama_paket);

        String harga = tablePaket.getValueAt(baris, 3).toString();
        txtHargapaket.setText(harga);
        
          String jml_kue = tablePaket.getValueAt(baris, 4).toString();
        txtJumlahpaket.setText(jml_kue);
        
        String macam_kue = tablePaket.getValueAt(baris, 5).toString();
        txtMacampaket.setText(macam_kue);
        
          String id_kemasan = tablePaket.getValueAt(baris, 6).toString();
        try{
            String sql = "SELECT id_kemasan, jenis_kemasan from kemasan where id_kemasan = '"+id_kemasan+"' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmKemasan.setSelectedItem(res.getString("id_kemasan")+"-"+res.getString("jenis_kemasan"));
                }
        }catch(Exception e){
            
        }
        
    }//GEN-LAST:event_tablePaketMouseClicked

    private void tableAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAdminMouseClicked
        // TODO add your handling code here:
        int baris = tableAdmin.rowAtPoint(evt.getPoint());
        String username = tableAdmin.getValueAt(baris, 1).toString();
        usernameTxt.setText(username);
        
          String rf_id = tableAdmin.getValueAt(baris, 2).toString();
        txtRf_id.setText(rf_id);

        String alamat = tableAdmin.getValueAt(baris, 3).toString();
        txtAlamat.setText(alamat);
 
        String telepon = tableAdmin.getValueAt(baris, 4).toString();
        txtTelepon.setText(telepon);

        String password = tableAdmin.getValueAt(baris, 5).toString();
        passwordAdmTxt.setText(password);

        String Hak = tableAdmin.getValueAt(baris, 6).toString();
        hak.setSelectedItem(Hak);
        
        String Pertanyaan = tableAdmin.getValueAt( baris , 7).toString();
        pertanyaanTxt.setText(Pertanyaan);
        
        String Jawaban = tableAdmin.getValueAt(baris, 8).toString();
        jawabanTxt.setText(Jawaban);
        
    }//GEN-LAST:event_tableAdminMouseClicked

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
        int baris = tableBarang.rowAtPoint(evt.getPoint());
        String id_barang = tableBarang.getValueAt(baris, 1).toString();
        txtId.setText(id_barang);

        String nama_barang = tableBarang.getValueAt(baris, 2).toString();
        txtNama.setText(nama_barang);

        String harga_jual = tableBarang.getValueAt(baris, 3).toString();
        txtJual.setText(harga_jual);

        String harga_beli = tableBarang.getValueAt(baris, 4).toString();
        txtBeli.setText(harga_beli);

        String keterangan = tableBarang.getValueAt(baris, 5).toString();
        comboBoxJenis.setSelectedItem(keterangan);
        
        String jenis_kue = tableBarang.getValueAt(baris, 6).toString();
        txtKet.setSelectedItem(jenis_kue);
    }//GEN-LAST:event_tableBarangMouseClicked

    private void tableSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSupplierMouseClicked
        // TODO add your handling code here:
        int baris = tableSupplier.rowAtPoint(evt.getPoint());
        String id_suplier = tableSupplier.getValueAt(baris, 1).toString();
        txtIdsup.setText(id_suplier);

        String suplier = tableSupplier.getValueAt(baris, 2).toString();
        txtNamasup.setText(suplier);

        String alamat_suplier = tableSupplier.getValueAt(baris, 3).toString();
        txtAlamatsup.setText(alamat_suplier);

        String telepon_suplier = tableSupplier.getValueAt(baris, 4).toString();
        txtTeleponsup.setText(telepon_suplier);
    }//GEN-LAST:event_tableSupplierMouseClicked

    private void tableKemasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKemasanMouseClicked
        // TODO add your handling code here:
        int baris = tableKemasan.rowAtPoint(evt.getPoint());
        String id_kemasan = tableKemasan.getValueAt(baris, 1).toString();
        txtIdkem.setText(id_kemasan);

        String jenis_kemasan = tableKemasan.getValueAt(baris, 2).toString();
        txtJeniskem.setText(jenis_kemasan);
        
        
        String muat = tableKemasan.getValueAt(baris, 3).toString();
        txtMuat.setText(muat);

        String harga = tableKemasan.getValueAt(baris, 4).toString();
        txtHargakem.setText(harga); 
    }//GEN-LAST:event_tableKemasanMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        failMessage me = new failMessage(this, true);
        judulWarn.setText("Berhasil Logout!");
        txtWarn.setText("Kembali ke Login");
        me.showAlert();
        setVisible(false);
        new login().setVisible(true);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleponActionPerformed
try {
            Long.parseLong(txtTelepon.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Harap masukkan No. Telephone dengan bener");
            txtTelepon.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleponActionPerformed

    private void txtSearchAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchAdmActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new pesananMendatangFrame().setVisible(true);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void txtMendatangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMendatangMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new pesananMendatangFrame().setVisible(true);
    }//GEN-LAST:event_txtMendatangMouseClicked

    private void txtSearchBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBrgActionPerformed

    private void resetBtnSplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnSplActionPerformed
        // TODO add your handling code here:
        reset3();
    }//GEN-LAST:event_resetBtnSplActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        autonumberPkt();
    }//GEN-LAST:event_addActionPerformed

    private void addIdBrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIdBrngActionPerformed
        // TODO add your handling code here:
        autonumberBrng();
    }//GEN-LAST:event_addIdBrngActionPerformed

    private void addIdSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIdSupActionPerformed
        // TODO add your handling code here:
        autonumberSup();
    }//GEN-LAST:event_addIdSupActionPerformed

    private void addDetailSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDetailSupActionPerformed
        // TODO add your handling code here:
        String sup = cmSup.getSelectedItem().toString();
        String brng = cmBrng.getSelectedItem().toString();
        String kap = String.valueOf(txtKapasitas.getValue());
        
        try{
            String sql = "SELECT id_suplier FROM suplier where suplier = '"+sup+"' ";
            String sql1 = "SELECT id_barang FROM barang where nama_barang = '"+brng+"' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
            while(res.next()){
                sup = res.getString("id_suplier");
            }
            while(res1.next()){
                brng = res1.getString("id_barang");
            }
        }catch(SQLException ex){}
        
        try {
            String sql = "insert into detail_suplier (id_suplier, id_barang, stok_barang) "
                    + "values ('" + sup + "','" + brng + "','" + kap + "')";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
            
            //JOptionPane.showMessageDialog(this, "Detail Suplier Disimpan");
            Message me = new Message(this, true);
        judulJoption.setText("Berhasil!");
        txtJoption.setText("Detail supplier berhasil disimpan");
        me.showAlert();
            cmSup.setSelectedIndex(0);
            cmBrng.setSelectedIndex(0);
            txtKapasitas.setValue(0);
            tbDtSup();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_addDetailSupActionPerformed

    private void hapusBtnKmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnKmsActionPerformed
        // TODO add your handling code here:
        try{
            String sql = "DELETE FROM kemasan WHERE id_kemasan ='"+txtIdkem.getText()+"'";
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                failMessage me = new failMessage(this, true);
                judulWarn.setText("Terhapus!");
                txtWarn.setText("Data Berhasil Dihapus");
                me.showAlert();
                table3();
                reset3();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_hapusBtnKmsActionPerformed

    private void txtSearchSplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSplActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSplActionPerformed

    private void tbDtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDtMouseClicked
        // TODO add your handling code here:
        int baris = tbDt.rowAtPoint(evt.getPoint());
        String sup = tbDt.getValueAt(baris, 0).toString();
        String brng = tbDt.getValueAt(baris, 1).toString();
        String kap = tbDt.getValueAt(baris, 2).toString();
        
        cmSup.setSelectedItem(sup);
        cmBrng.setSelectedItem(brng);
        txtKapasitas.setValue(Integer.valueOf(kap));
    }//GEN-LAST:event_tbDtMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sup = cmSup.getSelectedItem().toString();
        String brng = cmBrng.getSelectedItem().toString();
        
        try{
            String sql = "SELECT id_suplier FROM suplier where suplier = '"+sup+"' ";
            String sql1 = "SELECT id_barang FROM barang where nama_barang = '"+brng+"' ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
            while(res.next()){
                sup = res.getString("id_suplier");
            }
            while(res1.next()){
                brng = res1.getString("id_barang");
            }
        }catch(SQLException ex){}
        
        try{
            String sql = "DELETE FROM detail_suplier WHERE id_suplier ='"+sup+"' AND id_barang = '"+brng+"'";
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                failMessage me = new failMessage(this, true);
                judulWarn.setText("Terhapus!");
                txtWarn.setText("Detail Paket Berhasil Dihapus");
                me.showAlert();
                tbDtSup();
                cmSup.setSelectedIndex(0);
                cmBrng.setSelectedIndex(0);
                txtKapasitas.setValue(0);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(homeOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homeOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homeOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homeOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homeOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdmTitle;
    private javax.swing.JLabel PktTitle;
    private javax.swing.JButton add;
    private javax.swing.JButton addDetailSup;
    private javax.swing.JButton addIdBrng;
    private javax.swing.JButton addIdSup;
    private javax.swing.JPanel bodyy;
    private javax.swing.JLabel border;
    private javax.swing.JLabel brgTitle;
    private combo_suggestion.ComboBoxSuggestion cmBrng;
    private combo_suggestion.ComboBoxSuggestion cmKemasan;
    private combo_suggestion.ComboBoxSuggestion cmSup;
    private combo_suggestion.ComboBoxSuggestion comboBoxJenis;
    private javax.swing.JPanel content;
    private javax.swing.JButton editBtnKms;
    private javax.swing.JButton editBtnPkt;
    private javax.swing.JButton editBtnSpl;
    private javax.swing.JButton editButtonAdm;
    private javax.swing.JButton editButtonBrg;
    private combo_suggestion.ComboBoxSuggestion hak;
    private javax.swing.JButton hapusBtnKms;
    private javax.swing.JButton hapusBtnPkt;
    private javax.swing.JButton hapusBtnSpl;
    private javax.swing.JButton hapusButtonAdm;
    private javax.swing.JButton hapusButtonBrg;
    private javax.swing.JPanel home;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jawabanTxt;
    private javax.swing.JLabel kmsTitle;
    private javax.swing.JPanel laporanPenj;
    private javax.swing.JPanel master;
    private javax.swing.JPanel masterAdmPanel;
    private javax.swing.JLabel masterAdminBrg;
    private javax.swing.JLabel masterAdminPkt;
    private javax.swing.JLabel masterAdminSpl;
    private javax.swing.JLabel masterBarang;
    private javax.swing.JLabel masterBarangPkt;
    private javax.swing.JLabel masterBarangSpl;
    private javax.swing.JPanel masterBrgPanel;
    private javax.swing.JLabel masterKemasan;
    private javax.swing.JLabel masterKemasanBrg;
    private javax.swing.JLabel masterKemasanPkt;
    private javax.swing.JLabel masterKemasanSpl;
    private javax.swing.JPanel masterKmsPanel;
    private javax.swing.JLabel masterPaket;
    private javax.swing.JLabel masterPaketBrg;
    private javax.swing.JLabel masterPaketSpl;
    private javax.swing.JPanel masterPktPanel;
    private javax.swing.JPanel masterSplPanel;
    private javax.swing.JLabel masterSupplier;
    private javax.swing.JLabel masterSupplierKms;
    private javax.swing.JLabel masterSupplierPkt;
    private javax.swing.JLabel masterSupplierbrg;
    private javax.swing.JPanel menuBarr;
    private javax.swing.JTextField passwordAdmTxt;
    private javax.swing.JPanel pengambilan;
    private javax.swing.JTextField pertanyaanTxt;
    private javax.swing.JButton resetBtnKms;
    private javax.swing.JButton resetBtnPkt;
    private javax.swing.JButton resetBtnSpl;
    private javax.swing.JButton resetButtonAdm;
    private javax.swing.JButton resetButtonBrg;
    private javax.swing.JButton searchBtnAdm;
    private javax.swing.JButton searchBtnBrg;
    private javax.swing.JButton searchBtnKms;
    private javax.swing.JButton searchBtnPkt;
    private javax.swing.JButton searchBtnSpl;
    private javax.swing.JPanel setoranSpl;
    private table.tabel tableAdmin;
    private table.tabel tableBarang;
    private table.tabel tableKemasan;
    private table.tabel tablePaket;
    private table.tabel tableSupplier;
    private javax.swing.JButton tambahBtnKms;
    private javax.swing.JButton tambahBtnPkt;
    private javax.swing.JButton tambahBtnSpl;
    private javax.swing.JButton tambahButtonAdm;
    private javax.swing.JButton tambahButtonBrg;
    private table.tabel tbDt;
    private table.tabel tbRiwayatLog;
    private table.tabel tbRiwayatTr;
    private javax.swing.JPanel transact;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtAlamatsup;
    private javax.swing.JTextField txtBeli;
    private javax.swing.JLabel txtBerlangsung;
    private javax.swing.JTextField txtHargakem;
    private javax.swing.JTextField txtHargapaket;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdkem;
    private javax.swing.JTextField txtIdpaket;
    private javax.swing.JTextField txtIdsup;
    private javax.swing.JTextField txtJeniskem;
    private javax.swing.JTextField txtJual;
    private javax.swing.JTextField txtJumlahpaket;
    private com.toedter.components.JSpinField txtKapasitas;
    private combo_suggestion.ComboBoxSuggestion txtKet;
    private javax.swing.JTextField txtMacampaket;
    private javax.swing.JLabel txtMendatang;
    private javax.swing.JTextField txtMuat;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaPaket;
    private javax.swing.JTextField txtNamasup;
    private javax.swing.JTextField txtRf_id;
    private javax.swing.JTextField txtSearchAdm;
    private javax.swing.JTextField txtSearchBrg;
    private javax.swing.JTextField txtSearchKms;
    private javax.swing.JTextField txtSearchPkt;
    private javax.swing.JTextField txtSearchSpl;
    private javax.swing.JLabel txtSelesai;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTeleponsup;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables
}
