package lab4;

import java.sql.Connection;

public abstract class DataBaseManager implements DataManager {
    private Connection connection;

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }
    public Connection getConnection()
    {
        return connection;
    }
}
