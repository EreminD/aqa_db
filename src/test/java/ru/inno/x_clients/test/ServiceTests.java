package ru.inno.x_clients.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.x_clients.db.CompanyRepository;
import ru.inno.x_clients.db.jdbc.CompanyRepositoryJDBC;
import ru.inno.x_clients.db.jdbc.model.CompanyDB;
import ru.inno.x_clients.http.WebClient;
import ru.inno.x_clients.http.model.Company;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServiceTests {
    private WebClient client;
    private CompanyRepository repository;

    private int companyIdToDelete;

    @BeforeEach
    public void setUp() throws SQLException {
        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

        repository = new CompanyRepositoryJDBC(connectionString, user, pass);
        client = new WebClient();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        repository.deleteById(companyIdToDelete);
        repository.close();
    }

    @Test
    public void iCanCreateCompany() throws SQLException {
        String token = client.getToken("leonardo", "leads");

        companyIdToDelete = client.createCompany("Рога и копыта", "", token);

        CompanyDB company = repository.getById(companyIdToDelete);

        assertEquals(companyIdToDelete, company.id());
        assertEquals("Рога и копыта", company.name());
        assertEquals("", company.description());
        assertTrue(company.isActive());
    }

    @Test
    public void iCanReadCompanyInfo() throws SQLException {
        String companyName = "Удалите меня!";
        companyIdToDelete = repository.create(companyName);
        Company jsonCompany = client.getCompanyInfo(companyIdToDelete);

        assertEquals(companyName, jsonCompany.name());
        assertTrue(jsonCompany.isActive());
    }
}
