/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.product.domain.service;

import com.demo.db.DBConnection;
import com.demo.product.dao.ProductDao;
import com.demo.product.domain.entity.Product;

import java.sql.Connection;

/**
 *
 * @author Nanang, Irfin A
 */
public class ProductService
{
    public void save(Product p) throws RuntimeException
    {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            ProductDao dao = new ProductDao(con);
            dao.create(p);
            
            // Commit TX
            con.commit();
        }
        catch (Exception x)
        {
            x.printStackTrace(System.out);

            // Rollback TX
            try {
                if (con != null)
                    con.rollback();
            }
            catch (Exception y) {
                throw new RuntimeException(y);
            }
            throw new RuntimeException(x);
        }
    }

    //list product service
public void listProduct() throws RuntimeException
    {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            ProductDao dao = new ProductDao(con);
            dao.listProduct();

            // Commit TX
            //con.commit();
        }
        catch (Exception x)
        {
            x.printStackTrace(System.out);

            // Rollback TX
            try {
                //if (con != null)
                    //con.rollback();
            }
            catch (Exception y) {
                throw new RuntimeException(y);
            }
            throw new RuntimeException(x);
        }
    }







}
