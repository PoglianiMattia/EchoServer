package echoserver;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoThread extends Thread {

    private Socket s;
    private boolean maiuscolo = false;

    public EchoThread(Socket s) {

        this.s = s;
    }

    public void run() {
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            String stringa;
            
            while (true) {
                stringa = in.readLine();

                switch(stringa){
                    case "fine":
                        return;
                    case "maiuscole: on" : 
                        maiuscolo = true;
                        break;
                    case "maiuscole: off" :
                        maiuscolo = false;
                        break;
                    default:
                        if(maiuscolo)
                            out.println(stringa.toUpperCase());
                        else
                            out.println(stringa);
                        break;
                }
                
            }
        } catch (IOException ex) {        }
        
        
    }
}
