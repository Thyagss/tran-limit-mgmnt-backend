package com.transaction.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class DBconfig {

    private static final HikariDataSource dataSource;

    static {

        try {
            Properties prop = new Properties();
            InputStream input = DBconfig.class.getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("db.url"));
            config.setPassword(prop.getProperty("db.username"));
            config.setUsername(prop.getProperty("db.password"));
            config.setDriverClassName(prop.getProperty("db.driver"));

            config.setMaximumPoolSize(Integer.parseInt(prop.getProperty("hikari.maxPoolSize")));
            config.setMinimumIdle(Integer.parseInt(prop.getProperty("hikari.minIdle")));
            config.setIdleTimeout(Integer.parseInt(prop.getProperty("hikari.idleTimeout")));
            config.setMaxLifetime(Integer.parseInt(prop.getProperty("hikari.maxLifetime")));
            config.setConnectionTimeout(Integer.parseInt(prop.getProperty("hikari.connectionTimeout")));

            config.setPoolName(prop.getProperty("hikari.pool.name"));

            dataSource = new HikariDataSource(config);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public static HikariDataSource getDataSource() {
            return dataSource;
        }
}
