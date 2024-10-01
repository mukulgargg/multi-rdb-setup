package com.multirdbsetup.datatransfer.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.multirdbsetup.datatransfer.primaryRepo"}, entityManagerFactoryRef =
	"primaryEntityManagerFactory", transactionManagerRef = "primaryTransactionManager")
@EntityScan("com.multirdbsetup.datatransfer.primaryDataSourceEntity")
public class PrimaryDataSourceConfig {
	
	@Bean
	public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
		return hibernateProperties -> {
			// Set the naming strategy to convert field names to uppercase with underscores
			hibernateProperties.put("hibernate.naming.physical_naming_strategy",
				UnderscorePhysicalStartegy.class.getName());
			hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
			hibernateProperties.put("hibernate.auto-commit", "true");
			hibernateProperties.put("hibernate.show-sql", "true");
			hibernateProperties.put("hibernate.allow_update_outside_transaction", "true");
		};
	}
	
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "primaryEntityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder primaryentityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
		@Qualifier("primaryEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
		@Qualifier("primaryDataSource") DataSource primaryDataSource) {
		Map<String, String> hibernateProperties = new HashMap<>();
		hibernateProperties.put("hibernate.naming.physical_naming_strategy",
			UnderscorePhysicalStartegy.class.getName());
		hibernateProperties.put("hibernate.auto-commit", "true");
		hibernateProperties.put("hibernate.show-sql", "true");
		return builder
			.dataSource(primaryDataSource)
			.packages("com.multirdbsetup.datatransfer.primaryDataSourceEntity",
				"com.multirdbsetup.datatransfer.primaryRepo")
			.persistenceUnit("primaryEntityManager")
			.properties(hibernateProperties)
			.build();
	}
	
	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager transactionManager(
		@Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(primaryEntityManagerFactory.getObject()));
	}
}
