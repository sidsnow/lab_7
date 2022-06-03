package src.serverstuff.commands.wargs;

import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.usefulstuff.FileParser;


import java.io.*;


public class ExecuteScriptFilename implements CommonCommand, ArgsCommand {
    String[] fileCheck = new String[300];
    int m = 1;
    /** execute main method
     *
     * @param
     * @throws IOException
     */
    public String execute(String name, Object val) throws IOException {
         return "well um ues but actually no";}
    /** execute method to show off
     *
     *
     */
    public String execute() {return  "Забыли аргументы.\n";
    }

    /** gets name
     *
     * @return
     */
    public String getName() {
        return "execute_script";
    }
}
