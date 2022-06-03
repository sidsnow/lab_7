package src.serverstuff.commands;

import java.io.IOException;

public interface CommonCommand {

    String execute() throws IOException;
    String getName();



}
