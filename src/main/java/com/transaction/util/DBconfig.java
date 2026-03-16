package com.transaction.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBconfig {

        private static final HikariDataSource dataSource;

        static {

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/transaction_limit_db");
            config.setPassword("Thyags@mysql");
            config.setUsername("root");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setMaxLifetime(1800000);
            config.setConnectionTimeout(30000);

            config.setPoolName("TransactionPool");

            dataSource = new HikariDataSource(config);
        }

        public static HikariDataSource getDataSource() {
            return dataSource;
        }
}
