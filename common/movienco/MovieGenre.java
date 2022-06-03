package com.movienco;

import java.io.Serializable;

public enum MovieGenre implements Serializable {
    COMEDY("COMEDY"),
    ADVENTURE("ADVENTURE"),
    THRILLER("THRILLER");
    public String name;
   MovieGenre(String s) {this.name = s;}
    public String getName() {return name;}
}