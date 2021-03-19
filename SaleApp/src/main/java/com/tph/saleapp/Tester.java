/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tph.saleapp;

import com.tph.service.CategoryService;
import com.tph.service.ProductService;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class Tester {
    public static void main(String[] args) {
        // Test Category
//        CategoryService s = new CategoryService();
//        s.getCategory().forEach(c -> System.out.printf("%d - %s\n", c.getId(), c.getName()));
        
//        System.out.println("Danh sach san pham");
//        ProductService ps = new ProductService();
//        ps.getProducts().forEach(p -> System.out.printf("%d: %s, %s, %.2f, %s\n",
//                p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getCategory().getName()));

        // Test Product
        ProductService ps = new ProductService();
//        System.out.println("--- TIM KIEM THEO TU KHOA ---");
//        ps.getProducts("iphone").forEach(p -> System.out.println(p.getName()));
//        System.out.println("--- TIM KIEM THEO GIA ---");
//        ps.getProducts(new BigDecimal(10000000), new BigDecimal(20000000)).forEach(p -> System.out.println(p.getName()));

        // Thong ke
        ps.getStatus().forEach(objs -> {
            System.out.printf("%s\n", objs[0]);
            System.out.printf("Count: %d\n", objs[1]);
            System.out.printf("Max: %.1f\n", objs[2]);
            System.out.printf("Min: %.1f\n", objs[3]);
            System.out.printf("Average: %.1f\n", objs[4]);
        });
    }
}
