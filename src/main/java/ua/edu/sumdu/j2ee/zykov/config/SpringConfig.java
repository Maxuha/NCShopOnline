package ua.edu.sumdu.j2ee.zykov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    @Bean
    public JdbcTemplate getJdbcTemplate() throws NamingException {
        return new JdbcTemplate(dataSource());
    }

    @Bean(destroyMethod = "")
    public DataSource dataSource() throws NamingException{
        Context context = new InitialContext();
        return (DataSource)context.lookup("NCShopOnlineJNDIDB");
    }
}
