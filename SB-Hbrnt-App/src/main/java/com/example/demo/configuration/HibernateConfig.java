package com.example.demo.configuration;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
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

	@Value("${spring.jpa.properties.hibernate.dialect")
	private String dialect;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Value("${spring.jpa.hibernate.packagetoscan}")
	private String packageToScan;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = DataSourceBuilder.create().username(dbusername).password(dbpassword).url(dburl)
				.driverClassName(drivername).build();
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages("com.example.demo.*");
		return sessionBuilder.buildSessionFactory();
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		return initializer;
	}
	/*
	 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
	 * new DriverManagerDataSource(); dataSource.setUrl(dburl);
	 * dataSource.setDriverClassName(drivername);
	 * dataSource.setUsername(dbusername); dataSource.setPassword(dbpassword);
	 * 
	 * return dataSource; }
	 */

	/*
	 * 
	 * @Bean public LocalSessionFactoryBean sessionFactory() {
	 * 
	 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource());
	 * sessionFactory.setPackagesToScan(packageToScan); Properties properties = new
	 * Properties(); properties.put("hibernate-dialect", dialect);
	 * properties.put("hibernate-show-sql", showsql);
	 * properties.put("hibernate-ddl-auto", ddl);
	 * 
	 * sessionFactory.setHibernateProperties(properties);
	 * 
	 * return sessionFactory;
	 * 
	 * }
	 * 
	 * @Bean public HibernateTransactionManager transactionManager() {
	 * HibernateTransactionManager hibernateTransactionManager = new
	 * HibernateTransactionManager();
	 * hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
	 * 
	 * return hibernateTransactionManager;
	 * 
	 * }
	 */
}