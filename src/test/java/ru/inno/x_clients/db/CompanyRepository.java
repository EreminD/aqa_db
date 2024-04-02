package ru.inno.x_clients.db;

import ru.inno.x_clients.db.jdbc.model.CompanyDB;

import java.sql.SQLException;

public interface CompanyRepository {
    int create(String name) throws SQLException;

    CompanyDB getById(int id) throws SQLException;

    void deleteById(int id) throws SQLException;

    void close() throws SQLException;
}
