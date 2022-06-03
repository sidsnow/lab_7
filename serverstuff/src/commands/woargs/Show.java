package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBVizualization;

public class Show implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {

       return DBVizualization.getInstance().show("ASC");
    }

    /**
     *
     * @return
     */

    public String getName() {
        return "show";
    }
}
