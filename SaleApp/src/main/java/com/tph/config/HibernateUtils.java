/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tph.config;

import com.tph.pojo.Category;
import com.tph.pojo.Product;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Environment;

/**
 *
 * @author Admin
 */
public class HibernateUtils {
    private static final SessionFactory FACTORY;
    
    static {
        Configuration conf = new Configuration();
        
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        
        Properties props = new Properties();
        props.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.setProperty(Environment.URL, "jdbc:mysql://localhost/saledb");
        props.setProperty(Environment.USER, "root");
        props.setProperty(Environment.PASS, "12345678");
        props.setProperty(Environment.SHOW_SQL, "true");
        
        conf.setProperties(props);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}