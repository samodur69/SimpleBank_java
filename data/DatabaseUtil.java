package banking.data;

import banking.FileUtil;
import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseUtil {
    private String url = "jdbc:sqlite:" + FileUtil.path;
    SQLiteDataSource dataSource = new SQLiteDataSource();

    public DatabaseUtil(String filename) {
        this.url += filename;
        dataSource.setUrl(this.url);
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                System.out.println("Connection is valid");
            }
        } catch (SQLException e) {
            System.out.println("Troubles with connection");
            e.printStackTrace();
        }
    }


}
