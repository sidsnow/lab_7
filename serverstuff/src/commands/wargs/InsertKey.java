package src.serverstuff.commands.wargs;

import src.serverstuff.commands.ArgsWConsoleCommand;
import src.serverstuff.commands.CommonCommand;
import src.serverstuff.dbstuff.DBAutorization;
import com.MovieDTO;
import src.serverstuff.dbstuff.DBModification;
import src.serverstuff.dbstuff.UserRightsVerification;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;


public class InsertKey implements CommonCommand, ArgsWConsoleCommand {
    private Integer key;

    public String execute(String name, MovieDTO movie) throws IOException, ClassNotFoundException {
        ArrayList<Object> info = new ArrayList<>();
        info.add((int) new Date().getTime());
        info.add(movie.getName());
        info.add(movie.getCoordinates().getX());
        info.add(movie.getCoordinates().getY());
        info.add(movie.getOscarsCount());
        info.add(movie.getGoldenPalmCount());
        info.add(movie.getTotalBoxOffice());
        info.add(movie.getGenre().toString());
        info.add(movie.getDirector().toString());
        info.add(movie.getCreatorId());
        return DBModification.getInstance().insert(info);
    }


    /** execute method to show off
     *
     * @throws IOException
     */
    public String execute() throws IOException {
        return  "Забыли аргументы.\n";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "insert";
    }
}
