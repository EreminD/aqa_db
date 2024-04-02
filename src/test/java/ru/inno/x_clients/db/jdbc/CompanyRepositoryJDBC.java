package ru.inno.x_clients.db.jdbc;

import ru.inno.x_clients.db.CompanyRepository;
import ru.inno.x_clients.db.jdbc.model.CompanyDB;

import java.sql.*;

public class CompanyRepositoryJDBC implements CompanyRepository {
    private static final String SQL_INSERT_COMPANY = "INSERT INTO company(\"name\") values (?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM company WHERE id = ?";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM company where id = ?";
    private final Connection connection;

    public CompanyRepositoryJDBC(String connectionString, String user, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString, user, pass);
    }

    @Override
    public int create(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);
        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getInt(1);
    }

    @Override
    public CompanyDB getById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next(); // 1

        return new CompanyDB(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBoolean("is_active")
        );
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();

    }

    // ошибка
    public void close() throws SQLException {
        connection.close();
    }
}
