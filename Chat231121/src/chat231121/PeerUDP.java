/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat231121;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;


/**
 *
 * @author MAALFING
 */
public class PeerUDP extends Thread{

    private DatagramSocket S;
    private boolean conn;
    private String nickname, dest, ipdest;
    private Scanner Sc;
    private Scanner SV;
    private Chat C;
    CHATFRAME F;
    

    public PeerUDP(Scanner SV, CHATFRAME F) throws SocketException {
        S = new DatagramSocket(2003);
        conn=false;
        nickname = "guest";
        dest="null";
        Sc = new Scanner(System.in);
        this.SV=SV;
        C = new Chat(this);
        this.F = F;
        ipdest="";
    }

    public boolean isConn() {
        return conn;
    }

    public void setConn(boolean conn) {
        this.conn = conn;
    }
    

    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIpdest() {
        return ipdest;
    }

    public void setIpdest(String ipdest) {
        this.ipdest = ipdest;
    }
    
    
    
    
    @Override
    public void run() {
        String n="";
        while(true){
            
            try {
                n = ricevi();
                System.out.println(n);
                String[] S = n.split(";");
                if(S[0].equals("c")&&!conn){
                    //SV.close(); 
                    int scelta = JOptionPane.showConfirmDialog(F, "Vuoi accettare la connessione con "+ S[1], "Richiesta connessione", JOptionPane.YES_NO_OPTION);
                    //System.out.println("Vuoi accettare la connessione con "+ S[1] +"? [S] o [N]");
                    //String scelta= Sc.nextLine();
                    System.out.println("La scelta è " + scelta);                    
                    if (scelta==0) {
                        dest=S[1];
                        invia("y;"+nickname);                         
                        conn=true;
                         F.jLabel1.setText("Connesso con: " + dest);
                         attivaElementi();
                        //C.start();
                    }
                    else{
                        invia("n;");
                    }                    
                }
                else if(S[0].equals("c")&&conn){
                    invia("n;");                    
                }
                else if(S[0].equals("n")&&!conn){
                    JOptionPane.showMessageDialog(F, "La connessione è stata rifiutata");
                    //System.out.println("La connessione è stata rifiutata");
                }
                else if(S[0].equals("n")&&conn){
                    JOptionPane.showMessageDialog(F, "La connessione è stata rifiutata");
                    //System.out.println("La connessione è stata rifiutata");
                }
                else if(S[0].equals("y")&&!conn){
                    dest=S[1];
                    conn=true;
                    JOptionPane.showMessageDialog(F,"La connessione con " + S[1] + " è stata accettata");
                    invia("y;");
                    F.jLabel1.setText("Connesso con: " + dest);
                    attivaElementi();
                    //C.start();
                }
                else if(S[0].equals("m")&&conn){
                    F.jTextArea1.append(dest + ": " + S[1]+"\n");
//                    System.out.print(dest);                   
//                    System.out.print(": ");
//                    System.out.println(S[1]);
                }
                else if(S[0].equals("e")){
                    conn=false;
                    JOptionPane.showMessageDialog(F,"La connessione è terminata");
                    ipdest="";
                    F.jTextArea1.setText("");
                    disattivaElementi();
                    F.jLabel1.setText("Connessione non stabilita");
                }
            } catch (IOException ex) {
                Logger.getLogger(PeerUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
    }
    
    void attivaElementi(){        
        F.jButton1.setEnabled(!conn);
        F.jTextPane1.setEnabled(conn);
        F.jButton3.setEnabled(conn);
    }
    
    void disattivaElementi(){        
        F.jButton1.setEnabled(conn);
        F.jTextPane1.setEnabled(!conn);
        F.jButton3.setEnabled(!conn);
    }
    
    
    
     synchronized void invia(String n) throws UnknownHostException, IOException{
        byte[] di = n.getBytes();
        DatagramPacket D = new DatagramPacket(di, di.length);
        InetAddress A =  InetAddress.getByName(ipdest);
         System.out.println(n+" -> " + ipdest);
        D.setAddress(A);
        D.setPort(2003);
        S.send(D);
    }
     
     
     synchronized void invia(String n, String ip) throws UnknownHostException, IOException{
        byte[] di = n.getBytes();
        DatagramPacket D = new DatagramPacket(di, di.length);
        InetAddress A =  InetAddress.getByName(ip);
        D.setAddress(A);
        D.setPort(2003);
        S.send(D);
    }
     
    
     String ricevi() throws IOException{
      byte[] risp = new byte[1500];
        DatagramPacket R = new DatagramPacket(risp,risp.length);
        S.receive(R);
        ipdest = R.getAddress().toString().substring(1);
        return new String(risp);  
    }
    
}
