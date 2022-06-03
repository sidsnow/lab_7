package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBVizualization;

public class PrintDescending implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {
       return DBVizualization.getInstance().show("DESC");
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "print_descending";
    }
}
