package table;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VivoBook
 */
    public class tabel extends JTable{
    
    public tabel() {
        getTableHeader().setDefaultRenderer(new tabelHeader());
        getTableHeader().setPreferredSize(new Dimension(0, 35));
        setDefaultRenderer(Object.class, new tabelCell());
        setRowHeight(30);
    }
    
    private class tabelHeader extends DefaultTableCellRenderer{
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            Component com= super.getTableCellRendererComponent(jtable, o, bln1, bln1, i, i1);
            com.setBackground(new Color(57,72,12));
            com.setForeground(new Color(255,255,255));
            com.setFont(com.getFont().deriveFont(Font.BOLD, 20));
            return com;
        }
    }
    private class tabelCell extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int coloum) {
            Component com= super.getTableCellRendererComponent(jtable, o, bln, bln1, row, coloum); 
            if(isCellSelected(row, coloum)) {
                if (row%2==0) {
                    com.setBackground(new Color(196,150,113));
                }else{
                    com.setBackground(new Color(196,150,113));
                }
            }else{
                if (row%2==0) {
                    com.setBackground(new Color(230,187,153));
                }else{
                    com.setBackground(new Color(230,187,153));
                }
            }
            com.setForeground(new Color(50,50,50));
            setBorder(new EmptyBorder(0,5,0,5));
            return com;
        }
    }
    }
