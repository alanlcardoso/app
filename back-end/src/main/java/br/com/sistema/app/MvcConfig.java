package br.com.sistema.app;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import br.com.sistema.app.entidade.Equipe;
import br.com.sistema.app.repositorio.IEmpresaRepositorio;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = { Equipe.class })
@EnableJpaRepositories(basePackageClasses = { IEmpresaRepositorio.class })
public class MvcConfig implements ApplicationContextAware {
	
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	
	@Value("${spring.datasource.url}")
	private String userBucketPath;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	@ConfigurationProperties("spring.datasource")
	public HikariDataSource dataSource(DataSourceProperties properties) {
		HikariDataSource hikari =  properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
		hikari.setIdleTimeout(30000);
		hikari.setMaximumPoolSize(50);
		hikari.setMinimumIdle(5);
		return hikari;
	}
}