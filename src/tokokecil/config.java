


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokecil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author VivoBook
 */
public class config {

     private Connection akses;
    public Connection akses(){
        try{
            String url = "jdbc:mysql://localhost:3306/tokokue_new";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            akses = DriverManager.getConnection(url, user, pass);
        }catch(SQLException ex){
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return akses;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
