package com.dira.multidb.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dira.multidb.database1.entity.Address;
import com.dira.multidb.database1.entity.Contact;
import com.dira.multidb.database1.entity.User;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.dira.multidb.database1", entityManagerFactoryRef = "database1EntityManagerFactory", transactionManagerRef = "database1TransactionManager") // berfungsi untuk mengaktifkan repositori JPA
public class Database1DatasourceConfig {
    @Bean 
    @ConfigurationProperties("spring.datasource.database1")
    public DataSourceProperties database1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.database1.configuration")
    public DataSource database1DataSource() {
        return database1DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        return new EntityManagerFactoryBuilder(vendorAdapter, new HashMap<>(), null);
    }

    @Primary
    @Bean(name = "database1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean database1EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(database1DataSource())
                .packages(User.class, Address.class, Contact.class)
                .build();
    }

    @Primary
    @Bean(name = "database1TransactionManager")
    public PlatformTransactionManager database1TransactionManager(
        final @Qualifier("database1EntityManagerFactory") LocalContainerEntityManagerFactoryBean database1EntityManagerFactory) {
        return new JpaTransactionManager(database1EntityManagerFactory.getObject());
    }

}

// @Configuration:
// Fungsi: Menandakan bahwa kelas tersebut adalah kelas konfigurasi Spring.
// Penjelasan: Anotasi ini digunakan untuk menandai bahwa kelas Database2DatasourceConfig adalah kelas konfigurasi Spring yang akan digunakan untuk mengkonfigurasi komponen-komponen aplikasi.

// @ConfigurationProperties
// berfungsi untuk mengambil properti database1 dari application.properties

// @EnableTransactionManagement:
// Fungsi: Mengaktifkan manajemen transaksi(query database) Spring di kelas tersebut.
// Penjelasan: Anotasi ini digunakan untuk mengaktifkan manajemen transaksi Spring di kelas Database2DatasourceConfig, yang memungkinkan penggunaan fitur-fitur transaksi Spring di dalamnya.

// @EnableJpaRepositories:
// Fungsi: Mengaktifkan repositori JPA di paket yang ditentukan.
// Penjelasan: Anotasi ini digunakan untuk mengaktifkan repositori JPA di dalam paket com.dira.multidb.database2, sehingga memungkinkan Spring Data JPA untuk mengelola repositori JPA dalam paket tersebut.

// @Bean:
// Fungsi: Menandakan bahwa metode yang dianotasi akan menghasilkan bean yang akan dikelola oleh kontainer Spring.
// Penjelasan: Anotasi ini digunakan untuk menandai metode-metode yang menghasilkan bean, seperti database2DataSourceProperties(), database2DataSource(), database2EntityManagerfactory(), dan database2TransactionManager().

// @Qualifier:
// Fungsi: Digunakan untuk memilih bean yang tepat ketika ada beberapa bean dari jenis yang sama.
// Penjelasan: Anotasi ini digunakan untuk menentukan kualifikasi bean yang akan digunakan, misalnya dalam metode database2TransactionManager() untuk memilih database2EntityManagerFactory sebagai sumber EntityManagerFactory.