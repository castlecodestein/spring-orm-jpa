package com.castlecodestein.orm;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages={"com.castlecodestein.orm.student"})
public class SpringConfiguration {

	/** 
	 * Spring's JPA Entity Manager wrapper.
	 * @param source
	 * @return
	 * @throws PropertyVetoException
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource source) throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(source);
		em.setPackagesToScan(new String[] { "com.castlecodestein.orm" });
		em.setPersistenceUnitName("default");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			LocalContainerEntityManagerFactoryBean entityManagerFactory)
			throws PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory
				.getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * Embedded data source.
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.build();
	}

	/**
	 * Hibernate specific properties.
	 * @return
	 */
	private Properties additionalProperties() {
		return new Properties() {
			{ // Hibernate Specific:
				setProperty("hibernate.dialect",
						"org.hibernate.dialect.H2Dialect");
				// setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.use_sql_comments", "true");
				setProperty("hibernate.format_sql", "true");
				// setProperty("hibernate.generate_statistics", "true");

			}
		};

	}

}
