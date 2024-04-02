package ru.inno.iterator;

import java.sql.*;
import java.util.Scanner;

public class DbDemo {

    public static void main(String[] args) throws SQLException {

        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";
        String SQL = "SELECT * FROM company where name like '%ompan%' order by id desc";
        String SQL_COUNT = "SELECT count(*) FROM company where name like '%ompan%'";

        Connection connection = DriverManager.getConnection(connectionString, user, pass);


        // select
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        int counter = 0;
        while (resultSet.next()) {
            counter++;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + " - " + name);
        }

        // count
        ResultSet rowsCount = connection.createStatement().executeQuery(SQL_COUNT);



        rowsCount.next();
        int count = rowsCount.getInt(1);
        System.out.println(count);
        System.out.println(counter);

        // dynamic query
        Scanner scanner = new Scanner(System.in);
        String descToBe = scanner.nextLine();


//        SQL Injection heaven
//        ResultSet myCompanies = connection.createStatement().executeQuery("SELECT * FROM company where description = '" + descToBe + "';");

        getByDesc(connection, descToBe);

        connection.close();


        //
        // []
        // [] <
        // []
        // []
        // []
        // []
        // []
        // []
        // []
        // []
        // <



    }

    private static void getByDesc(Connection connection, String descToBe) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM company where description = ? and id > ?");
            statement.setString(1, descToBe);
            statement.setInt(2, 2759);
            ResultSet myCompanies = statement.executeQuery();
            while (myCompanies.next()) {
                int id = myCompanies.getInt("id");
                String name = myCompanies.getString("name");
                String desc = myCompanies.getString("description");
                System.out.println(id + " - " + name + " - " + desc);
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

}
