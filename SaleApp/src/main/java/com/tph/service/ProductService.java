/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tph.service;

import com.tph.config.HibernateUtils;
import com.tph.pojo.Category;
import com.tph.pojo.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.DoubleStream;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ProductService {
    
    public List<Product> getProductsBase(String [] args, ProducerChecker c) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root root = query.from(Product.class);
            query.select(root);
            
            query = c.getWhere(builder, query, root, args);
            
            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }
    
    public List<Product> getProducts(String kw) {
//        return getProductsBase(new String[] {kw}, (builder, query, root, args) -> {
//            Predicate p = builder.like(root.get("name").as(String.class),
//                        String.format("%%%s%%", args[0]));
//                query = query.where(p);
//                
//                return query;
//        });
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root root = query.from(Product.class);
            query.select(root);
            
            if (kw != null && !kw.isEmpty()) {
                Predicate p = builder.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw));
                query = query.where(p);
            }
            
            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }
    
    public List<Product> getProducts(BigDecimal fromPrice, BigDecimal toPrice) {
//        return getProductsBase(new String[] {String.valueOf(fromPrice), String.valueOf(toPrice)},
//                (builder, query, root, args) -> {
//        });
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root root = query.from(Product.class);
            query.select(root);
            
            if (fromPrice != null && toPrice != null) {
                Predicate p = builder.between(root.get("price").as(BigDecimal.class),
                        fromPrice, toPrice);
                query = query.where(p);
            }
            
            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }
    
    public List<Object> getStatus() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            
            Root rootP = query.from(Product.class);
            Root rootC = query.from(Category.class);
            
            query = query.where(builder.equal(rootP.get("category"), rootC.get("id")));
            
            query.multiselect(rootC.get("name").as(String.class),
                    builder.count(rootP.get("name").as(Integer.class)),
                    builder.max(rootP.get("price").as(BigDecimal.class)),
                    builder.min(rootP.get("price").as(BigDecimal.class)),
                    builder.avg(rootP.get("price").as(Double.class)));
            
            query = query.groupBy(rootC.get("name").as(String.class));
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
}

interface ProducerChecker {
    CriteriaQuery getWhere(CriteriaBuilder builder, CriteriaQuery query, Root root, String [] args);
}
