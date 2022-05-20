package com.nodomen.alertapp.old_web;

import com.nodomen.alertapp.AlertAppApplication;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/*
Унаследовавшись от подобного класса мы создаём глобальную конфигурацию сервлетов и в то же
время конфигурацию конретного сервлета. В каждом классе можно сконфигурировать один DispatcherServlet.

Причём вторая разделяется на ту, которую можно описать конкретно здесь,
и вторую, которая поставляется с наследником WebMvcConfigurer, в моём случае наследник
называется WebConfig
 */

public class MyWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    Здесь глобальная конфигурация для всех DispatcherServlet-ов,
    её следует написать только в одном из них, в других оставить пустой
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                AlertAppApplication.class
        };
    }


    /*
    Этот и другие методы для конфигурации конкретно этого сервлета.
    Общий путь под него, фильтры и прочее.
    Полный список возможных конфигураций раскроется если посмотреть на методы,
    которые можно переопределять.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                WebConfig.class
        };
    }


    @Override
    protected String[] getServletMappings() {
        return new String[] { "/app/*" };
    }
}
