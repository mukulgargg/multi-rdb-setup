package com.multirdbsetup.datatransfer.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mukulgarg
 * @date 03/11/23
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multirdbsetup.datatransfer.secondaryRepo", entityManagerFactoryRef =
	"secondaryEntityManagerFactory", transactionManagerRef = "SecondaryTransactionManager")
@EntityScan("com.multirdbsetup.datatransfer.secondaryDataSourceEntity")
public class SecondaryDataSourceConfig {
	
	@Bean(name = "secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	private Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.legacy_limit_handler", "true");
		properties.setProperty("hibernate.show-sql", "true");
		return properties;
	}
	
	@Bean(name = "secondaryEntityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
		@Qualifier("secondaryEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
		@Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
		Map<String, String> hibernateProperties = new HashMap<>();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		hibernateProperties.put("hibernate.legacy_limit_handler", "true");
		hibernateProperties.put("hibernate.show-sql", "true");
		return builder
			.dataSource(secondaryDataSource)
			.packages("com.multirdbsetup.datatransfer.secondaryDataSourceEntity",
				"com.multirdbsetup.datatransfer.secondaryRepo")
			.persistenceUnit("secondaryEntityManager")
			.properties(hibernateProperties)
			.build();
	}
	
	@Bean(name = "SecondaryTransactionManager")
	public PlatformTransactionManager transactionManager(
		@Qualifier("secondaryEntityManagerFactory")
		LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory) {
		return new JpaTransactionManager(secondaryEntityManagerFactory.getObject());
	}
}