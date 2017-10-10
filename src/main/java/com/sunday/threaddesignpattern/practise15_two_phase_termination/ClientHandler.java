package com.sunday.threaddesignpattern.practise15_two_phase_termination;

import java.io.*;
import java.net.Socket;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter printWriter = new PrintWriter(outputStream);) {

            while (!running) {
                String msg = br.readLine();
                if (null==msg){
                    break;
                }
                System.out.println("Come from client>" + msg);
                printWriter.write("echo:" + msg+"\r\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.running=false;
        }finally {
            this.stop();
        }

    }

    public void stop() {
        if (running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
