import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = ["cat"], entityManagerFactoryRef = "emf")
@ComponentScan(basePackages = ["cat"])
class ConfigMain {

    @Bean
    DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
                .addScript("create-db.sql")
                .addScript("insert-data.sql")
                .build()
        return db;
    }

    @Bean(name = "emf")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter adapter) {
        def factoryBean = new LocalContainerEntityManagerFactoryBean()
        factoryBean.setPackagesToScan("cat")
        factoryBean.setJpaVendorAdapter(adapter)
        factoryBean.setDataSource(ds)
        return factoryBean
    }
    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL)
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect")
        return vendorAdapter

    }
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
