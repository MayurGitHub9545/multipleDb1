package com.mul.db.forDatabase2.config;

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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryBean", transactionManagerRef = "transactionManager", basePackages = {
		"com.mul.db.forDatabase2.repo" })
public class database2Config {

	@Autowired
	private Environment enviroment;

	// datasource

	@Bean
	@Primary

	public DataSource dataSource() {

		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl(enviroment.getProperty("second.datasource.url"));
		datasource.setDriverClassName(enviroment.getProperty("second.datasource.driver-class-name"));
		datasource.setUsername(enviroment.getProperty("second.datasource.username"));
		datasource.setPassword(enviroment.getProperty("second.datasource.password"));

		return datasource;
	}

	// entityManagerFactory

	@Bean("entityManagerFactoryBean")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.mul.db.forDatabase2.enties");
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		
		bean.setJpaVendorAdapter(adapter);
		
		Map<String,String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.dialect", "");
		
		bean.setJpaPropertyMap(props);
		 
		
		return bean;

	}

	// platformtransactionManager
	
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
		
	}
}
