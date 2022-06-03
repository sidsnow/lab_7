package src.serverstuff.usefulstuff;

import com.movienco.Movie;
import com.MovieDTO;

import java.io.IOException;

public class MovieUpdater {
    private static MovieUpdater instance;

    private MovieUpdater() {}
    public static MovieUpdater getInstance() {
        if (instance == null) {
            instance = new MovieUpdater();
        }
        return instance;
    }
    public void start(Movie ms, MovieDTO movieToCopy) throws IOException {
        ms.setIdCauseNecessary();
        ms.setName(movieToCopy.getName());
        ms.setCoordinates(movieToCopy.getCoordinates());
        ms.setOscarsCount(movieToCopy.getOscarsCount());
        ms.setGoldenPalmCount(movieToCopy.getGoldenPalmCount());
        ms.setTotalBoxOffice(movieToCopy.getTotalBoxOffice());
        ms.setMovieGenre(movieToCopy.getGenre());
        ms.setDirector(movieToCopy.getDirector().toString());



    }






}


