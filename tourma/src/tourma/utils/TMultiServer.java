/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WFMJ7631
 */
public class TMultiServer extends Thread {

    private boolean stop = false;
    private int _port=2017;
    ServerSocket serverSocket;

    /*public TMultiServer(int port) {
        _port = port;
    }*/

    public void stopServer() {
        stop = true;
        if (this.isAlive()) {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TMultiServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void run() {
        try {
            serverSocket = new ServerSocket(_port);
            while (!stop) {
                Socket socket = serverSocket.accept();
                new TServerThread(socket).start();
            }
        } catch (IOException e) {
            Logger.getLogger(TMultiServer.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
