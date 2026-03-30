package com.transaction.util;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.Connection;

public class LiquibaseRunner {

    private static final Logger logger = LoggerFactory.getLogger(LiquibaseRunner.class);

    public static void run(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));

            Liquibase liquibase = new Liquibase(
                    "db/changelog/db.changelog-master.xml",
                    new ClassLoaderResourceAccessor(),
                    database);

            liquibase.update();
            logger.info("Liquibase migrations executed successfully");

        } catch (Exception e) {
            logger.error("Liquibase migration failed", e);
            throw new RuntimeException("Liquibase migration failed", e);
        }
    }
}