package src.serverstuff.usefulstuff;


import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RecursiveTask;

public class ClientConnectionInvoker extends RecursiveTask<String> {

    private static Socket socket;
    public ClientConnectionInvoker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String compute() {
        ClientConnection clientConnection = new ClientConnection(socket, Thread.currentThread().getId());
        new Thread(clientConnection).start();
        return "Клиент " + Thread.currentThread().getId() + " принят." ;
    }
}
