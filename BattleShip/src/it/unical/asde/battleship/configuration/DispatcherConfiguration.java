package it.unical.asde.battleship.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("it.unical.asde.battleship.components")
public class DispatcherConfiguration implements WebMvcConfigurer
{

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }

    @Bean
    public DataSource getDataSource()
    {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:MyDB");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory()
    {

        final LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(getDataSource());
        lsfb.setHibernateProperties(getHibernateProperties());
        lsfb.setPackagesToScan("it.unical.asde.battleship.model");

        try
        {
            lsfb.afterPropertiesSet();
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        return lsfb.getObject();

    }

    @Bean
    public InternalResourceViewResolver viewResolver()
    {
        return new InternalResourceViewResolver("WEB-INF/views/", ".jsp");
    }

    private Properties getHibernateProperties()
    {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.put("hibernate.show_sql", true);
		prop.put("hibernate.format_sql", true);
		prop.put("hibernate.hbm2ddl.auto", "create");
		prop.put("hibernate.current_session_context_class", "thread");
		return prop;
	}

}
