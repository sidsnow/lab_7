package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBModification;

public class Clear implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {
        return DBModification.getInstance().clear();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "clear";
    }
}
