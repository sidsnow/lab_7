package  src.serverstuff.commands.wargs;
import src.serverstuff.commands.ArgsCommand;
import src.serverstuff.commands.CommonCommand;
import com.movienco.MovieGenre;
import src.serverstuff.dbstuff.DBAutorization;
import src.serverstuff.dbstuff.DBVizualization;

import java.io.IOException;

public class CountByGenreKey implements CommonCommand, ArgsCommand {
    /**
     *
     * @param s
     */

    /** execute main method
     *
     * @param
     */


    public String execute(String name,Object val)  {
        String o;
        String s = String.valueOf(val);
            try {
                MovieGenre genre = MovieGenre.valueOf(s);
                o = "Количество фильмов по жанру " + genre.getName() + " " +
                        DBVizualization.getInstance().count(genre.toString());
            } catch (IllegalArgumentException e) {
                o = "Нет таких жанров в списке(ADVENTURE, THRILLER, COMEDY). Try again.\n";
            }
            return o;
         }

    /** execute method to show off
     *
     * @throws IOException
     */
    public String execute() throws IOException {
        return "Забыли аргументы.\n";
    }

    /**gets the name
     *
     * @return
     */
    public String getName() {
        return "count_by_genre";
    }
}
