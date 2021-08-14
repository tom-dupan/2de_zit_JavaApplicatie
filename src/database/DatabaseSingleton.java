package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
        private static final DatabaseSingleton singleton = new DatabaseSingleton();
        private static DatabaseSingleton instance;
        private Connection connection;

        public static DatabaseSingleton getInstance() throws SQLException {
            if (instance==null)
                instance= new DatabaseSingleton();
            return instance;
        }

        public Connection getConnection() throws SQLException {
            String url="jbdc:mysql://dt-srv-mysqlex.ehb.local/DBPRESS073";
            String user="DBPRESS073";
            String password="15864293";
            if (connection==null||connection.isClosed())
                connection = DriverManager.getConnection(url+user+password);
            return connection;

        }
    }

