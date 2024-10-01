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
@EnableJpaRepositories(basePackages = "com.multirdbsetup.datatransfer.thirdRepo", entityManagerFactoryRef =
	"thirdEntityManagerFactory", transactionManagerRef = "ThirdTransactionManager")
@EntityScan("com.multirdbsetup.datatransfer.ThirdDataSourceEntity")
public class ThirdDataSourceConfig {
	
	@Bean(name = "thirdDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.third")
	public DataSource thirdDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
	@Bean(name = "thirdJdbcTemplate")
	public JdbcTemplate thirdJdbcTemplate(@Qualifier("thirdDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	private Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.setProperty("hibernate.legacy_limit_handler", "true");
		properties.setProperty("hibernate.show-sql", "true");
		return properties;
	}
	
	@Bean(name = "thirdEntityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder thirdEntityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Bean(name = "thirdEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(
		@Qualifier("thirdEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
		@Qualifier("thirdDataSource") DataSource thirdDataSource) {
		Map<String, String> hibernateProperties = new HashMap<>();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		hibernateProperties.put("hibernate.legacy_limit_handler", "true");
		hibernateProperties.put("hibernate.show-sql", "true");
		return builder
			.dataSource(thirdDataSource)
			.packages("com.multirdbsetup.datatransfer.ThirdDataSourceEntity",
				"com.multirdbsetup.datatransfer.thirdRepo")
			.persistenceUnit("thirdEntityManager")
			.properties(hibernateProperties)
			.build();
	}
	
	@Bean(name = "ThirdTransactionManager")
	public PlatformTransactionManager transactionManager(
		@Qualifier("thirdEntityManagerFactory") LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory) {
		return new JpaTransactionManager(thirdEntityManagerFactory.getObject());
	}
}