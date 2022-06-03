package com.movienco;

import java.io.Serializable;

public class Coordinates implements Serializable {
    /**
     *
     */
    private Double x; //Поле не может быть null
    private double y; //Значение поля должно быть больше -793

    /**basic constructor
     *
     * @param x
     * @param y
     */
    public Coordinates (Double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public java.lang.String toString() {
        return String.valueOf(this.x) + ";" + String.valueOf(this.y);
    }
}
