package hhh13432;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;

public class Application {

	public static void main(String[] args) {
		DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test", "sa", "sa");
		PersistenceProvider provider = new HibernatePersistenceProvider();
		PersistenceUnitInfo info = new Info(dataSource);
		Map<String, Object> properties = new LinkedHashMap<>();
		properties.put(Environment.DIALECT, H2Dialect.class.getName());
		EntityManagerFactory entityManagerFactory = provider.createContainerEntityManagerFactory(info, properties);
		System.out.println(entityManagerFactory.getProperties().get("javax.persistence.nonJtaDataSource"));
	}

}
