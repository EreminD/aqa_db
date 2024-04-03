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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class JpaDemo {

    private EntityManager entityManager;

    @BeforeEach
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(Path.of("src/test/resources/db.properties").toFile()));

        String connectionString = properties.getProperty("db.connection_string");
        String user = properties.getProperty("db.user");
        String pass = properties.getProperty("db.pass");

        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", connectionString);
        props.put("hibernate.connection.username", user);
        props.put("hibernate.connection.password", pass);
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.connection.autocommit", "true");
        props.put("hibernate.show_sql", properties.getProperty("db.show_sql"));
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.hbm2ddl.auto", properties.getProperty("db.strategy")); //update

        PersistenceUnitInfo persistenceUnitInfo = new MyPUI(props);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, persistenceUnitInfo.getProperties());
        entityManager = factory.createEntityManager();

    }

    @Test
    public void iCanGetCompanyById() {
        CompanyEntity company2779 = entityManager.find(CompanyEntity.class, 3448);
        assertNotNull(company2779);
        assertEquals(3448, company2779.getId());
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
//        company.setDescription("The best JPA implements");

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

    @Test
    public void createEmps(){
        CompanyEntity company = new CompanyEntity();
        company.setName("Inno");
//        company.setDescription("Courses");
        company.setActive(true);

        entityManager.getTransaction().begin();
        entityManager.persist(company); // назначился id
        entityManager.getTransaction().commit();

        EmployeeEntity employee1 = new EmployeeEntity();
//        employee1.setCompanyId(company.getId());
        employee1.setFirstName("Steve");
        employee1.setLastName("Rogers");
        employee1.setPhone("+7987654345678");
        employee1.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        employee1.setChangeTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        EmployeeEntity employee2 = new EmployeeEntity();
//        employee2.setCompanyId(company.getId());
        employee2.setFirstName("Natasha");
        employee2.setLastName("Romanov");
        employee2.setPhone("+745678934");
        employee2.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        employee2.setChangeTimestamp(Timestamp.valueOf(LocalDateTime.now()));


        entityManager.getTransaction().begin();
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.getTransaction().commit();
    }

    @Test
    public void iCanGetCompanyAndEmpList(){
        int id = 3448;

        CompanyEntity company = entityManager.find(CompanyEntity.class, id);

        TypedQuery<EmployeeEntity> query = entityManager.createQuery("select obj from EmployeeEntity obj where obj.companyId = :comp_id", EmployeeEntity.class);
        query.setParameter("comp_id", company.getId());
        List<EmployeeEntity> emps = query.getResultList();

    }

    private List<CompanyEntity> getCompaniesByName(String companyName) {
        TypedQuery<CompanyEntity> getByName = entityManager.createQuery("SELECT obj FROM CompanyEntity obj WHERE obj.name=:desiredName", CompanyEntity.class);
        getByName.setParameter("desiredName", companyName);
        return getByName.getResultList();
    }
}
