package src.serverstuff.commands;

import src.serverstuff.commands.wargs.*;
import src.serverstuff.commands.woargs.*;

import java.io.IOException;
import java.util.TreeMap;

public class CommandsCollection {
    /**
     *
     */
    private static CommandsCollection instance;
    private final static TreeMap<String, CommonCommand> commands = new TreeMap<>();

    private CommandsCollection() throws IOException {
        this.setCommands(new Help(), new Exit(), new Show(), new PrintDescending(), new Info(),
                new GroupCountingByDirector(), new Clear(), new UpdateIdKey(), new RemoveLowerKeyKey(), new RemoveGreaterKeyKey()
                /*, new RemoveGreaterKey()*/, new InsertKey(), new ExecuteScriptFilename(), new CountByGenreKey(), new RemoveKeyKey()
                /*new Register(), new Login()*/);
    }

    public static CommandsCollection getInstance() throws IOException {
        if (instance == null) {
            instance = new CommandsCollection();
        }
        return instance;
    }


    /**
     * gets command
     * @param s
     * @return
     */
    public CommonCommand getCommand(String s) {
        s.trim();
        return commands.get(s);
    }


    /**
     * sets command
     * @param comm
     */
    public void setCommands(CommonCommand ... comm) {
        for (CommonCommand command : comm) {
            commands.put(command.getName(), command);
        }
    }

    /**checks if command is present
     *
     * @param s
     * @return
     */
    public boolean containsCommand (String s) {
        return commands.containsKey(s);
    }


}