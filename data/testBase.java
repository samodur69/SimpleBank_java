package banking.data;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;

class TestDB {
    public TestDB() {
        String url = "jdbc:sqlite:testDB.db";
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
