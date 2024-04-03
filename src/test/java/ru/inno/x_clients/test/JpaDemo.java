package ru.inno.x_clients.test;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.x_clients.db.jpa.MyPUI;
import ru.inno.x_clients.db.jpa.entity.CompanyEntity;
import ru.inno.x_clients.db.jpa.entity.EmployeeEntity;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class JpaDemo {

    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", connectionString);
        props.put("hibernate.connection.username", user);
        props.put("hibernate.connection.password", pass);
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.connection.autocommit", "true");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");

        PersistenceUnitInfo persistenceUnitInfo = new MyPUI(props);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, persistenceUnitInfo.getProperties());
        entityManager = factory.createEntityManager();
    }

    @Test
    public void iCanGetCompanyById() {
        CompanyEntity company2779 = entityManager.find(CompanyEntity.class, 2779);
        assertNotNull(company2779);
        assertEquals(2779, company2779.getId());
    }

    @Test
    public void iCanGetEmployeeById() {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, 2402);
        assertNotNull(employee);
        assertEquals(2402, employee.getId());
    }

    @Test
    public void iCanGetCompanyByName() {
        String companyName = "Java test";
        List<CompanyEntity> companyList = getCompaniesByName(companyName);

        assertNotNull(companyList);
        assertTrue(companyList.size() > 0);
        companyList.forEach(companyEntity -> assertEquals(companyName, companyEntity.getName()));
    }

    @Test
    public void iCanCreateCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setName("Hibernate inc. LTD");
        company.setDescription("The best JPA implements");

        entityManager.getTransaction().begin();
        entityManager.persist(company);

        List<CompanyEntity> list = getCompaniesByName("Hibernate inc.");

        assertEquals(1, list.size());
        assertEquals("Hibernate inc.", list.get(0).getName());
        assertEquals("The best JPA implements", list.get(0).getDescription());
    }

    @Test
    public void iCanDeleteCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setName("Delete me");

        entityManager.getTransaction().begin();
        entityManager.persist(company); // назначился id
        entityManager.getTransaction().commit();

        entityManager.remove(company);

        CompanyEntity shouldBeEmpty = entityManager.find(CompanyEntity.class, company.getId());
        assertNull(shouldBeEmpty);
    }

    @Test
    public void iCanDeleteCompanyById() {
        int id = 3226;

        // 1
        CompanyEntity toDelete = entityManager.find(CompanyEntity.class, id);
        entityManager.remove(toDelete);

        // 2
        Query query = entityManager.createQuery("DELETE FROM CompanyEntity obj WHERE obj.id=:idToDelete");
        query.setParameter("idToDelete", id);
    }

    private List<CompanyEntity> getCompaniesByName(String companyName) {
        TypedQuery<CompanyEntity> getByName = entityManager.createQuery("SELECT obj FROM CompanyEntity obj WHERE obj.name=:desiredName", CompanyEntity.class);
        getByName.setParameter("desiredName", companyName);
        return getByName.getResultList();
    }
}
