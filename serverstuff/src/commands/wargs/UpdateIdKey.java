package src.serverstuff.commands.wargs;

import com.MovieDTO;
import src.serverstuff.commands.ArgsWConsoleCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBModification;
import src.serverstuff.dbstuff.UserRightsVerification;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateIdKey implements CommonCommand, ArgsWConsoleCommand {


    public String execute(String name, MovieDTO movie) throws IOException, ClassNotFoundException {
        if (UserRightsVerification.getInstance().ifAble(movie.getIdToUpdate(),
                name)) {
        ArrayList<Object> info = new ArrayList<>();
        info.add(movie.getName());
        info.add(movie.getCoordinates().getX()); info.add(movie.getCoordinates().getY());
        info.add(movie.getOscarsCount()); info.add(movie.getGoldenPalmCount());
        info.add(movie.getTotalBoxOffice()); info.add(movie.getGenre().toString());
        info.add(movie.getDirector().toString()); info.add(movie.getIdToUpdate());
        return DBModification.getInstance().update(info); }
        else return  "Нет прав.\n";
    }


    /** execute method to show off
     *
     * @throws IOException
     */
    public String execute() throws IOException {
        return "Забыли аргументы.\n";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "update";
    }
}
