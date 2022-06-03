package src.serverstuff.usefulstuff;

import com.CommandDelegate;
import src.serverstuff.commands.CommandsCollection;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.exceptions.DontYouDare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientConnection implements Runnable  {

    private static Socket clientSocket;
    private  static long connectionId;
    public ClientConnection (Socket socket, long id) {
        this.clientSocket = socket;
        connectionId = id;
    }
    private boolean isAutorized = false;
    private static String username;

    @Override
    public void run() {
        try {
            //серверная магия

            while (true) {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ObjectInputStream objReciever = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objSender = new ObjectOutputStream(clientSocket.getOutputStream());
                objSender.writeObject(CommandDelegate.getInstance());
                objSender.flush();
                ConsoleClient clientConsole = new ConsoleClient(reader, writer, objReciever, objSender);


                while (!clientSocket.isClosed()) {
                    try {
                        System.out.println("Принято. Клиент " + connectionId + " на этапе авторизации.");

                        while (!isAutorized) {
                            int check = Integer.parseInt(objReciever.readObject().toString());
                            List<Object> recievedInfo = (List<Object>) objReciever.readObject();
                            try {
                                CommandParser.getInstance().autorization(check, String.valueOf(recievedInfo.get(0)),
                                        String.valueOf(recievedInfo.get(1)));
                                objSender.writeObject("success"); objSender.flush();
                                username = String.valueOf(recievedInfo.get(0));
                                objSender.writeObject(DBAutorization.getInstance().getUserId(username));
                                isAutorized = true;
                            } catch (NullPointerException e) {
                                objSender.writeObject("Введите пароль."); objSender.flush();
                            } catch (DontYouDare e) {
                                objSender.writeObject(e.getMessage()); objSender.flush();
                            }
                        }
                        System.out.println("Клиент " + connectionId + " авторизован.");

                        List<Object> recievedInfo = (List<Object>) objReciever.readObject();
                        System.out.println("~~~" + recievedInfo.get(0));
                        ClientSender.getInstance().invokeSendingTask(
                                        CommandParser.getInstance().getReadyToRun(objSender,
                                                username, recievedInfo, clientConsole));

                    } catch (SocketException e) {
                        System.out.println(connectionId + "  клиент: разрыв соединения.");
                        clientSocket.close();

                    } catch (ClassNotFoundException e) {
                        writer.write("Данные не приняты сервером. Не найден объект при десериализации.\n");
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println(connectionId + " клиент: That's all folks!");
        }
    }
}
