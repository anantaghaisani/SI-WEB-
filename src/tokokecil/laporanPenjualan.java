/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author VivoBook
 */
public class laporanPenjualan extends javax.swing.JFrame {

    /**
     * Creates new form laporanPenjualan
     */
    
    public void tbMainNota(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No Nota");
        model.addColumn("Customer");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Tanggal Ambil");
        model.addColumn("Qty");
        model.addColumn("Pemasukan");
        model.addColumn("Setoran");
        model.addColumn("Keuntungan");
        
        Date date1 = tgl.getDate();
        
        String waktu = cmFilter.getSelectedItem().toString();
        switch(waktu){
            case "Harian" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = '"+tgl1+"' " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                tgl.setDate(null);
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = CURDATE() " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Mingguan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) BETWEEN '"+tgl1+"' AND DATE_ADD('"+tgl1+"', INTERVAL 7 DAY) " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEARWEEK(transaksi.tgl_transaksi) = YEARWEEK(CURDATE()) " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Bulanan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                    String[] parts = tgl1.split("-");
                    String tahun = parts[0];
                    String bulan = parts[1];
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' AND MONTH(transaksi.tgl_transaksi) = '"+bulan+"' " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) AND MONTH(transaksi.tgl_transaksi) = MONTH(CURDATE()) " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Tahunan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                    String[] parts = tgl1.split("-");
                    String tahun = parts[0];
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
        } 
    }
    
    public void tbMainBarang(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Qty");
        model.addColumn("Pemasukan");
        model.addColumn("Setoran");
        model.addColumn("Keuntungan");
        
        Date date1 = tgl.getDate();
        String waktu = cmFilter.getSelectedItem().toString();
        switch(waktu){
            case "Harian" :
                if(date1 != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String tgl1 = dateFormat.format(date1);
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = '"+tgl1+"' " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = '"+tgl1+"' " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = CURDATE() " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = CURDATE() " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            
            case "Mingguan" :
                if(date1 != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String tgl1 = dateFormat.format(date1);
                
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) BETWEEN '"+tgl1+"' AND DATE_ADD('"+tgl1+"', INTERVAL 7 DAY) " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) BETWEEN '"+tgl1+"' AND DATE_ADD('"+tgl1+"', INTERVAL 7 DAY) " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEARWEEK(transaksi.tgl_transaksi) = YEARWEEK(CURDATE()) " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEARWEEK(transaksi.tgl_transaksi) = YEARWEEK(CURDATE()) " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            
            case "Bulanan" :
                if(date1 != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String tgl1 = dateFormat.format(date1);
                String[] parts = tgl1.split("-");
                String tahun = parts[0];
                String bulan = parts[1];
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' AND MONTH(transaksi.tgl_transaksi) = '"+bulan+"' " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' AND MONTH(transaksi.tgl_transaksi) = '"+bulan+"' " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) AND MONTH(transaksi.tgl_transaksi) = MONTH(CURDATE()) " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) AND MONTH(transaksi.tgl_transaksi) = MONTH(CURDATE()) " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Tahunan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                    String[] parts = tgl1.split("-");
                    String tahun = parts[0];
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
        }
    }
    
    public void tbMainSup(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Suplier");
        model.addColumn("Nama Suplier");
        model.addColumn("Qty");
        model.addColumn("Pemasukan");
        model.addColumn("Setoran");
        model.addColumn("Keuntungan");
        
        Date date1 = tgl.getDate();
        String waktu = cmFilter.getSelectedItem().toString();
        switch(waktu){
            case "Harian" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = '"+tgl1+"' " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = '"+tgl1+"' " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = CURDATE() " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) = CURDATE() " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Mingguan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) BETWEEN '"+tgl1+"' AND DATE_ADD('"+tgl1+"', INTERVAL 7 DAY) " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND DATE(transaksi.tgl_transaksi) BETWEEN '"+tgl1+"' AND DATE_ADD('"+tgl1+"', INTERVAL 7 DAY) " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEARWEEK(transaksi.tgl_transaksi) = YEARWEEK(CURDATE()) " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEARWEEK(transaksi.tgl_transaksi) = YEARWEEK(CURDATE()) " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Bulanan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                    String[] parts = tgl1.split("-");
                    String tahun = parts[0];
                    String bulan = parts[1];
                try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' AND MONTH(transaksi.tgl_transaksi) = '"+bulan+"' " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' AND MONTH(transaksi.tgl_transaksi) = '"+bulan+"' " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) AND MONTH(transaksi.tgl_transaksi) = MONTH(CURDATE()) " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) AND MONTH(transaksi.tgl_transaksi) = MONTH(CURDATE()) " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
            case "Tahunan" :
                if(date1 != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl1 = dateFormat.format(date1);
                    String[] parts = tgl1.split("-");
                    String tahun = parts[0];
                try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = '"+tahun+"' " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }else{
                    try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND YEAR(transaksi.tgl_transaksi) = YEAR(CURDATE()) " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            model.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                }
            break;
        }
    }
    
