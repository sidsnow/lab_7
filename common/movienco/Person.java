package com.movienco;

import java.io.Serializable;

public class Person implements Serializable {

    /**
     *
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле не может быть null

    /**
     *
     * @param s
     */
    public Person(String s) {
    this.name = s; this.location = new Location(1L, "nowhere");
    }

    @Override
    public java.lang.String toString () {
        return this.name;
    }
}