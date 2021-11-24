/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat231121;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 *
 * @author MAALFING
 */
public class Chat231121 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException, InterruptedException {
        // TODO code application logic here
        String nickname = "";
        boolean sceltanick = false;
        Scanner S = new Scanner(System.in);
        //PeerUDP U = new PeerUDP(S);        
        
        
        //U.start();

        while (!sceltanick) {
            System.out.println("Inserisci nickname");
            nickname = S.nextLine();
            //U.setNickname(nickname);
            sceltanick = !sceltanick;
        }
        System.out.println("1. Avvia connessione\n2. Cambia nickname\n________");
        String scelta = S.nextLine();
        if (scelta.equals("1")) {
            //U.invia(new String("c;" + nickname));
        }
               
    
    
    }

}
