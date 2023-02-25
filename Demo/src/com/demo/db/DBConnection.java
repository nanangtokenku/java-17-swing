/*
 * DBConnection.java
 *
 * Created on Jan 23, 2023, 16:21
 */
package com.demo.db;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * Kelas ini digunakan untuk menghubungkan aplikasi dengan database.
 *
 * @author Nanang, Irfin
 */
public class DBConnection
{
    private static Connection con;

    public static Connection getConnection() throws Exception
    {
        if (con == null) {
            con = initConnection();
        }
        else if (con.isClosed()) {
            con = initConnection();
        }
        else if (!con.isValid(0)) {
            con = initConnection();
        }

        return con;
    }

    private static Connection initConnection() throws Exception
    {
        if (con == null) {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Buat koneksi ke database
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/price", "price_dba", "Sembilan9");
            con.setAutoCommit(false);
        }

        return con;
    }

    // Tulis DDL untuk tabel product
    // CREATE TABLE product (
    //     code VARCHAR(10) PRIMARY KEY,
    //     name VARCHAR(50) NOT NULL
    // );
}
