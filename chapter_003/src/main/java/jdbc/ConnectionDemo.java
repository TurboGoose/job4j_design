package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[]args) throws Exception {
        String url, login, password;
        Class.forName("org.postgresql.Driver");
        try (InputStream is =
                     Settings.class.getClassLoader().getResourceAsStream("app.properties")) {
            Settings s = new Settings(is);
            url = s.getProperty("url");
            login = s.getProperty("login");
            password = s.getProperty("password");
        }

        try (Connection connect = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connect.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getUserName());
        }
    }
}

class Settings {
    private final Properties properties = new Properties();

    public Settings(InputStream is) throws IOException {
        properties.load(is);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
