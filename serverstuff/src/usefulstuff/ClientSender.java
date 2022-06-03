package src.serverstuff.usefulstuff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientSender {
    private ExecutorService executor = Executors.newCachedThreadPool();
    private static ClientSender instance;

    private ClientSender() {}

    public static ClientSender getInstance() {
        if (instance == null) {
            instance = new ClientSender();
        }
        return instance;
    }

    public void invokeSendingTask(Runnable task) {
        executor.execute(task);
    }
}
