package src.serverstuff.commands;

import com.MovieDTO;


import java.io.IOException;

public interface ArgsWConsoleCommand {
    String execute (String username, MovieDTO movie) throws IOException, ClassNotFoundException;
}
