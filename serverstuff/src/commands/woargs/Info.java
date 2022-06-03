package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBVizualization;

public class Info implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {

       return DBVizualization.getInstance().info();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "info";
    }
}