    public void txtTotalnya(){
        String cmBox = cmKategori.getSelectedItem().toString();
        switch(cmBox){
            case "Nota" :
                int rowCount = tbMain.getRowCount();
                int totalQty = 0;
                int totalPemasukan = 0;
                int totalSetoran = 0;
                int totalKeuntungan = 0;
                int columnQty = 4; 
                int columnPemasukan = 5; 
                int columnSetoran = 6; 
                int columnKeuntungan = 7; 
                for (int row = 0; row < rowCount; row++) {
                    int valueQty = Integer.parseInt(tbMain.getValueAt(row, columnQty).toString());
                    int valuePemasukan = Integer.parseInt(tbMain.getValueAt(row, columnPemasukan).toString());
                    int valueSetoran = Integer.parseInt(tbMain.getValueAt(row, columnSetoran).toString());
                    int valueKeuntungan = Integer.parseInt(tbMain.getValueAt(row, columnKeuntungan).toString());
                    totalQty += valueQty;
                    totalPemasukan += valuePemasukan;
                    totalSetoran += valueSetoran;
                    totalKeuntungan += valueKeuntungan;
                }
                txtTtlQty.setText(String.valueOf(totalQty));
                txtPemasukan.setText(String.valueOf(totalPemasukan));
                txtSetoran.setText(String.valueOf(totalSetoran));
                txtKeuntungan.setText(String.valueOf(totalKeuntungan));
            break;
            case "Barang" :
                int rowCountB = tbMain.getRowCount();
                int totalQtyB = 0;
                int totalPemasukanB = 0;
                int totalSetoranB = 0;
                int totalKeuntunganB = 0;
                int columnQtyB = 2; 
                int columnPemasukanB = 3; 
                int columnSetoranB = 4; 
                int columnKeuntunganB = 5; 
                for (int row = 0; row < rowCountB; row++) {
                    int valueQty = Integer.parseInt(tbMain.getValueAt(row, columnQtyB).toString());
                    int valuePemasukan = Integer.parseInt(tbMain.getValueAt(row, columnPemasukanB).toString());
                    int valueSetoran = Integer.parseInt(tbMain.getValueAt(row, columnSetoranB).toString());
                    int valueKeuntungan = Integer.parseInt(tbMain.getValueAt(row, columnKeuntunganB).toString());
                    totalQtyB += valueQty;
                    totalPemasukanB += valuePemasukan;
                    totalSetoranB += valueSetoran;
                    totalKeuntunganB += valueKeuntungan;
                }
                txtTtlQty.setText(String.valueOf(totalQtyB));
                txtPemasukan.setText(String.valueOf(totalPemasukanB));
                txtSetoran.setText(String.valueOf(totalSetoranB));
                txtKeuntungan.setText(String.valueOf(totalKeuntunganB));
            break;
            case "Suplier" :
                int rowCountS = tbMain.getRowCount();
                int totalQtyS = 0;
                int totalPemasukanS = 0;
                int totalSetoranS = 0;
                int totalKeuntunganS = 0;
                int columnQtyS = 2; 
                int columnPemasukanS = 3; 
                int columnSetoranS = 4; 
                int columnKeuntunganS = 5; 
                for (int row = 0; row < rowCountS; row++) {
                    int valueQty = Integer.parseInt(tbMain.getValueAt(row, columnQtyS).toString());
                    int valuePemasukan = Integer.parseInt(tbMain.getValueAt(row, columnPemasukanS).toString());
                    int valueSetoran = Integer.parseInt(tbMain.getValueAt(row, columnSetoranS).toString());
                    int valueKeuntungan = Integer.parseInt(tbMain.getValueAt(row, columnKeuntunganS).toString());
                    totalQtyS += valueQty;
                    totalPemasukanS += valuePemasukan;
                    totalSetoranS += valueSetoran;
                    totalKeuntunganS += valueKeuntungan;
                }
                txtTtlQty.setText(String.valueOf(totalQtyS));
                txtPemasukan.setText(String.valueOf(totalPemasukanS));
                txtSetoran.setText(String.valueOf(totalSetoranS));
                txtKeuntungan.setText(String.valueOf(totalKeuntunganS));
                break;
            }
        
        
    }
    
