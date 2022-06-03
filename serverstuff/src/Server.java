package  src.serverstuff;

import src.serverstuff.commands.CommandsCollection;
import src.serverstuff.usefulstuff.ClientConnectionInvoker;
import src.serverstuff.usefulstuff.CommandParser;
import java.io.*;
import java.net.ServerSocket;
import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;


public class Server {

    /**
     * app itself
     *
     * @param args
     * @throws IOException
     *
     */
    private static ArrayList<ClientConnectionInvoker> connections = new ArrayList<>();


    public static void main(String args[]) throws IOException, ClassNotFoundException {
        System.out.println("starting . . .\n");
      try  {
          Class.forName("org.postgresql.Driver");
          Connection connection = DriverManager.getConnection("jdbc:postgresql://pg:5432/studs","s338975","ljg001");
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users ("
                    + "id serial primary key,"
                    + "element_id text,"
                    + "password text);"
                    + "CREATE TABLE IF NOT EXISTS moviecollection ("
                    + "id serial primary key ,"
                    + " element_id int,"
                    + "  name text,"
                    + " coordX double precision,"
                    + "  coordY double precision,"
                    + "  oscarsCount integer,"
                    + "  goldenPalmCount bigint,"
                    + " totalBoxOffice integer,"
                    + " genre text,"
                    + " director text," +
                    "creator_id INTEGER REFERENCES users(id));" );
            preparedStatement.execute();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Wasn't a success.");
      }
        CommandParser.getInstance().setCommandsCollection(CommandsCollection.getInstance());
        System.out.println("Приготовления завершены.");

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {

                ClientConnectionInvoker connectionInvoker = new ClientConnectionInvoker(serverSocket.accept());
                connections.add(connectionInvoker);
                System.out.println(connectionInvoker.invoke());


            }
        }


    }
}
