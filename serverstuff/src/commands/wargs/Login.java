package src.serverstuff.commands.wargs;

import src.serverstuff.commands.AutorizationCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.exceptions.DontYouDare;

public class Login implements CommonCommand, AutorizationCommand {
    public String getName() {
        return "login";
    }

    public String execute() {
        return "Забыли аргументы";
    }
    public String execute(String name, String password) {
        return DBAutorization.getInstance().toAutorize(name, password);
    }
}
