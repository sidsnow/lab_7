package src.serverstuff.dbstuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBVizualization {
    private static DBVizualization instance;


    private DBVizualization(){}

    public static DBVizualization getInstance() {
        if (instance == null) {
            instance = new DBVizualization();
        }
        return instance;
    }

    public boolean ifPresent(int index, String option) throws SQLException {
        Connection connection = Connector.getInstance().getConnection();
        ResultSet result = null;
        switch (index) {
            case 1: {
                PreparedStatement ifPresent = connection.prepareStatement("SELECT exists(SELECT 1 FROM users WHERE element_id = ?) as values;");
                ifPresent.setInt(1, Integer.parseInt(option));
                result = ifPresent.executeQuery();
                break;
            }
            case 2:
            {
                PreparedStatement ifPresent = connection.prepareStatement("SELECT exists(SELECT 1 FROM moviecollection WHERE element_id = ?) as values;");
                ifPresent.setInt(1, Integer.parseInt(option));
                result = ifPresent.executeQuery();
                break;
            }
        }
        if (result.next()) return true;
        else  return false;
    }

    public String count(String expression) {
        String sql = "SELECT COUNT(*) FROM moviecollection where genre=?";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,expression);
            return preparedStatement.executeQuery().getString(1);
        } catch (SQLException e) {return String.valueOf(e.getErrorCode());}
    }

    public  String countGroup (String vals) {
        String sql = "SELECT ?, COUNT(*) FROM moviecollection GROUP BY ?";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,vals);
            preparedStatement.setString(2,vals);
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "";
            while (resultSet.next()) {
                result += "В группе с режиссером " + resultSet.getString(1) +
                        " " + resultSet.getString(2) + " фильмов. \n";
            }
            return result;
        } catch (SQLException e) {return String.valueOf(e.getErrorCode());}

    }
    public String info() {
        String sql = "SELECT table_name FROM information_schema.tables " +
                "WHERE table_schema NOT IN ('information_schema','pg_catalog');";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "Информация о БД (входящие таблицы):\n";
            while (resultSet.next()) result += resultSet.getString(1) + '\n';
            return result;
        } catch (SQLException e) {return String.valueOf(e.getErrorCode());}

    }
    public String show(String ordering) {
        String sql = "SELECT * FROM moviecollection ORDER BY id " + ordering + " ;";
        try (Connection connection = Connector.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "Информация о таблице:\n";
            while (resultSet.next()) {
                for (int i = 2; i < 12; i++) {
                    result += resultSet.getMetaData().getColumnName(i) + ": " + resultSet.getString(i) + "\n";
                }
                result +='\n';
            }
            return  result;
        } catch (SQLException e) {return String.valueOf(e.getErrorCode());}
    }
}
