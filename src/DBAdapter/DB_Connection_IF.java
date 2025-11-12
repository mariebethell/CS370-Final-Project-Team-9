package src.DBAdapter;

import java.sql.Connection;

public interface DB_Connection_IF {
    public Connection getConnection();
    public Connection closeConnection();
}
