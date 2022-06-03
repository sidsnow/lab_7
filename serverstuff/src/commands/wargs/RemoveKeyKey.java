package src.serverstuff.commands.wargs;

import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBModification;
import src.serverstuff.dbstuff.UserRightsVerification;

import java.io.IOException;


public class RemoveKeyKey implements CommonCommand, ArgsCommand {
    /**
     *
     * @param s
     * @throws IOException
     */
    /** main execute
     *
     * @param key
     * @throws IOException
     */
    public String execute(String name, Object key) throws IOException {
        if (UserRightsVerification.getInstance().ifAble((Integer) key,
                name)) {
            Integer key1 = (Integer) key;
            return DBModification.getInstance().delete(key1);
        } else return "Нет прав.\n";

    }
    /** execute method to show off
     *
     * @throws IOException
     */
    public String execute() throws IOException {
        return "Забыли аргументы.\n";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "remove_key";
    }

}
