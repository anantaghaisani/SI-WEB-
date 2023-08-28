/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import test.Message;
import static test.Message.judulJoption;
import static test.Message.txtJoption;

/**
 *
 * @author VivoBook
 */
public class pesananMendatangFrame extends javax.swing.JFrame {

    /**
     * Creates new form pesananMendatangFrame
     */
    
    public void tbMainNota(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nota");
        model.addColumn("Customer");
        model.addColumn("Jam");
        model.addColumn("Tanggal Ambil");

        String pilih = cmWaktu.getSelectedItem().toString();

        switch (pilih) {
            case "Besok" :
               try{
            String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.jam, pengambilan.tanggal_pengambilan"
                    + " FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + " WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tbMain.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            break;
            case "Semua" :
                try{
            String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.jam, pengambilan.tanggal_pengambilan"
                    + " FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + " WHERE pengambilan.status_pengambilan = 'belum'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tbMain.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            break;
            default :
        }
        
    }
    
    public void tbMainBarang(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Barang");
        model.addColumn("Total Qty");
        model.addColumn("Kategori");

        String pilih = cmWaktu.getSelectedItem().toString();

        switch (pilih) {
            case "Besok" :
               try{
            String sql = "SELECT detail.id_barang, brng.nama_barang, SUM(detail.qty), brng.keterangan " 
                    +"FROM detail JOIN barang AS brng ON detail.id_barang = brng.id_barang JOIN transaksi " 
                    +"ON transaksi.no_nota = detail.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY " 
                    +"GROUP BY detail.id_barang " 
                    +"UNION " 
                    +"SELECT detail_paket.id_barang, barang.nama_barang, SUM(detail_paket.qty*detail_paket_tr.qty), barang.keterangan " 
                    +"FROM detail_paket JOIN barang ON detail_paket.id_barang = barang.id_barang JOIN detail_paket_tr " 
                    +"ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN transaksi " 
                    +"ON transaksi.no_nota = detail_paket_tr.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY " 
                    +"GROUP BY detail_paket.id_barang ";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tbMain.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            break;
            case "Semua" :
                try{
             String sql = "SELECT detail.id_barang, barang.nama_barang, SUM(detail.qty), barang.keterangan "
                    + "FROM detail JOIN barang ON detail.id_barang = barang.id_barang JOIN transaksi ON transaksi.no_nota = detail.no_nota "
                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota WHERE pengambilan.status_pengambilan = 'belum' "
                    + "GROUP BY detail.id_barang UNION "
                    + "SELECT detail_paket.id_barang, barang.nama_barang, SUM(detail_paket.qty*detail_paket_tr.qty), barang.keterangan "
                    + "FROM detail_paket JOIN barang ON detail_paket.id_barang = barang.id_barang JOIN detail_paket_tr "
                    + "ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota "
                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + "WHERE pengambilan.status_pengambilan = 'belum' GROUP BY detail_paket.id_barang;";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)
                });
            }
            tbMain.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            break;
            default :
        }
    }
    
