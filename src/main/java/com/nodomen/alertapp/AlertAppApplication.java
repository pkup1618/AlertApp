package com.nodomen.alertapp;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.GroupAuthResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nodomen.alertapp.repositories",
        entityManagerFactoryRef = "entityManagerFactory_custom",
        transactionManagerRef = "jpaTransactionManager_custom")
public class AlertAppApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(AlertAppApplication.class, args);

        VkApiClient vk = ctx.getBean("VkApiClient_custom", VkApiClient.class);

    }


    //FOR JPA BEANS
    @Bean("dataSource_custom")
    public DataSource dataSource() {

        return DataSourceBuilder
                .create()
                .username("postgres")
                .password("password")
                .url("jdbc:postgresql://localhost:5432/alertApp_db?currentSchema=alertapp")
                .driverClassName("org.postgresql.Driver").build();
    }


    @Bean("jpaTransactionManager_custom")
    public PlatformTransactionManager transactionManager() {

        return new JpaTransactionManager(entityManagerFactory());
    }


    @Bean("VkApiClient_custom")
    public VkApiClient vkApiClient() {
        return new VkApiClient(new HttpTransportClient());
    }


//    @Bean("CommunityAuth")
//    public GroupAuthResponse authResponse = vkApiClient()
//            .oAuth().groupAuthorizationCodeFlow()


    @Bean("hibernateJpaVendorAdapter_custom")
    public JpaVendorAdapter jpaVendorAdapter() {

        return new HibernateJpaVendorAdapter();
    }


    @Bean("hibernateProperties_custom")
    public Properties hibernateProperties() {

        Properties hibernateProp = new Properties();

        try {
            hibernateProp.load(new FileReader("src/main/resources/hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hibernateProp;
    }


    @Bean("entityManagerFactory_custom")
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPackagesToScan("com.nodomen.alertapp.models");
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
}