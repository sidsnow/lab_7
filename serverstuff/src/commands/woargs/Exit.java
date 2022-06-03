package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;

public class Exit implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {
        System.exit(0);
            return "А вот все, теперь мы завершаемся";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "exit";
    }
}
