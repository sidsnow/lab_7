package src.serverstuff.usefulstuff;

import com.MovieDTO;
import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.ArgsWConsoleCommand;
import src.serverstuff.commands.CommandsCollection;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.exceptions.DontYouDare;
import src.serverstuff.usefulstuff.ScanConsole;

import java.io.*;
import java.sql.Connection;
import java.util.List;

public class CommandParser implements Runnable {
    /**
     *
     */
    private static CommandsCollection commandsCollection;
    private ScanConsole console;
    private String filePathCheck;
    private ObjectOutputStream sender;
    private static CommandParser instance;
    private String username;
    private List<Object> recievedInfo;
    private ConsoleClient clientConsole;

    private CommandParser() {
    }

    public static CommandParser getInstance() {
        if (instance == null) {
            instance = new CommandParser();
        }
        return instance;
    }

    public void setCommandsCollection(CommandsCollection commandSCollection) {
        commandsCollection = commandSCollection;
    }

    public CommandsCollection getCommandCollection() {
        return commandsCollection;
    }

    public void run() {
        try {
            sender.writeObject(work(username,recievedInfo,clientConsole));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  Runnable getReadyToRun (ObjectOutputStream sender, String username, List<Object> recievedInfo, ConsoleClient clientConsole) {
        this.sender = sender;
        this.username = username;
        this.recievedInfo = recievedInfo;
        this.clientConsole = clientConsole;
        return  instance;
    }


    public <T extends ScanConsole> Object work(String name, List<Object> info, T console) throws IOException, ClassNotFoundException {
        Object o = null;
        Object[] commandInfo = info.stream().toArray();
         String command = String.valueOf(commandInfo[0]);
        this.console = console;
        if ((command == null) || command.equals("")) {
            System.out.print("Надо команду. Очень надо. \n");
        } else {
            if (commandsCollection.containsCommand(command)) {
                if (commandsCollection.getCommand(command) instanceof ArgsCommand) {
                    try {
                        o = ((ArgsCommand) commandsCollection.getCommand(command)).execute(name, commandInfo[1]);
                    } catch (NullPointerException e) {
                        o = commandsCollection.getCommand(command).execute();
                    }
                } else if (commandsCollection.getCommand(command) instanceof ArgsWConsoleCommand) {
                    try {
                        o = ((ArgsWConsoleCommand) commandsCollection.getCommand(command)).execute(name, (MovieDTO) commandInfo[1]);
                    } catch (NullPointerException e) {
                        o = commandsCollection.getCommand(command).execute();
                    }
                } else {
                    o = commandsCollection.getCommand(command).execute();
                }
            }
        }

        return o;
    }

    public String autorization(int i, String name, String password) throws DontYouDare {
        if (i == 1) {
            return DBAutorization.getInstance().toAutorize(name,password);
        } else {
            return DBAutorization.getInstance().toRegister(name,password);
        }
    }
}


