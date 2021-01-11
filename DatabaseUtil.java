package banking;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class DatabaseUtil {

    public DatabaseUtil(String filename) {
        String url = "jdbc:sqlite:" + FileUtil.path + filename;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                System.out.println("Connection is valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
