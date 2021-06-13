package com.example.libarary.connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;

public class MyConnection {
    private static final DruidDataSource source = new DruidDataSource();
    static {
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/library?useSSL=false&allowPublicKeyRetrieval=true");
        source.setUsername("root");
        source.setPassword("xhy711711");
    }
    public static DruidPooledConnection getConn() throws SQLException {
        return source.getConnection();
    }
}
