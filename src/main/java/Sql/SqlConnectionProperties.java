package Sql;

public class SqlConnectionProperties {
    private String userName;
    private String password;
    private String connectionUrl;

    public SqlConnectionProperties(String userName, String password, String connectionUrl) {
        this.userName = userName;
        this.password = password;
        this.connectionUrl = connectionUrl;
    }


    String getPassword() {
        return password;
    }

    String getUserName() {
        return userName;
    }

    String getConnectionUrl() {
        return connectionUrl;
    }
}