    public laporanPenjualan() {
        initComponents();
        tbMainNota();
        txtTotalnya();
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
        cmFilter = new combo_suggestion.ComboBoxSuggestion();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtKeuntungan = new javax.swing.JTextField();
        txtTtlQty = new javax.swing.JTextField();
        txtPemasukan = new javax.swing.JTextField();
        txtSetoran = new javax.swing.JTextField();
        cmKategori = new combo_suggestion.ComboBoxSuggestion();
        tgl = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMain = new table.tabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(237, 224, 209));

        jPanel1.setBackground(new java.awt.Color(237, 224, 209));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmFilter.setBorder(null);
        cmFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Harian", "Mingguan", "Bulanan", "Tahunan", " ", " " }));
        cmFilter.setToolTipText("");
        cmFilter.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cmFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmFilterActionPerformed(evt);
            }
        });
        jPanel1.add(cmFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 460, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/BrowniesTitle.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backbtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        txtKeuntungan.setBackground(new java.awt.Color(220, 193, 151));
        txtKeuntungan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtKeuntungan.setBorder(null);
        txtKeuntungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeuntunganActionPerformed(evt);
            }
        });
        jPanel1.add(txtKeuntungan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 910, 320, 50));

        txtTtlQty.setBackground(new java.awt.Color(220, 193, 151));
        txtTtlQty.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTtlQty.setBorder(null);
        txtTtlQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTtlQtyActionPerformed(evt);
            }
        });
        jPanel1.add(txtTtlQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 910, 320, 50));

        txtPemasukan.setBackground(new java.awt.Color(220, 193, 151));
        txtPemasukan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtPemasukan.setBorder(null);
        txtPemasukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPemasukanActionPerformed(evt);
            }
        });
        jPanel1.add(txtPemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 910, 320, 50));

        txtSetoran.setBackground(new java.awt.Color(220, 193, 151));
        txtSetoran.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtSetoran.setBorder(null);
        txtSetoran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSetoranActionPerformed(evt);
            }
        });
        jPanel1.add(txtSetoran, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 910, 320, 50));

        cmKategori.setBorder(null);
        cmKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nota", "Barang", "Suplier", " " }));
        cmKategori.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cmKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmKategoriActionPerformed(evt);
            }
        });
        jPanel1.add(cmKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 367, 460, 40));

        tgl.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 278, 330, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangledown.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Triangleup.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pic.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/army.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LAPORAN PENJUALAN.png"))); // NOI18N
        jLabel6.setToolTipText("");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field 3col.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 359, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field layer.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 910, -1, -1));

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RESET BUTTON.png"))); // NOI18N
        reset.setBorder(null);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 320, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field layer.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 910, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field layer.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 910, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field layer.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 910, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Total Qty.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 880, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Total Setoran.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 880, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Total Pemasukan.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 880, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Total Keuntungan.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 880, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field 3col.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        txtCari.setBackground(new java.awt.Color(220, 193, 151));
        txtCari.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtCari.setBorder(null);
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 320, 410, 70));

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lup big.png"))); // NOI18N
        btCari.setBorder(null);
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1740, 330, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/text field seach big.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 320, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cbboxsetoran.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, -1, -1));

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
        jScrollPane2.setViewportView(tbMain);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 1610, 330));

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
        this.dispose();
        new homeOwner().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        cmFilter.setSelectedIndex(0);
    }//GEN-LAST:event_resetActionPerformed

    private void txtKeuntunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeuntunganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeuntunganActionPerformed

    private void txtTtlQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTtlQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTtlQtyActionPerformed

    private void txtPemasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPemasukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPemasukanActionPerformed

    private void txtSetoranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSetoranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSetoranActionPerformed

    private void cmFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmFilterActionPerformed
        // TODO add your handling code here:
        String pilih = cmKategori.getSelectedItem().toString();
        switch(pilih){
            case "Nota" :
                tbMainNota();
                txtTotalnya();
            break;
            case "Barang" :
                tbMainBarang();
                txtTotalnya();
            break;
            case "Suplier" :
                tbMainSup();
                txtTotalnya();
            break;
        }
    }//GEN-LAST:event_cmFilterActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        // TODO add your handling code here:
        String pilih = cmKategori.getSelectedItem().toString();
        
        switch(pilih){
            case "Nota" :
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("No Nota");
                model.addColumn("Customer");
                model.addColumn("Tanggal Transaksi");
                model.addColumn("Tanggal Ambil");
                model.addColumn("Qty");
                model.addColumn("Pemasukan");
                model.addColumn("Setoran");
                model.addColumn("Keuntungan");
                
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT transaksi.no_nota, " 
                            +"COALESCE(SUM(detail_paket.qty),0)* COALESCE(detail_paket_tr.qty, 0) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY history_pengambilan.no_nota " 
                            +") " 
                            +"SELECT " 
                            +"transaksi.no_nota, " 
                            +"transaksi.nama_cust, " 
                            +"transaksi.tgl_transaksi, " 
                            +"history_pengambilan.tanggal_diambil, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0), COALESCE(dt.qty_dt,0) + COALESCE(pkt.qty_pkt,0)) AS totalQty, " 
                            +"transaksi.grand_total, " 
                            +"IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS totalSetoran, " 
                            +"transaksi.grand_total - IF(dt.no_nota = pkt.no_nota, COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0), COALESCE(dt.stDt,0) + COALESCE(pkt.stPkt,0)) AS keuntungan " 
                            +"FROM transaksi " 
                            +"LEFT JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"LEFT JOIN dt ON transaksi.no_nota = dt.no_nota " 
                            +"LEFT JOIN pkt ON transaksi.no_nota = pkt.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND (transaksi.no_nota = '"+txtCari.getText()+"' OR transaksi.nama_cust LIKE '%"+txtCari.getText()+"%') " 
                            +"GROUP BY history_pengambilan.no_nota;";
                    java.sql.Connection conn = new config().akses();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    java.sql.ResultSet res = pst.executeQuery(sql);
                    while(res.next()){
                        model.addRow(new Object[]{res.getString(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),
                            res.getString(6),res.getString(7),
                            res.getString(8)
                        });
                    }
                    tbMain.setModel(model);
                }catch(SQLException ex){

                }
                txtTotalnya();
            break;
            case "Barang" :
                DefaultTableModel modelB = new DefaultTableModel();
                modelB.addColumn("Id Barang");
                modelB.addColumn("Nama Barang");
                modelB.addColumn("Qty");
                modelB.addColumn("Pemasukan");
                modelB.addColumn("Setoran");
                modelB.addColumn("Keuntungan");
                try{
                    String sql = "WITH dt AS ( " 
                            +"SELECT detail.id_barang, " 
                            +"COALESCE(SUM(detail.qty),0) AS qty_dt, " 
                            +"COALESCE(SUM(detail.total),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' "
                            +"GROUP BY detail.id_barang " 
                            +"), " 
                            +"pkt AS ( " 
                            +"SELECT detail_paket.id_barang, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' " 
                            +"GROUP BY detail_paket.id_barang " 
                            +") " 
                            +"SELECT " 
                            +"detail.id_barang, barang.nama_barang, dt.qty_dt, dt.pemasukan, dt.stDt, COALESCE(dt.pemasukan-dt.stDt,0) AS keuntungan " 
                            +"FROM detail " 
                            +"LEFT JOIN barang ON detail.id_barang = barang.id_barang " 
                            +"LEFT JOIN dt ON dt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND barang.nama_barang LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY detail.id_barang " 
                            +"UNION " 
                            +"SELECT " 
                            +"detail_paket.id_barang, barang.nama_barang, pkt.qty_pkt, pkt.pemasukan, pkt.stPkt, COALESCE(pkt.pemasukan-pkt.stPkt,0) AS keuntungan " 
                            +"FROM detail_paket " 
                            +"LEFT JOIN barang ON detail_paket.id_barang = barang.id_barang " 
                            +"LEFT JOIN pkt ON pkt.id_barang = detail_paket.id_barang " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND barang.nama_barang LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY detail_paket.id_barang ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            modelB.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(modelB);
                }catch(SQLException ex){

                }
                txtTotalnya();
            break;
            case "Suplier" :
                DefaultTableModel modelS = new DefaultTableModel();
                modelS.addColumn("Id Suplier");
                modelS.addColumn("Nama Suplier");
                modelS.addColumn("Qty");
                modelS.addColumn("Pemasukan");
                modelS.addColumn("Setoran");
                modelS.addColumn("Keuntungan");
                
                try{
                    String sql = "WITH ttlDt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(detail.qty) AS qty_dt, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_jual),0) AS pemasukan, " 
                            +"COALESCE(SUM(detail.qty*brngDt.harga_beli),0) AS stDt " 
                            +"FROM detail " 
                            +"JOIN suplier ON suplier.id_suplier = detail.id_suplier " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail.id_barang " 
                            +"JOIN transaksi ON transaksi.no_nota = detail.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND suplier.suplier LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY suplier.id_suplier " 
                            +"), "
                            +"ttlPkt AS ( " 
                            +"SELECT suplier.id_suplier, " 
                            +"SUM(COALESCE(detail_paket.qty)* COALESCE(detail_paket_tr.qty, 0)) AS qty_pkt, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_jual AS pemasukan, " 
                            +"COALESCE(SUM(detail_paket.qty*detail_paket_tr.qty),0)*brngDt.harga_beli AS stPkt " 
                            +"FROM detail_paket " 
                            +"JOIN detail_paket_tr ON detail_paket_tr.identitas_pkt = detail_paket.identitas_pkt " 
                            +"JOIN barang AS brngDt ON brngDt.id_barang = detail_paket.id_barang " 
                            +"JOIN suplier ON suplier.id_suplier = detail_paket.id_suplier " 
                            +"JOIN transaksi ON transaksi.no_nota = detail_paket_tr.no_nota " 
                            +"JOIN history_pengambilan ON transaksi.no_nota = history_pengambilan.no_nota " 
                            +"WHERE history_pengambilan.status_pengambilan = 'diambil' AND suplier.suplier LIKE '%"+txtCari.getText()+"%' " 
                            +"GROUP BY suplier.id_suplier " 
                            +") " 
                            +"SELECT "
                            +"suplier.id_suplier, " 
                            +"suplier.suplier, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier,  ttlDt.qty_dt + ttlPkt.qty_pkt,  COALESCE(ttlDt.qty_dt,0)+COALESCE(ttlPkt.qty_pkt,0)) AS qty, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) AS pemasukan, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS setoran, " 
                            +"IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.pemasukan + ttlPkt.pemasukan, COALESCE(ttlDt.pemasukan,0)+COALESCE(ttlPkt.pemasukan,0)) - IF(ttlDt.id_suplier = ttlPkt.id_suplier, ttlDt.stDt + ttlPkt.stPkt, COALESCE(ttlDt.stDt,0)+COALESCE(ttlPkt.stPkt,0)) AS keuntungan " 
                            +"FROM suplier " 
                            +"LEFT JOIN ttlDt ON suplier.id_suplier = ttlDt.id_suplier " 
                            +"LEFT JOIN ttlPkt ON suplier.id_suplier = ttlPkt.id_suplier " 
                            +"WHERE ttlDt.id_suplier IS NOT NULL OR ttlPkt.id_suplier IS NOT NULL " 
                            +"GROUP BY suplier.id_suplier; ";
                        java.sql.Connection conn = new config().akses();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        java.sql.ResultSet res = pst.executeQuery(sql);
                        while(res.next()){
                            modelS.addRow(new Object[]{res.getString(1),
                                res.getString(2),res.getString(3),
                                res.getString(4),res.getString(5),
                                res.getString(6)
                            });
                        }
                    tbMain.setModel(modelS);
                }catch(SQLException ex){

                }
                txtTotalnya();
            break;
        }
    }//GEN-LAST:event_btCariActionPerformed

    private void cmKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmKategoriActionPerformed
        // TODO add your handling code here:
        String pilih = cmKategori.getSelectedItem().toString();
        
        switch(pilih){
            case "Nota" :
                tbMainNota();
                txtTotalnya();
            break;
            case "Barang" :
                tbMainBarang();
                txtTotalnya();
            break;
            case "Suplier" :
                tbMainSup();
                txtTotalnya();
            break;
        }
    }//GEN-LAST:event_cmKategoriActionPerformed

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
            java.util.logging.Logger.getLogger(laporanPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporanPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporanPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporanPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporanPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private combo_suggestion.ComboBoxSuggestion cmFilter;
    private combo_suggestion.ComboBoxSuggestion cmKategori;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reset;
    private table.tabel tbMain;
    private com.toedter.calendar.JDateChooser tgl;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKeuntungan;
    private javax.swing.JTextField txtPemasukan;
    private javax.swing.JTextField txtSetoran;
    private javax.swing.JTextField txtTtlQty;
    // End of variables declaration//GEN-END:variables
}
