package ua.edu.sumdu.j2ee.zykov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    @Value("classpath:database/schema.sql")
    private Resource SCHEMA_SCRIPT;

    @Bean
    public JdbcTemplate getJdbcTemplate() throws NamingException {
        return new JdbcTemplate(getDataSource());
    }

    @Bean(destroyMethod = "")
    public DataSource getDataSource() throws NamingException{
        Context context = new InitialContext();
        return (DataSource)context.lookup("NCShopOnlineJNDIDB");
    }

    @Autowired
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(SCHEMA_SCRIPT);
        return populator;
    }
}
