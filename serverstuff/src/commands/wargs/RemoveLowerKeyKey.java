package src.serverstuff.commands.wargs;

import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBModification;
import src.serverstuff.dbstuff.UserRightsVerification;

import java.io.IOException;


public class RemoveLowerKeyKey implements CommonCommand, ArgsCommand {
    /**
     *
     * @param key
     * @throws IOException
     */
    /**main execute
     *
     * @param value
     * @throws IOException
     */
    public String execute(String name, Object value) throws IOException {
        if (UserRightsVerification.getInstance().ifAble((Integer) value,
                name)) {
            Integer key = (Integer) value;
            return DBModification.getInstance().deleteLowerId(key);
        } else return  "Нет прав.\n";
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
        return "remove_lower_key";
    }
}
