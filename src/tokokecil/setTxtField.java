/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokokecil;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author T440
 */

public class setTxtField extends PlainDocument {
    private final int maxLength;

    public setTxtField(int maxLength) {
        this.maxLength = maxLength;     //Buat Ngebatasin Panjang Isi dari Suatu txtField
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= maxLength) {
            super.insertString(offset, str, attr);
        }
    }
}