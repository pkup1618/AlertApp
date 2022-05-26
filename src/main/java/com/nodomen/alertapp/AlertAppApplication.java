package com.nodomen.alertapp;

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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nodomen.alertapp.repositories")
public class AlertAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AlertAppApplication.class, args);

//        for (String bean : ctx.getBeanDefinitionNames()) {
//            System.out.println(bean);
//            System.out.println(ctx.containsBeanDefinition(bean));
//        }


    }


//    SECURITY BEANS
//    Оказывается, конфигурировать Spring Security нужно обязательно не здесь.
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .headers()
//                .frameOptions().disable().and()
//                .authorizeRequests()
//                .antMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated().and()
//                .formLogin()
//                .loginPage("/user/login").permitAll()
//                .defaultSuccessUrl("/index").and()
//                .logout()
//                .logoutUrl("/user/logout").and()
//                .build();
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withUsername("user").password("password").roles("USER").build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


    //WEB MVC BEANS
    @Bean
    WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("resources/**").addResourceLocations("classpath:/WEB-INF/jsp/");
            }
        };
    }


    //FOR JPA BEANS
    @Bean
    public DataSource dataSource() {

//      DataSource - база для взаимодействия с базой данных. На нём строится jdbc.
//      Поверх него создаются другие объекты

        return DataSourceBuilder
                .create()
                .username("postgres")
                .password("password")
                .url("jdbc:postgresql://localhost:5432/alertApp_db?currentSchema=alertapp")
                .driverClassName("org.postgresql.Driver").build();
    }


    @Bean
    public PlatformTransactionManager transactionManager() {

//      JpaTransactionManager - это диспетчер транзакций, поставляемый Spring специально для JPA

        return new JpaTransactionManager(entityManagerFactory());
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

//      В JPA есть интерфейс JpaVendorAdapter, HibernateJpaVendorAdapter - его реализация из Hibernate

        return new HibernateJpaVendorAdapter();
    }


    @Bean
    public Properties hibernateProperties() {

        Properties hibernateProp = new Properties();

        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);

        return hibernateProp;
    }


    @Bean
    public EntityManagerFactory entityManagerFactory() {

//      LocalContainerEntityManagerFactoryBean -
//      в него внедряется DataSource, HibernateJpaVendorAdapter, указывается где смотреть Entity
//      в свойстве jpaProperties устанавливаются подробности конфигурации поставщика услуг сохраняемости из Hibernate

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPackagesToScan("com.nodomen.alertapp.models");
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
}
