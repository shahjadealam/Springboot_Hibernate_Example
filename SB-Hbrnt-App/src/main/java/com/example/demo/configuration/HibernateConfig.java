package com.example.demo.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String drivername;

	@Value("${spring.datasource.url}")
	private String dburl;

	@Value("${spring.datasource.username}")
	private String dbusername;

	@Value("${spring.datasource.password}")
	private String dbpassword;

	@Value("${spring.jpa.show-sql}")
	private String showsql;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;

	@Value("${spring.jpa.hibernate.ddl}")
	private String ddl;

	@Value("${spring.jpa.hibernate.packagetoscan}")
	private String packageToScan;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(packageToScan);
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = DataSourceBuilder.create().username(dbusername).password(dbpassword).url(dburl)
				.driverClassName(drivername).build();
		return dataSource;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", ddl);
		hibernateProperties.setProperty("hibernate.dialect", dialect);

		return hibernateProperties;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
