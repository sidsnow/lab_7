package src.serverstuff.commands.wargs;

import src.serverstuff.commands.AutorizationCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;

public class Register implements CommonCommand, AutorizationCommand {
    public String getName() {
        return "login";
    }

    public String execute() {
        return "Забыли аргументы";
    }
    public String execute(String name, String password) {
        return DBAutorization.getInstance().toRegister(name, password);
    }
}
