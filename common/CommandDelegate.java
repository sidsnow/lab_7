package com;

import com.movienco.MovieCollection;

import java.io.Serializable;
import java.sql.*;

public class CommandDelegate implements Serializable {

    private static CommandDelegate instance;


    private CommandDelegate () {}

    public void setCommandDelegate(Object o) {
        instance = (CommandDelegate) o;
    }
    public static CommandDelegate getInstance() {
        if (instance == null) {
            instance = new CommandDelegate();
        }
        return instance;
    }




}
