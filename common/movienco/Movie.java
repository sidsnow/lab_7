package com.movienco;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.Math.abs;

public class Movie implements Serializable {
    /**
     *
     */
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    private long goldenPalmCount; //Значение поля должно быть больше 0
    private Integer totalBoxOffice; //Поле может быть null, Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private Person director; //Поле может быть null

    /** to create a simple one
     *
     * @param s
     */
    public Movie(String s) {
        this.name = s;
        Date dateid = new Date();
        this.id = - (int) dateid.getTime();
        creationDate = ZonedDateTime.now();
        this.coordinates = new Coordinates(0D,0D);
        this.oscarsCount = 0;
        goldenPalmCount = 0;
        totalBoxOffice = 0;
        this.genre = null;
        this.director = new Person("nobody");
    }
    public Movie(int id) {
        this.id = id;
    }

    @Override
    public java.lang.String toString() {
        return "id = " + String.valueOf(id) + "\n" +
                "Название: " + this.name + "\n" +
                "Координаты: " + this.coordinates.toString() + "\n" +
                "Дата создания: " + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDate) + "\n" +
                "Количество оскаров: " + String.valueOf(this.oscarsCount) + "\n" +
                "Количество золотых веточек(?): " + String.valueOf(goldenPalmCount) + "\n" +
                "Кассовый сбор: " + String.valueOf(totalBoxOffice) + "\n" +
                "Жанр: " + String.valueOf(genre) + "\n" +
                "Режиссер: " + director.toString() + "\n";

    }

    public String getInfoString() {
        return  this.name + ";" +
                this.coordinates.toString() + ";" +
                String.valueOf(this.oscarsCount) + ";" +
                String.valueOf(goldenPalmCount) + ";" +
                String.valueOf(totalBoxOffice) + ";" +
                genre.getName() + ";" +
                director.toString();
    }

    /** when initializing from file in a specific order prescripted
     *
     * @param info
     */
    //String name, double x, double y, Integer oscarsCount, long goldenPalmCount,
    //Integer totalBoxOffice, MovieGenre genre, String director
    public Movie(String[] info) {
        this.name = info[0];
        this.coordinates = ((info[1]==null)&(info[2]==null)) ? new Coordinates(0D,0D) : new Coordinates(Double.valueOf(info[1]),Double.parseDouble(info[2]));
        this.oscarsCount = (info[3] == null) ? 0 : Integer.parseInt(info[3])  ;
        this.goldenPalmCount = (info[4] == null)? 0 : Long.parseLong(info[4]);
        this.totalBoxOffice = ((info[5]==null) |info[5].equals("") ) ?  0 : Integer.parseInt(info[5]);
        this.genre = MovieGenre.valueOf(info[6]); this.director = new Person(info[7]);
        this.id = Integer.valueOf(info[8]);
        creationDate = ZonedDateTime.now();
    }
    public Movie(String name, Double coord1, Double coord2,
                 int oscc, long gldp, int tbo, String moviegenre, String drct, int id) {
        this.name = name;
        this.coordinates = new Coordinates(coord1, coord2);
        this.oscarsCount = oscc;
        this.goldenPalmCount = gldp;
        this.totalBoxOffice = tbo;
        this.genre = MovieGenre.valueOf(moviegenre); this.director = new Person(drct);
        this.id = id;
        creationDate = ZonedDateTime.now();
    }



    public void setIdCauseNecessary(){
        this.id = (int) new Date().getTime();
    }
    public void setName(String s) { this.name = s;}
    public void setCoordinates(double x, double y) {this.coordinates = new Coordinates(x,y);}
    public void setCoordinates(Coordinates coordinates) {this.coordinates = coordinates;}
    public void setOscarsCount (Integer i) {this.oscarsCount = i;}
    public void setGoldenPalmCount (long i) {this.goldenPalmCount = i;}
    public void setMovieGenre (MovieGenre i) {this.genre = i;}
    public void setDirector(String i) {this.director = new Person(i);}
    public void setTotalBoxOffice (int i) {this.totalBoxOffice = i;}

    /**getters
     *
     * @return
     */
    public Integer getId() {return this.id;}
    public String getName() {return this.name;}
    public Person getDirector() {return this.director;}
    public MovieGenre getGenre() {return this.genre;}
}
