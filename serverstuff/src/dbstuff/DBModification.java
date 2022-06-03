package src.serverstuff.dbstuff;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DBModification {
    private static DBModification instance;


    private DBModification(){}

    public static DBModification getInstance() {
        if (instance == null) {
            instance = new DBModification();
        }
        return instance;
    }
    public  String clear() {
        String sql = "DELETE FROM moviecollection;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            return "Успешно";
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }


    public String delete(Integer value){
        String sql = "DELETE FROM moviecollection WHERE element_id = ?;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,value);
            if (DBVizualization.getInstance().ifPresent(2, value.toString())) { preparedStatement.execute();
                return "Успешно.\n"; } else {return  "Элемента нет в БД.\n";}
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }
    public String deleteGreaterId(Integer value){
        String sql = "DELETE FROM moviecollection WHERE element_id > ?;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,value);
            if (DBVizualization.getInstance().ifPresent(2, value.toString())) { preparedStatement.execute();
                return "Успешно.\n"; } else {return  "Элемента нет в БД.\n";}
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }
    public String deleteGreaterName(String value){
        String sql = "DELETE FROM moviecollection WHERE name > ?;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,value);
            if (DBVizualization.getInstance().ifPresent(2, value.toString())) { preparedStatement.execute();
                return "Успешно.\n"; } else {return  "Элемента нет в БД.\n";}
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }
    public String deleteLowerId(Integer value){
        String sql = "DELETE FROM moviecollection WHERE element_id < ?;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,value);
            if (DBVizualization.getInstance().ifPresent(2, value.toString())) { preparedStatement.execute();
                return "Успешно.\n"; } else {return  "Элемента нет в БД.\n";}
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }

    public String insert(ArrayList values) {
        String sql = "INSERT INTO moviecollection (element_id, name, coordx, coordy, oscarscount, goldenpalmcount, totalboxoffice, genre, director, creator_id) VALUES (?,?,?,?,?,?,?,?,?,?);";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            values.forEach(x -> {
                try {
                    preparedStatement.setObject(values.indexOf(x)+1, x);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            preparedStatement.execute();
            return "Успешно.";
        } catch (SQLException throwables) {
            return "ошибка на уровне дао" + throwables.getMessage();
        }
    }

    public synchronized String update(ArrayList values) {
        String sql = "UPDATE moviecollection SET " +
                "name = ?, " +
                "coordx = ?, " +
                "coordy = ?," +
                "oscarscount = ?," +
                "goldenpalmcount = ?," +
                "totalboxoffice = ?," +
                "genre = ?," +
                "director = ?" +
                "WHERE element_id = ?;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (DBVizualization.getInstance().ifPresent(2, String.valueOf(values.get(8)))) {
                values.forEach(x -> {
                    try {
                        preparedStatement.setObject(values.indexOf(x)+1,x);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                preparedStatement.execute();
                return "Успешно."; } else return "Нет такого элемента.";
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }
}
