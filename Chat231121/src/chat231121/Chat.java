/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat231121;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MAALFING
 */
public class Chat extends Thread{

    private Scanner S;
    private PeerUDP U;

    public Chat(PeerUDP U) {
        S = new Scanner(System.in);
        this.U = U;
    }
    
    @Override
    public void run() {
        //System.out.println("Ora puoi inviare messaggi");
        JOptionPane.showMessageDialog(U.F,"Ora puoi inviare messaggi");
        while (true) {
            try {
                U.invia("m;"+S.nextLine());
                //U.sleep(1000);
            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
