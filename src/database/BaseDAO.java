package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
        private static Connection connection;
        public static Connection getConnection() throws SQLException {
            if (connection==null|| connection.isClosed()) connection=DatabaseSingleton.getInstance().getConnection();
            return connection;
        }



}
