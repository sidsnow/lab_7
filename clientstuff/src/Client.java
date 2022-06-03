
import com.CommandDelegate;
import usefulstuff.Autorizator;
import usefulstuff.MovieEditor;
import usefulstuff.User;
import usefulstuff.Validator;

import java.io.*;
import java.util.*;
import  java.net.*;


public class Client {
    /**
     * app itself
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException, InterruptedException {
        final Scanner console = new Scanner(System.in);
        System.out.println("starting . . .\n");
        String lastCommand = "";
        int flag = 0;

        while (true) {
            try (Socket clientSocket = new Socket("127.0.0.1", 8000);) {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ObjectOutputStream objSender = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream objReciever = new ObjectInputStream(clientSocket.getInputStream());
                CommandDelegate.getInstance().setCommandDelegate(objReciever.readObject());
                Validator.getInstance().setConsole(console);
                while (!Autorizator.getInstance().toAutorize(objReciever, objSender, console));


                if (!(lastCommand.equals(""))) {
                    String[] lastCommandInfo = lastCommand.split(" ");
                    Object toSend = Validator.getInstance().validateData(lastCommandInfo);
                    List<Object> infoToSend = new ArrayList<>();
                    infoToSend.add(lastCommandInfo[0]);
                    infoToSend.add(toSend);
                    objSender.writeObject(infoToSend);
                    objSender.flush();
                    Object[] recievedObject = (Object[]) objReciever.readObject();
                    System.out.println(recievedObject[0]);
                }
                while (clientSocket.isConnected()) {
                        System.out.print("Введите команду по главному слову. Для помощи, введите help.\n" + "~\n");
                        try {
                        String command = console.nextLine().trim();
                        String[] commandInfo = command.split(" ");
                        boolean exit = commandInfo[0].equals("exit");
                        if (exit) {
                            System.out.println("That's all folks!!!!!!!");
                            flag = 3;
                            break;
                        }
                        boolean isPresent = Arrays.stream(Validator.getInstance().getComList()).anyMatch(s -> s.equals(commandInfo[0]));
                        if (isPresent) {
                            lastCommand = command;
                            Object toSend = Validator.getInstance().validateData(commandInfo);
                            List<Object> infoToSend = new ArrayList<>();
                            infoToSend.add(commandInfo[0]);
                            infoToSend.add(toSend);
                            objSender.writeObject(infoToSend);
                            objSender.flush();
                            Object recievedObject = objReciever.readObject();
                            System.out.println(recievedObject);
                        } else {
                            System.out.println("Команды нет.");
                        } } catch (NoSuchElementException e) {
                            System.out.println("Введите что-нибудь.");
                        }
                    }
            } catch (ConnectException e) {
                flag+=1;
                if (flag == 3) {
                    System.out.println("Сервер недоступен. Пожалуйста, сначала запустите сервер, а потом приложение.");
                    System.exit(0);
                } else {
                System.out.println("Включите сервер. Ожидаю.");
                    Thread.sleep(10000);}
            } catch (SocketException e) {
                System.out.println("Похоже, сервер отключился. Пробуем установить соединение . . .");
                }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}

