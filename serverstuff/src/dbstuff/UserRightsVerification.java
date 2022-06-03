package src.serverstuff.dbstuff;

import src.serverstuff.exceptions.DontYouDare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRightsVerification {
    private static UserRightsVerification instance;
    private UserRightsVerification(){}

    public static UserRightsVerification getInstance() {
        if (instance == null) {
            instance = new UserRightsVerification();
        }
        return instance;
    }
    public boolean ifAble(Integer movie_id, String name)  {
        boolean result = false;
        try (
        Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT element_id FROM moviecollection WHERE creator_id = (SELECT id FROM users WHERE element_id = ?);");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (movie_id == resultSet.getInt("element_id")) {
                    result = true;
                }
            }
            }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            return result;

    }
    public boolean ifAble(String movie_name, String name)  {
        boolean result = false;
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT element_id FROM moviecollection WHERE name = ?");
            preparedStatement.setString(1,movie_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int movie_id = resultSet.getInt("element_id");
                PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT element_id FROM moviecollection WHERE creator_id = (SELECT id FROM users WHERE element_id = ?);");
                preparedStatement.setString(1,name);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()) {
                    if (movie_id == resultSet1.getInt("element_id")) {
                        result = true;
                    }
                }
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;

    }
    public Integer getUserId (String name) {
        Integer result = -1;
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE element_id = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;

    }
}
