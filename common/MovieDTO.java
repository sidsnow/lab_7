package com;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.movienco.Coordinates;
import com.movienco.MovieCollection;
import com.movienco.MovieGenre;
import com.movienco.Person;

public class MovieDTO implements Serializable {

    private final Integer creator_id;
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    private final long goldenPalmCount; //Значение поля должно быть больше 0
    private final Integer totalBoxOffice; //Поле может быть null, Значение поля должно быть больше 0
    private final MovieGenre genre; //Поле может быть null
    private final Person director; //Поле может быть null
    private final Integer idToUpdate;


    public MovieDTO (ArrayList info){
        this.creator_id = (int) info.get(0);
        this.name = (String) info.get(1);
        ArrayList<Double> coords = (ArrayList<Double>) info.get(2);
        this.coordinates = ((coords.get(0)==null)&&(coords.get(1)==null)) ? new Coordinates(0D,0D) : new Coordinates(coords.get(0), coords.get(1));
        this.oscarsCount = (info.get(3) == null) ? 0 : (Integer) info.get(3);
        this.goldenPalmCount = (info.get(4) == null)? 0 : (Long) info.get(4);
        this.totalBoxOffice = ((info.get(5)==null) |info.get(5).equals("") ) ?  0 : (Integer) info.get(5);
        this.genre = (MovieGenre) info.get(6); this.director = new Person( (String) info.get(7));
          this.idToUpdate = (Integer) info.get(8);
    }

    public Integer getCreatorId() {return  creator_id;}
    public String getName() {
        return name;
    }

    public Integer getIdToUpdate() {return idToUpdate;}

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public long getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public Integer getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Person getDirector() {
        return director;
    }
}
