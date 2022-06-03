package src.serverstuff.commands;

import src.serverstuff.usefulstuff.Scannable;

import java.io.IOException;
import java.util.Scanner;

public interface ArgsCommand {

    String execute(String username, Object s) throws IOException;

}
