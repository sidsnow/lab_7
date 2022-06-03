package src.serverstuff.commands.wargs;

import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.CommonCommand;
import com.CommandDelegate;
import src.serverstuff.dbstuff.DBModification;
import src.serverstuff.dbstuff.UserRightsVerification;

import java.io.IOException;


public class RemoveGreaterKey implements CommonCommand, ArgsCommand {
    /**
     *
     * @param s
     * @throws IOException
     */
    /** main execute
     *
     * @param
     * @throws IOException
     */
    public String execute(String user, Object val) throws IOException {
        if (UserRightsVerification.getInstance().ifAble(String.valueOf(val),
                user)) {
        return DBModification.getInstance().deleteGreaterName(String.valueOf(val)); }
        else return "Нет прав.";
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
        return "remove_greater";
    }
}
