package br.com.decade.oraerror.database;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;

public class ConnectionFactory {
    public ConnectionFactory() {
        super();
    }

    protected static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            try {
                String cdRoot = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
                String dbPath = cdRoot + "/ORAErrorDB.db";
                String conStr = "jdbc:sqlite:" + dbPath;

                conn = new SQLite.JDBCDataSource(conStr).getConnection();
            } catch (SQLException e) {
                Utility.ApplicationLogger.severe(e.getMessage());
            }
        }

        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
