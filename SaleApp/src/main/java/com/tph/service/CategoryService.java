/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tph.service;

import com.tph.config.HibernateUtils;
import com.tph.pojo.Category;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class CategoryService {
    public List<Category> getCategory() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query q = session.createQuery("FROM Category");
            
            return q.getResultList();
        }
    }
}
