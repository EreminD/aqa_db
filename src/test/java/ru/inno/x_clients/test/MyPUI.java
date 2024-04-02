package ru.inno.x_clients.test;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.jpa.HibernatePersistenceProvider;
import ru.inno.x_clients.db.jpa.entity.CompanyEntity;
import ru.inno.x_clients.db.jpa.entity.EmployeeEntity;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class MyPUI implements PersistenceUnitInfo {

    private final Properties props;

    public MyPUI(Properties props) {
        this.props = props;
    }

    @Override
    public Properties getProperties() {
        return props;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(
                CompanyEntity.class.getName(),
                EmployeeEntity.class.getName()
                // Author.class.getName(),
                // Book.class.getName(),
                // Chapter.class.getName(),
                // Genre.class.getName(),
                // Section.class.getName()
        );
    }

    @Override
    public String getPersistenceUnitName() {
        return "TestPUI";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return HibernatePersistenceProvider.class.getName();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return null;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }


    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
