package com.example.projeto.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class JDBCConfig {

	DataSource dataSource;
	String url = "jdbc:postgresql://localhost:5432/shield";
	String user = "postgres";
	String pass = "celinha123";
	

	public JDBCConfig() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(pass);
		
		comboPooledDataSource.setMaxPoolSize(15);
		this.dataSource = comboPooledDataSource;
		
	}

	@Bean
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
		
	}
}
