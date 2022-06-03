package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBVizualization;

public class GroupCountingByDirector implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {
        return DBVizualization.getInstance().countGroup("director");
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "group_counting_by_director";
    }
}
