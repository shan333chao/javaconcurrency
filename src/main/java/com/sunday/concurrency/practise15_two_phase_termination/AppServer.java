package com.sunday.concurrency.practise15_two_phase_termination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class AppServer  extends  Thread{
    private  int port;
    private static final int Default_port=12711;
    private  volatile  boolean start=true;
    private List<ClientHandler> clientHandlers=new ArrayList<>();
    private  final    ExecutorService executor= Executors.newFixedThreadPool(10);
    private  ServerSocket serverSocket;
    public AppServer(){
        this(Default_port);
    }

    public AppServer(int port) {
        this.port = port;
    }


    public  void shutdown() throws IOException {
        this.start=false;
        this.serverSocket.close();
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            this.serverSocket=new ServerSocket(port);
            while (start){
                Socket accept =   this.serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(accept);
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }finally {
            this.dispose();
        }
    }

    private void dispose() {
        System.out.println("dispose");
        this.clientHandlers.stream().forEach(ClientHandler::stop);

        this.executor.shutdown();

    }
}
