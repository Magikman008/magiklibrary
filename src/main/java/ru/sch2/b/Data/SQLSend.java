package ru.sch2.b.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLSend {
    public static void send(String book, String author) {

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] subStr = author.split(" ");
        String name = subStr[0];
        String lastname = subStr[1];
        String url = "jdbc:mysql://db4free.net:3306/magikman008";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "magikman008", "magikman008");
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM book WHERE name='" + book + "'");
            int bookId = 0;
            while (resultSet.next()) {
                bookId = resultSet.getInt("id");
            }
            resultSet = statement.executeQuery("SELECT * FROM author WHERE name='" + name + "' AND lastname='" + lastname + "'");
            int authorId = 0;
            while (resultSet.next()) {
                authorId = resultSet.getInt("id");
            }
            statement.executeUpdate("INSERT INTO bookauthor (book, author) VALUES('" + bookId + "', '" + authorId + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
