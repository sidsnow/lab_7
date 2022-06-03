package com.movienco;

import java.io.Serializable;

public class Location implements Serializable {
    /**
     *
     */
    private Long x; //Поле не может быть null
    private double y;
    private float z;
    private String name; //Поле не может быть null

    /**
     *
     * @param x
     * @param name
     */
    public Location (Long x, String name) {
        this.x = x; this.name = name;
    }
}