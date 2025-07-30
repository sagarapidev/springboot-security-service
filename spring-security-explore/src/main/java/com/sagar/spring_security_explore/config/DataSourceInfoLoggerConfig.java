package com.sagar.spring_security_explore.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceInfoLoggerConfig {
    @Bean
    public CommandLineRunner logDataSourceInfo(DataSource dataSource) {
        return args -> {
            // Only log if not already logged (use a static flag)
            if (System.getProperty("hikari.datasource.info.logged") == null) {
                System.setProperty("hikari.datasource.info.logged", "true");
                System.out.println("==== DataSource Info ====");
                if (dataSource instanceof HikariDataSource hikari) {
                    System.out.println("JDBC URL: " + hikari.getJdbcUrl());
                    System.out.println("Driver: " + hikari.getDriverClassName());
                    System.out.println("Pool Name: " + hikari.getPoolName());
                    System.out.println("Min Idle: " + hikari.getMinimumIdle());
                    System.out.println("Max Pool Size: " + hikari.getMaximumPoolSize());
                    System.out.println("Auto Commit: " + hikari.isAutoCommit());
                    int isolation = Integer.parseInt(hikari.getTransactionIsolation());
                    String isolationLevel = switch (isolation) {
                        case java.sql.Connection.TRANSACTION_NONE -> "TRANSACTION_NONE";
                        case java.sql.Connection.TRANSACTION_READ_UNCOMMITTED -> "TRANSACTION_READ_UNCOMMITTED";
                        case java.sql.Connection.TRANSACTION_READ_COMMITTED -> "TRANSACTION_READ_COMMITTED";
                        case java.sql.Connection.TRANSACTION_REPEATABLE_READ -> "TRANSACTION_REPEATABLE_READ";
                        case java.sql.Connection.TRANSACTION_SERIALIZABLE -> "TRANSACTION_SERIALIZABLE";
                        default -> "UNKNOWN (" + isolation + ")";
                    };
                    System.out.println("Transaction Isolation: " + isolationLevel + " (" + isolation + ")");
                    try (var conn = hikari.getConnection()) {
                        System.out.println("DB Version: " + conn.getMetaData().getDatabaseProductVersion());
                        System.out.println("Autocommit (conn): " + conn.getAutoCommit());
                        int connIsolation = conn.getTransactionIsolation();
                        String connIsolationLevel = switch (connIsolation) {
                            case java.sql.Connection.TRANSACTION_NONE -> "TRANSACTION_NONE";
                            case java.sql.Connection.TRANSACTION_READ_UNCOMMITTED -> "TRANSACTION_READ_UNCOMMITTED";
                            case java.sql.Connection.TRANSACTION_READ_COMMITTED -> "TRANSACTION_READ_COMMITTED";
                            case java.sql.Connection.TRANSACTION_REPEATABLE_READ -> "TRANSACTION_REPEATABLE_READ";
                            case java.sql.Connection.TRANSACTION_SERIALIZABLE -> "TRANSACTION_SERIALIZABLE";
                            default -> "UNKNOWN (" + connIsolation + ")";
                        };
                        System.out.println("Isolation Level (conn): " + connIsolationLevel + " (" + connIsolation + ")");
                    }
                } else {
                    System.out.println("Datasource is not HikariCP: " + dataSource.getClass());
                }
                System.out.println("=========================");
            }
        };
    }
}
