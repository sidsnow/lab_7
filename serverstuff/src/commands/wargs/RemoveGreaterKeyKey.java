package src.serverstuff.commands.wargs;

import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBModification;
import src.serverstuff.dbstuff.UserRightsVerification;

import java.io.IOException;

public class RemoveGreaterKeyKey implements CommonCommand, ArgsCommand {
    /**
     *
     * @param s
     * @throws IOException
     */

    /**main execute
     *
     * @param m
     * @throws IOException
     */
    public String execute(String name, Object m) throws IOException {
        if (UserRightsVerification.getInstance().ifAble((Integer) m,
                name)) {
            Integer key = (Integer) m;
            return DBModification.getInstance().deleteGreaterId(key);
        } else return "Нет прав.\n";

    }
    /** execute method to show off
     *
     * @throws IOException
     */
    public String execute() throws IOException {
        return  "Забыли аргументы.\n";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "remove_greater_key";
    }
}
