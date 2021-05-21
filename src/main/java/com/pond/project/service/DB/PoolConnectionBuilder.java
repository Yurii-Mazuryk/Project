package com.pond.project.service.DB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder {
    private static PoolConnectionBuilder poolConnection = null;

    public static PoolConnectionBuilder getInstance() {
        if (poolConnection == null)
            poolConnection = new PoolConnectionBuilder();
        return poolConnection;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ConnectionPool");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
