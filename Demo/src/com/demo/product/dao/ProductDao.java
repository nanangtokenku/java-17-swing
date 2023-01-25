/*
 * ProductDao.java
 *
 * Created on Jan 23, 2023, 16:09
 */
package com.demo.product.dao;

import com.demo.db.DBConnection;
import com.demo.product.domain.entity.Product;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

/**
 * @author Nanang, Irfin A
 */
public class ProductDao
{
    private Connection connection;

    public ProductDao(Connection _connection)
    {
        connection = _connection;
    }

    public void create(Product p) throws Exception
    {
        // SQL Insert to table product
        var sql = "INSERT INTO product (code, name) VALUES (?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, p.getCode());
        stmt.setString(2, p.getName());
        stmt.executeUpdate();

        stmt.close();
    }

    public void update(Product _oldProduct, Product _newProduct) throws Exception
    {
        // SQL Update table product
        var sql = "UPDATE product SET code = ?, name = ? WHERE code = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, _newProduct.getCode());
        stmt.setString(2, _newProduct.getName());
        stmt.setString(3, _oldProduct.getCode());
        stmt.executeUpdate();

        stmt.close();
    }

    public List<Product> findByNameLike(String _name) throws Exception
    {
        // SQL Select from table product
        var sql = "SELECT * FROM product WHERE name LIKE ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + _name + "%");
        ResultSet rs = stmt.executeQuery();

        List<Product> list = new LinkedList<>();
        while (rs.next()) {
            Product p = new Product();
            p.setCode(rs.getString("code"));
            p.setName(rs.getString("name"));
            list.add(p);
        }
        stmt.close();

        return list;
    }
    public List<Product> all() throws Exception
    {
        // SQL Select from table product
        var sql = "SELECT * FROM product";

        PreparedStatement stmt = connection.prepareStatement(sql);
        //stmt.setString(1, "%" + _name + "%");
        ResultSet rs = stmt.executeQuery();

        List<Product> list = new LinkedList<>();
        while (rs.next()) {
            Product p = new Product();
            p.setCode(rs.getString("code"));
            p.setName(rs.getString("name"));
            list.add(p);
        }
        stmt.close();

        return list;
    }

    public Optional<Product> findByCode(String _code) throws Exception
    {
        // SQL Select from table product
        var sql = "SELECT * FROM product WHERE code = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, _code);
        ResultSet rs = stmt.executeQuery();

        Product p = null;
        if (rs.next()) {
            p = new Product();
            p.setCode(rs.getString("code"));
            p.setName(rs.getString("name"));
        }
        stmt.close();

        return Optional.ofNullable(p);
    }

    public void listProduct()
    {
        // SQL Select from table product
        var sql = "SELECT * FROM product";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Code: " + rs.getString("code"));
                System.out.println("Name: " + rs.getString("name"));
            }
            stmt.close();
        }
        catch (Exception x) {
            x.printStackTrace(System.out);
        }
    }

    public void delete(String _code) throws Exception
    {
        // SQL Delete from table product
        var sql = "DELETE FROM product WHERE code = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, _code);
        stmt.executeUpdate();

        stmt.close();
    }

    public DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    public ResultSet getQueryResult() {
        // SQL Select from table product
        var sql = "SELECT * FROM product";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                System.out.println("Code: " + rs.getString("code"));
                System.out.println("Name: " + rs.getString("name"));
            }
            return rs;
        }
        catch (Exception x) {
            x.printStackTrace(System.out);
        }

        return null;
    }
}
