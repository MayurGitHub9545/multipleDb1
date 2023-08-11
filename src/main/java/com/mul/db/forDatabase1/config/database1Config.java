package com.mul.db.forDatabase1.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "secondentityManagerFactoryBean", transactionManagerRef = "secondtransactionManager", basePackages = {
		"com.mul.db.forDatabase1.Repo" })
public class database1Config {


	@Autowired
	private Environment enviroment;

	// datasource

	@Bean(name = "secondDataSource")
	@Primary

	public DataSource dataSource() {

		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl(enviroment.getProperty("spring.datasource.url"));
		datasource.setDriverClassName(enviroment.getProperty("spring.datasource.driver-class-name"));
		datasource.setUsername(enviroment.getProperty("spring.datasource.username"));
		datasource.setPassword(enviroment.getProperty("spring.datasource.password"));

		return datasource;
	}

	// entityManagerFactory

	@Bean("secondentityManagerFactoryBean")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.mul.db.forDatabase1.entities");
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		
		bean.setJpaVendorAdapter(adapter);
		
		Map<String,String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		 
		
		bean.setJpaPropertyMap(props);
		 
		
		return bean;

	}

	// platformtransactionManager
	
	@Primary
	@Bean(name = "secondtransactionManager")
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
		
	}
}