    public void tbMainSup(){ 
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Suplier");
        model.addColumn("Suplier");
        model.addColumn("Total Qty");
//        model.addColumn("Total Harga");

        String pilih = cmWaktu.getSelectedItem().toString();

        switch (pilih) {
            case "Besok" :
               try{
            String sql = "WITH ttlDt AS ( " 
                    +"SELECT suplier.id_suplier,detail.no_nota, SUM(detail.qty) AS total_qty_dt " 
                    +"FROM detail " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                    +"JOIN transaksi ON detail.no_nota = transaksi.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY " 
                    +"GROUP BY suplier.id_suplier " 
                    +"), " 
                    +"ttlPkt AS ( " 
                    +"SELECT suplier.id_suplier, detail_paket_tr.no_nota, " 
                    +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS total_qty_pkt " 
                    +"FROM detail_paket " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                    +"WHERE pengambilan.status_pengambilan = 'belum' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY " 
                    +"GROUP BY suplier.id_suplier " 
                    +") " 
                    +"SELECT suplier.id_suplier, suplier.suplier, IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.total_qty_dt + ttlPkt.total_qty_pkt,  COALESCE(ttlDt.total_qty_dt,0)+COALESCE(ttlPkt.total_qty_pkt,0)) AS total " 
                    +"FROM suplier " 
                    +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                    +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier "
                    +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                    +"GROUP BY suplier.id_suplier;";
                    
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3)//,res.getString(4)
                        
                });
            }
            tbMain.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            break;
            case "Semua" :
                try{
            String sql = "WITH ttlDt AS ( " 
                    +"SELECT suplier.id_suplier, SUM(detail.qty) AS total_qty_dt " 
                    +"FROM detail " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail.id_suplier "
                    + "JOIN transaksi ON transaksi.no_nota = detail.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + "WHERE pengambilan.status_pengambilan = 'belum' " 
                    +"GROUP BY suplier.id_suplier " 
                    +"), " 
                    +"ttlPkt AS ( " 
                    +"SELECT suplier.id_suplier, " 
                    +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS total_qty_pkt " 
                    +"FROM detail_paket " 
                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                    +"LEFT JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier "
                    + "JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                    + "WHERE pengambilan.status_pengambilan = 'belum' " 
                    +"GROUP BY suplier.id_suplier " 
                    +") " 
                    +"SELECT suplier.id_suplier, suplier.suplier, IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.total_qty_dt + ttlPkt.total_qty_pkt,  COALESCE(ttlDt.total_qty_dt,0)+COALESCE(ttlPkt.total_qty_pkt,0)) AS total " 
                    +"FROM suplier " 
                    +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                    +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier "
                    + "WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                    +"GROUP BY suplier.id_suplier;";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3)//,res.getString(4)
                        //,res.getString(5)
                });
            }
            tbMain.setModel(model);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
            break;
            default :
        }
    }
    
    public void cmSupNota(){
        cmSuplier.removeAllItems();
        cmSuplier.addItem("---Select Suplier---");
        int baris = tbSide.getSelectedRow();
        String id = tbSide.getValueAt(baris, 0).toString();
        
        try{
            String sql = "SELECT suplier.suplier from suplier "
                    + "join detail_suplier ON suplier.id_suplier = detail_suplier.id_suplier "
                    + "JOIN barang ON barang.id_barang = detail_suplier.id_barang "
                    + "WHERE detail_suplier.id_barang = '"+id+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmSuplier.addItem(res.getString("suplier"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void cmSupBarang(){
        cmSuplier.removeAllItems();
        cmSuplier.addItem("---Select Suplier---");
        int baris = tbMain.getSelectedRow();
        String id = tbMain.getValueAt(baris, 0).toString();
        
        try{
            String sql = "SELECT suplier.suplier from suplier "
                    + "join detail_suplier ON suplier.id_suplier = detail_suplier.id_suplier "
                    + "JOIN barang ON barang.id_barang = detail_suplier.id_barang "
                    + "WHERE detail_suplier.id_barang = '"+id+"'";
            java.sql.Connection conn = new config().akses();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet res = pst.executeQuery(sql);
            while(res.next()){
                cmSuplier.addItem(res.getString("suplier"));
                }
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void tbsideNota(){
        String pilihW = cmWaktu.getSelectedItem().toString();
        DefaultTableModel model = new DefaultTableModel();
        switch (pilihW) {
                    
                    case "Besok" :
                         model.addColumn("Id");
                        model.addColumn("Barang");
                        model.addColumn("Qty");
                        model.addColumn("Suplier");

                        int baris = tbMain.getSelectedRow();
                        String id = tbMain.getValueAt(baris, 0).toString();

                        try{
                            String sql = "SELECT detail.id_barang, barang.nama_barang, detail.qty, detail.id_suplier FROM detail "
                            + "JOIN transaksi ON transaksi.no_nota = detail.no_nota JOIN barang ON detail.id_barang = barang.id_barang JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where transaksi.no_nota = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY AND pengambilan.status_pengambilan = 'belum' "
                                    + "UNION SELECT detail_paket.id_barang, barang.nama_barang, detail_paket.qty*detail_paket_tr.qty, detail_paket.id_suplier FROM detail_paket_tr "
                            + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN barang "
                            + "ON detail_paket.id_barang = barang.id_barang JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota"
                            + " where transaksi.no_nota = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY AND pengambilan.status_pengambilan = 'belum' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),res.getString(4)
                        });
                        }
                        tbSide.setModel(model);                                              
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    break;
                    case "Semua" :
                        model.addColumn("Id");
                        model.addColumn("Barang");
                        model.addColumn("Qty");
                        model.addColumn("Suplier");

                        int barisS = tbMain.getSelectedRow();
                        String idS = tbMain.getValueAt(barisS, 0).toString();

                        try{
                            String sql = "SELECT detail.id_barang, barang.nama_barang, detail.qty, detail.id_suplier FROM detail "
                            + "JOIN transaksi ON transaksi.no_nota = detail.no_nota "
                            + "JOIN barang ON detail.id_barang = barang.id_barang JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + "where transaksi.no_nota = '"+idS+"' AND pengambilan.status_pengambilan = 'belum' "
                                    + "UNION SELECT detail_paket.id_barang, barang.nama_barang, detail_paket.qty*detail_paket_tr.qty, detail_paket.id_suplier FROM detail_paket_tr "
                            + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN barang "
                            + "ON detail_paket.id_barang = barang.id_barang JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota "
                            + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where transaksi.no_nota = '"+idS+"' AND pengambilan.status_pengambilan = 'belum' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),res.getString(4)
                        });
                        }
                        tbSide.setModel(model);                                              
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    break;
        }
    }
    
    public void tbsideBarang(){
        String pilihW = cmWaktu.getSelectedItem().toString();
        DefaultTableModel model = new DefaultTableModel();
        switch (pilihW) {
                    case "Besok" :
                        model.addColumn("No Nota");
                        model.addColumn("Barang");
                        model.addColumn("Qty");
                        model.addColumn("Suplier");

                        int baris = tbMain.getSelectedRow();
                        String id = tbMain.getValueAt(baris, 0).toString();

                        try{
                            String sql = "SELECT transaksi.no_nota, barang.nama_barang, detail.qty, detail.id_suplier FROM detail "
                            + "JOIN transaksi ON transaksi.no_nota = detail.no_nota JOIN barang ON detail.id_barang = barang.id_barang JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail.id_barang = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY AND pengambilan.status_pengambilan = 'belum' "
                                    + "UNION SELECT transaksi.no_nota, barang.nama_barang, detail_paket.qty*detail_paket_tr.qty, detail_paket.id_suplier FROM detail_paket_tr "
                            + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota "
                            + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota JOIN barang ON detail_paket.id_barang = barang.id_barang "
                            + " where detail_paket.id_barang = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY AND pengambilan.status_pengambilan = 'belum' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),res.getString(4)
                        });
                    }
                        tbSide.setModel(model);                                              
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, "errrrrr"+e.getMessage());
                        }

                        cmSupBarang();
                    break;
                    case "Semua" :
                        model.addColumn("No Nota");
                        model.addColumn("Barang");
                        model.addColumn("Qty");
                        model.addColumn("Suplier");

                        int barisS = tbMain.getSelectedRow();
                        String idS = tbMain.getValueAt(barisS, 0).toString();

                        try{
                            String sql = "SELECT transaksi.no_nota, barang.nama_barang, detail.qty, detail.id_suplier FROM detail "
                            + "JOIN transaksi ON transaksi.no_nota = detail.no_nota JOIN barang ON detail.id_barang = barang.id_barang "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail.id_barang = '"+idS+"' AND pengambilan.status_pengambilan = 'belum' "
                                    + "UNION SELECT transaksi.no_nota, barang.nama_barang, detail_paket.qty*detail_paket_tr.qty, detail_paket.id_suplier FROM detail_paket_tr "
                            + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN barang "
                            + "ON detail_paket.id_barang = barang.id_barang JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail_paket.id_barang = '"+idS+"' AND pengambilan.status_pengambilan = 'belum' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),res.getString(4)
                        });
                    }
                        tbSide.setModel(model);                                              
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, "errrrrr"+e.getMessage());
                        }

                        cmSupBarang();
                    break;
                }
    }
    
    public void tbsideSup(){
        String pilihW = cmWaktu.getSelectedItem().toString();
        DefaultTableModel model = new DefaultTableModel();
        switch (pilihW) {
                    case "Besok" :
                        model.addColumn("No Nota");
                        model.addColumn("Id Barang");
                        model.addColumn("Barang");
                        model.addColumn("Qty");

                        int baris = tbMain.getSelectedRow();
                        String id = tbMain.getValueAt(baris, 0).toString();

                        try{
                            String sql = "SELECT transaksi.no_nota, detail.id_barang, barang.nama_barang, detail.qty FROM detail "
                            + "JOIN transaksi ON transaksi.no_nota = detail.no_nota JOIN barang ON detail.id_barang = barang.id_barang JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail.id_suplier = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY AND pengambilan.status_pengambilan = 'belum' "
                                    + "UNION SELECT transaksi.no_nota, detail_paket.id_barang, barang.nama_barang, detail_paket.qty*detail_paket_tr.qty FROM detail_paket_tr "
                            + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN barang "
                            + "ON detail_paket.id_barang = barang.id_barang JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail_paket.id_suplier = '"+id+"' AND DATE (pengambilan.tanggal_pengambilan) = CURDATE()+ INTERVAL 1 DAY AND pengambilan.status_pengambilan = 'belum' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),res.getString(4)
                        });
                        }
                        tbSide.setModel(model);                                              
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, "errrrrr"+e.getMessage());
                        }
                    break;
                    case "Semua" :
                        model.addColumn("No Nota");
                        model.addColumn("Id Barang");
                        model.addColumn("Barang");
                        model.addColumn("Qty");

                        int barisS = tbMain.getSelectedRow();
                        String idS = tbMain.getValueAt(barisS, 0).toString();

                        try{
                            String sql = "SELECT transaksi.no_nota, detail.id_barang, barang.nama_barang, detail.qty FROM detail "
                            + "JOIN transaksi ON transaksi.no_nota = detail.no_nota JOIN barang ON detail.id_barang = barang.id_barang "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail.id_suplier = '"+idS+"' AND pengambilan.status_pengambilan = 'belum' "
                                    + "UNION SELECT transaksi.no_nota, detail_paket.id_barang, barang.nama_barang, detail_paket.qty*detail_paket_tr.qty FROM detail_paket_tr "
                            + "JOIN detail_paket ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN barang "
                            + "ON detail_paket.id_barang = barang.id_barang JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " where detail_paket.id_suplier = '"+idS+"' AND pengambilan.status_pengambilan = 'belum' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),res.getString(4)
                        });
                        }
                        tbSide.setModel(model);                                              
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, "errrrrr"+e.getMessage());
                        }
                    break;
                }
    }
    
    public pesananMendatangFrame() {
        initComponents();
        tbMainNota();
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
        kapasitas = new javax.swing.JTextField();
        jmlPesanan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        cmKategori = new combo_suggestion.ComboBoxSuggestion();
        cari = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cmWaktu = new combo_suggestion.ComboBoxSuggestion();
        reset = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMain = new table.tabel();
        cmSuplier = new combo_suggestion.ComboBoxSuggestion();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSide = new table.tabel();
        jLabel14 = new javax.swing.JLabel();
        pilih = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kapasitas.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        kapasitas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(kapasitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 480, 80, 80));

        jmlPesanan.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jmlPesanan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jmlPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 480, 80, 80));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backbtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangledown.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BrowniesTitle.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangleup.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pic.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/army.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/DAFTAR PESANAN 2.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, -1, -1));

        txtCari.setBackground(new java.awt.Color(220, 193, 151));
        txtCari.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCari.setBorder(null);
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 320, 410, 70));

        cmKategori.setBorder(null);
        cmKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nota", "Barang", "Suplier", " " }));
        cmKategori.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cmKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmKategoriActionPerformed(evt);
            }
        });
        jPanel1.add(cmKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 308, 460, 40));

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup big.png"))); // NOI18N
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1700, 340, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field seach big.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 320, -1, -1));

        cmWaktu.setBorder(null);
        cmWaktu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Besok", "Semua" }));
        cmWaktu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cmWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmWaktuActionPerformed(evt);
            }
        });
        jPanel1.add(cmWaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 388, 460, 40));

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RESET BUTTON.png"))); // NOI18N
        reset.setBorder(null);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 320, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field 3col.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field 3col.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/texbox.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 470, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/texbox.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 470, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Kapasitas.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 440, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Total.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 440, -1, -1));

        tbMain.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tbMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMain);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 900, 480));

        cmSuplier.setBorder(null);
        cmSuplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Select Suplier---" }));
        cmSuplier.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cmSuplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmSuplierMouseClicked(evt);
            }
        });
        cmSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmSuplierActionPerformed(evt);
            }
        });
        jPanel1.add(cmSuplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 928, 320, 40));

        tbSide.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSide.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tbSide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSideMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSide);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 600, 820, 290));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxsetoran.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 920, -1, -1));

        pilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pilihspl.png"))); // NOI18N
        pilih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihMouseClicked(evt);
            }
        });
        jPanel1.add(pilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 930, -1, -1));

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

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        String pilihK = cmKategori.getSelectedItem().toString();
        
        switch (pilihK) {
            case "Nota" : 
                tbMainNota();
            break;
            case "Barang" : 
                tbMainBarang();
            break;
            case "Suplier" : 
                tbMainSup();
            break;
            
        }
    }//GEN-LAST:event_resetActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        String pilihK = cmKategori.getSelectedItem().toString();
        
        switch (pilihK) {
            case "Nota" : 
                DefaultTableModel modelN = new DefaultTableModel();
                modelN.addColumn("Nota");
                modelN.addColumn("Customer");
                modelN.addColumn("Jam");
                modelN.addColumn("Tanggal Ambil");
                try{
                    String sql = "SELECT transaksi.no_nota, transaksi.nama_cust, pengambilan.jam, pengambilan.tanggal_pengambilan"
                            + " FROM transaksi JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                            + " WHERE pengambilan.status_pengambilan = 'belum' AND (transaksi.no_nota = '"+txtCari.getText()+"' OR transaksi.nama_cust LIKE '%"+txtCari.getText()+"%') ";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        modelN.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4)
                        });
                    }
                    tbMain.setModel(modelN);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
            case "Barang" : 
                DefaultTableModel modelB = new DefaultTableModel();
                modelB.addColumn("Id");
                modelB.addColumn("Barang");
                modelB.addColumn("Total Qty");
                modelB.addColumn("Kategori");
                try{
                        String sql = "SELECT detail.id_barang, brng.nama_barang, SUM(detail.qty), brng.keterangan " 
                            +"FROM detail JOIN barang AS brng ON detail.id_barang = brng.id_barang JOIN transaksi " 
                            +"ON transaksi.no_nota = detail.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                            +"WHERE pengambilan.status_pengambilan = 'belum' AND brng.nama_barang LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT detail_paket.id_barang, barang.nama_barang, SUM(detail_paket.qty*detail_paket_tr.qty), barang.keterangan " 
                            +"FROM detail_paket JOIN barang ON detail_paket.id_barang = barang.id_barang JOIN detail_paket_tr " 
                            +"ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt JOIN transaksi " 
                            +"ON transaksi.no_nota = detail_paket_tr.no_nota JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                            +"WHERE pengambilan.status_pengambilan = 'belum' AND barang.nama_barang LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY detail_paket.id_barang ";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        modelB.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),res.getString(4)
                        });
                    }
                    tbMain.setModel(modelB);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
            case "Suplier" : 
                DefaultTableModel modelS = new DefaultTableModel();
                modelS.addColumn("Id Suplier");
                modelS.addColumn("Suplier");
                modelS.addColumn("Total Qty");
                try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier,detail.no_nota, SUM(detail.qty) AS total_qty_dt " 
                            +"FROM detail " 
                            +"LEFT JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN transaksi ON detail.no_nota = transaksi.no_nota " 
                            +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                            +"WHERE pengambilan.status_pengambilan = 'belum' AND suplier.suplier LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), " 
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, detail_paket_tr.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS total_qty_pkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"LEFT JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                            +"WHERE pengambilan.status_pengambilan = 'belum' AND suplier.suplier LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT suplier.id_suplier, suplier.suplier, IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.total_qty_dt + ttlPkt.total_qty_pkt,  COALESCE(ttlDt.total_qty_dt,0)+COALESCE(ttlPkt.total_qty_pkt,0)) AS total " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier "
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier;";

                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        modelS.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3)

                        });
                    }
                    tbMain.setModel(modelS);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            break;
            
        }
    }//GEN-LAST:event_cariActionPerformed

    private void pilihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihMouseClicked
        // TODO add your handling code here:
        String pilih = cmKategori.getSelectedItem().toString();

        switch (pilih) {
            case "Nota" :
                int baris = tbSide.getSelectedRow();
                int barisM = tbMain.getSelectedRow();
                String nt = tbMain.getValueAt(barisM, 0).toString();
                String id = tbSide.getValueAt(baris, 0).toString();
                String nmSup = cmSuplier.getSelectedItem().toString();
                String idSup = null;
                String bandingNt = null;
                try{
                String sql = "SELECT keterangan from barang where id_barang = '"+id+"'";
                String sql1 = "SELECT id_suplier from suplier where suplier = '"+nmSup+"'";
                java.sql.Connection conn = new config().akses();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                java.sql.ResultSet res = pst.executeQuery(sql);
                java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                java.sql.ResultSet res1 = pst1.executeQuery(sql1);
                    while(res.next()){
                        bandingNt = res.getString("keterangan");
                        }
                    while(res1.next()){
                        idSup = res1.getString("id_suplier");
                        }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

                if (bandingNt.equals("normal")) {
                    try {
                        String sql = "UPDATE detail SET id_suplier = '"+idSup+"' WHERE no_nota = '"+nt+"' AND id_barang = '"+id+"'";

                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);

                        pst.executeUpdate();
                        pst.close();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }else if (bandingNt.equals("paket")) {
                    try {
                        String sql = "UPDATE `detail_paket` JOIN detail_paket_tr ON detail_paket.identitas_pkt = detail_paket_tr.identitas_pkt "
                                + "SET detail_paket.id_suplier = '"+idSup+"' WHERE detail_paket_tr.no_nota = '"+nt+"' "
                                + "AND detail_paket.id_barang = '"+id+"'";

                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);

                        pst.executeUpdate();
                        pst.close();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
                tbsideNota();
            break;
            case "Barang" :
                int barisMB = tbMain.getSelectedRow();
                String idB = tbMain.getValueAt(barisMB, 0).toString();
                
                String nmSupB = cmSuplier.getSelectedItem().toString();
                String idSupB = null;
                String bandingNtB = null;
                
                    String kapasitas = null;
                    try{
                        String sql = "SELECT keterangan from barang where id_barang = '"+idB+"'";
                        String sql1 = "SELECT id_suplier from suplier where suplier = '"+nmSupB+"'";
                        String sql2 = "SELECT detail_suplier.stok_barang from detail_suplier JOIN suplier ON suplier.id_suplier = detail_suplier.id_suplier where suplier.suplier = '"+nmSupB+"' AND detail_suplier.id_barang = '"+idB+"'";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                        java.sql.ResultSet res1 = pst1.executeQuery(sql1);
                        java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
                        java.sql.ResultSet res2 = pst2.executeQuery(sql2);
                            while(res.next()){
                                bandingNtB = res.getString("keterangan");
                                }
                            while(res1.next()){
                                idSupB = res1.getString("id_suplier");
                                }
                            while(res2.next()){
                                kapasitas = res2.getString("stok_barang");
                                }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    
                    for (int brs = 0; brs < tbSide.getRowCount(); brs++) {
                        
                        String nota = tbSide.getValueAt(brs, 0).toString();
                        String qty = tbSide.getValueAt(brs, 2).toString();
                        Object cekSup = tbSide.getValueAt(brs, 3);
                        String jml = null;
                        if(cekSup == null){
                        if(bandingNtB.equals("normal")){
                        try{
                            String sql = "SELECT COALESCE(SUM(detail.qty),0) AS jml FROM detail "
                                    + "JOIN suplier ON suplier.id_suplier = detail.id_suplier JOIN detail_suplier "
                                    + "ON suplier.id_suplier = detail_suplier.id_suplier JOIN transaksi ON transaksi.no_nota = detail.no_nota "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                                    + "WHERE pengambilan.tanggal_pengambilan = CURDATE() + INTERVAL 1 DAY " 
                                    + "AND pengambilan.status_pengambilan = 'belum' AND detail.id_barang = '"+idB+"' AND suplier.suplier = '"+nmSupB+"' AND "
                                    + "detail_suplier.id_barang = '"+idB+"' AND suplier.suplier = '"+nmSupB+"'";
                            java.sql.Connection conn = new config().akses();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                            java.sql.ResultSet res = pst.executeQuery(sql);
                            while(res.next()){
                                jml = res.getString("jml");
                                }

                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }else if(bandingNtB.equals("paket")){
                        try{
                            String sql = "SELECT " 
                                    +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0) AS jml " 
                                    +"FROM detail_paket " 
                                    +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                                    +"JOIN detail_suplier ON suplier.id_suplier = detail_suplier.id_suplier " 
                                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                                    +"WHERE pengambilan.tanggal_pengambilan = CURDATE() + INTERVAL 1 DAY " 
                                    +"AND pengambilan.status_pengambilan = 'belum' " 
                                    +"AND detail_paket.id_barang = '"+idB+"' " 
                                    +"AND suplier.suplier = '"+nmSupB+"' " 
                                    +"AND detail_suplier.id_barang = '"+idB+"' " 
                                    +"AND suplier.suplier = '"+nmSupB+"' ";
                            java.sql.Connection conn = new config().akses();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                            java.sql.ResultSet res = pst.executeQuery(sql);
                            while(res.next()){
                                jml = res.getString("jml");
                                }

                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }
                        int jml1 = Integer.valueOf(jml);
                        int qty1 = Integer.valueOf(qty);
                        int kap1 = Integer.valueOf(kapasitas);
                        if(jml1+qty1 <= kap1){
                        if (bandingNtB.equals("normal")) {
                            try {
                                String sql = "UPDATE detail SET id_suplier = '"+idSupB+"' WHERE no_nota = '"+nota+"' AND id_barang = '"+idB+"'";

                                java.sql.Connection conn = new config().akses();
                                java.sql.PreparedStatement pst = conn.prepareStatement(sql);

                                //JOptionPane.showMessageDialog(null, "barang suplier disimpan");
                                Message me = new Message(this, true);
        judulJoption.setText("Supplier Terpilih");
        txtJoption.setText("Barang supplier berhasil disimpan.");
        me.showAlert();
                                pst.executeUpdate();
                                pst.close();

                            }catch (Exception e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                            }
                        }else if (bandingNtB.equals("paket")) {
                            try {
                                String sql = "UPDATE `detail_paket` JOIN detail_paket_tr ON detail_paket.identitas_pkt = detail_paket_tr.identitas_pkt "
                                        + "SET detail_paket.id_suplier = '"+idSupB+"' WHERE detail_paket_tr.no_nota = '"+nota+"' "
                                        + "AND detail_paket.id_barang = '"+idB+"'";

                                java.sql.Connection conn = new config().akses();
                                java.sql.PreparedStatement pst = conn.prepareStatement(sql);

                                //JOptionPane.showMessageDialog(null, "barang suplier disimpan");
                                Message me = new Message(this, true);
        judulJoption.setText("Supplier Terpilih");
        txtJoption.setText("Barang supplier berhasil disimpan.");
        me.showAlert();
                                pst.executeUpdate();
                                pst.close();

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }
                        }
                    }
                    tbsideBarang();
                        }
                }
            break;
            }
    }//GEN-LAST:event_pilihMouseClicked

    private void tbMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMainMouseClicked
        // TODO add your handling code here:
        String pilih = cmKategori.getSelectedItem().toString();
        
        switch (pilih) {
            case "Nota" :
               tbsideNota();
            break;
            case "Barang" :
                tbsideBarang();
                kapasitas.setText("");
                jmlPesanan.setText("");
            break;
            case "Suplier" :
                tbsideSup();
            break;
        }
    }//GEN-LAST:event_tbMainMouseClicked

    private void cmSuplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmSuplierMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmSuplierMouseClicked

    private void tbSideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSideMouseClicked
        // TODO add your handling code here:
        String pilihK = cmKategori.getSelectedItem().toString();

        switch (pilihK) {
            case "Nota" : 
                cmSupNota();
                kapasitas.setText("");
                jmlPesanan.setText("");
            break;
        }
    }//GEN-LAST:event_tbSideMouseClicked

    private void cmSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmSuplierActionPerformed
        // TODO add your handling code here:
        String pilih = cmKategori.getSelectedItem().toString();
        
        switch (pilih) {
            case "Nota" :
                int baris = tbSide.getSelectedRow();
                String id = tbSide.getValueAt(baris, 0).toString();
                Object supli = cmSuplier.getSelectedItem();
                if(supli != null){
                    String sup = supli.toString();
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
                            String sql = "SELECT COALESCE(SUM(detail.qty),0) AS jml FROM detail "
                                    + "JOIN suplier ON suplier.id_suplier = detail.id_suplier JOIN detail_suplier "
                                    + "ON suplier.id_suplier = detail_suplier.id_suplier JOIN transaksi ON transaksi.no_nota = detail.no_nota "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                                    + "WHERE pengambilan.tanggal_pengambilan = CURDATE() + INTERVAL 1 DAY " 
                                    + "AND pengambilan.status_pengambilan = 'belum' AND suplier.suplier = '"+sup+"' AND "
                                    + "detail_suplier.id_barang = '"+id+"' AND detail.id_barang = '"+id+"' ";
                            String sql1 = "SELECT stok_barang FROM detail_suplier "
                                    + "JOIN suplier ON suplier.id_suplier = detail_suplier.id_suplier "
                                    + "WHERE suplier.suplier = '"+sup+"' AND detail_suplier.id_barang = '"+id+"' ";
                            java.sql.Connection conn = new config().akses();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                            java.sql.ResultSet res = pst.executeQuery(sql);
                            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
                            while(res.next()){
                                jmlPesanan.setText(res.getString("jml"));
                                }
                            while(res1.next()){
                                kapasitas.setText(res1.getString("stok_barang"));
                                }

                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }else if(perbandingan.equals("paket")){
                        try{
                            String sql = "SELECT " 
                                    +"SUM(detail_paket.qty*detail_paket_tr.qty) AS jml " 
                                    +"FROM detail_paket " 
                                    +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                                    +"JOIN detail_suplier ON suplier.id_suplier = detail_suplier.id_suplier " 
                                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                                    +"WHERE pengambilan.tanggal_pengambilan = CURDATE() + INTERVAL 1 DAY  " 
                                    +"AND pengambilan.status_pengambilan = 'belum' " 
                                    +"AND detail_paket.id_barang = '"+id+"' " 
                                    +"AND suplier.suplier = '"+sup+"' " 
                                    +"AND detail_suplier.id_barang = '"+id+"' ";
                            String sql1 = "SELECT stok_barang FROM detail_suplier "
                                    + "JOIN suplier ON suplier.id_suplier = detail_suplier.id_suplier "
                                    + "WHERE suplier.suplier = '"+sup+"' AND detail_suplier.id_barang = '"+id+"' ";
                            java.sql.Connection conn = new config().akses();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                            java.sql.ResultSet res = pst.executeQuery(sql);
                            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
                            while(res.next()){
                                jmlPesanan.setText(res.getString("jml"));
                                }
                            while(res1.next()){
                                kapasitas.setText(res1.getString("stok_barang"));
                                }

                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }
                }
            break;
            case "Barang" :
                int barisB = tbMain.getSelectedRow();
                String idB = tbMain.getValueAt(barisB, 0).toString();
                Object supliB = cmSuplier.getSelectedItem();
                if(supliB != null){
                    String sup = supliB.toString();
                String perbandingan = null;
                    try{
                        String sql = "SELECT keterangan FROM barang WHERE id_barang = '"+idB+"' ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            perbandingan = res.getString("keterangan");
                            }

                    }catch(Exception e){}
                    if(perbandingan.equals("normal")){
                        try{
                            String sql = "SELECT COALESCE(SUM(detail.qty),0) AS jml FROM detail "
                                    + "JOIN suplier ON suplier.id_suplier = detail.id_suplier JOIN detail_suplier "
                                    + "ON suplier.id_suplier = detail_suplier.id_suplier JOIN transaksi ON transaksi.no_nota = detail.no_nota "
                                    + "JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota "
                                    + "WHERE pengambilan.tanggal_pengambilan = CURDATE() + INTERVAL 1 DAY " 
                                    + "AND pengambilan.status_pengambilan = 'belum' AND suplier.suplier = '"+sup+"' AND "
                                    + "detail_suplier.id_barang = '"+idB+"' AND detail.id_barang = '"+idB+"' ";
                            String sql1 = "SELECT stok_barang FROM detail_suplier "
                                    + "JOIN suplier ON suplier.id_suplier = detail_suplier.id_suplier "
                                    + "WHERE suplier.suplier = '"+sup+"' AND detail_suplier.id_barang = '"+idB+"' ";
                            java.sql.Connection conn = new config().akses();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                            java.sql.ResultSet res = pst.executeQuery(sql);
                            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
                            while(res.next()){
                                jmlPesanan.setText(res.getString("jml"));
                                }
                            while(res1.next()){
                                kapasitas.setText(res1.getString("stok_barang"));
                                }

                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }else if(perbandingan.equals("paket")){
                        try{
                            String sql = "SELECT " 
                                    +"SUM(detail_paket.qty*detail_paket_tr.qty) AS jml " 
                                    +"FROM detail_paket " 
                                    +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                                    +"JOIN detail_suplier ON suplier.id_suplier = detail_suplier.id_suplier " 
                                    +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                                    +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                                    +"JOIN pengambilan ON transaksi.no_nota = pengambilan.no_nota " 
                                    +"WHERE pengambilan.tanggal_pengambilan = CURDATE() + INTERVAL 1 DAY  " 
                                    +"AND pengambilan.status_pengambilan = 'belum' " 
                                    +"AND detail_paket.id_barang = '"+idB+"' " 
                                    +"AND suplier.suplier = '"+sup+"' " 
                                    +"AND detail_suplier.id_barang = '"+idB+"' ";
                            String sql1 = "SELECT stok_barang FROM detail_suplier "
                                    + "JOIN suplier ON suplier.id_suplier = detail_suplier.id_suplier "
                                    + "WHERE suplier.suplier = '"+sup+"' AND detail_suplier.id_barang = '"+idB+"' ";
                            java.sql.Connection conn = new config().akses();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                            java.sql.ResultSet res = pst.executeQuery(sql);
                            java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                            java.sql.ResultSet res1 = pst1.executeQuery(sql1);
                            while(res.next()){
                                jmlPesanan.setText(res.getString("jml"));
                                }
                            while(res1.next()){
                                kapasitas.setText(res1.getString("stok_barang"));
                                }

                        }catch(Exception e){
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                    }
                }
            break;
        }
    }//GEN-LAST:event_cmSuplierActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new homeOwner().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmKategoriActionPerformed
        // TODO add your handling code here:
        String pilihK = cmKategori.getSelectedItem().toString();
        
        switch (pilihK) {
            case "Nota" : 
                tbMainNota();
            break;
            case "Barang" : 
                tbMainBarang();
            break;
            case "Suplier" : 
                tbMainSup();
            break;
            
        }
    }//GEN-LAST:event_cmKategoriActionPerformed

    private void cmWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmWaktuActionPerformed
        // TODO add your handling code here:
        String pilihK = cmKategori.getSelectedItem().toString();
        
        switch (pilihK) {
            case "Nota" : 
                tbMainNota();
            break;
            case "Barang" : 
                tbMainBarang();
            break;
            case "Suplier" : 
                tbMainSup();
            break;
            
        }
    }//GEN-LAST:event_cmWaktuActionPerformed

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
            java.util.logging.Logger.getLogger(pesananMendatangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pesananMendatangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pesananMendatangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pesananMendatangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pesananMendatangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cari;
    private combo_suggestion.ComboBoxSuggestion cmKategori;
    private combo_suggestion.ComboBoxSuggestion cmSuplier;
    private combo_suggestion.ComboBoxSuggestion cmWaktu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField jmlPesanan;
    private javax.swing.JTextField kapasitas;
    private javax.swing.JLabel pilih;
    private javax.swing.JButton reset;
    private table.tabel tbMain;
    private table.tabel tbSide;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
