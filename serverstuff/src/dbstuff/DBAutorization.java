package src.serverstuff.dbstuff;


import src.serverstuff.exceptions.DontYouDare;

import java.sql.*;

public class DBAutorization {
    private static DBAutorization instance;


    private DBAutorization(){}

    public static DBAutorization getInstance() {
        if (instance == null) {
            instance = new DBAutorization();
        }
        return instance;
    }

    public boolean ifUserPresent(String name) {
        boolean result = false;
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT element_id FROM users WHERE element_id = ?;");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) result = true;
        } catch (SQLException e) { System.out.println("ошибка: " + e.getMessage());}

        return result;
    }

     public String toAutorize(String name, String password) throws DontYouDare {
         try (Connection connection = Connector.getInstance().getConnection()) {
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE element_id = ?");
             preparedStatement.setString(1, name);
             String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
             ResultSet resultSet = preparedStatement.executeQuery();
             if (resultSet.next()) {
                 if (resultSet.getString("password").equals(sha256hex)) {
                     return "Успешно.";
                 } else {
                     throw new DontYouDare("Неверный пароль.");
                 }
             } else {  throw new DontYouDare("Нет такого пользователя. Зарегистрируйтесь. ");}
         } catch (SQLException e) {
             return  e.getMessage();
         }
    }

    public String  toRegister(String name, String password) throws DontYouDare {
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
        String sql = "INSERT INTO users (element_id, password) VALUES ('" + name + "', '" + sha256hex + "');";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            return "Успешно добавили пользователя";
        } catch (SQLException e) {
            return  e.getMessage();
        }
    }

    public Integer getUserId (String name) {
        Integer key = null;
        String sql = "SELECT id FROM users WHERE element_id = ?";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
            key = resultSet.getInt(1); } else {key = -1;}
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return key;
    }
}
