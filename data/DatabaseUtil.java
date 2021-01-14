package banking.data;

import banking.Accounts.Account;
import banking.FileUtil;
import org.jetbrains.annotations.NotNull;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class DatabaseUtil {
    private String url = "jdbc:sqlite:" + FileUtil.path;
    final private String sqlUpdateAccount = "";

    /**
     * after initialization testing connection
     * @param filename - adding DB filename to path from com line args to create full path to DB
     */
    public DatabaseUtil(String filename) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        this.url += filename;
        dataSource.setUrl(this.url);
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                createTable();
                System.out.println("Connection is valid");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * creating every time new table if not exists in DB
     */
    public void createTable() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.url);
        try (Connection conn = dataSource.getConnection()) {
            Statement state = conn.createStatement();
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS card (\n"
                    + " id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
                    + " number VARCHAR(16) NOT NULL,\n"
                    + " pin VARCHAR(4) NOT NULL,\n"
                    + " balance INTEGER DEFAULT '0' );";
            state.executeUpdate(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Account importAccountInfo(String cardNumber, String pin) {
        Account accInfo = new Account();
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.url);
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM card WHERE number = ?");
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.equals(null)) {
                String checkPin = resultSet.getString("pin");
                if (pin.equals(checkPin)) {
                    accInfo.setUserId(resultSet.getInt("id"));
                    accInfo.setCardNumber(resultSet.getString("number"));
                    accInfo.setCardPin(checkPin);
                    accInfo.setCardBalance(resultSet.getInt("balance"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Troubles when try to import account data from DB");
        }
        return accInfo;
    }

    public void exportAccountInfo(Account account) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.url);
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM card WHERE number = ?");
            // continue update statement

        } catch (SQLException e) {
            System.out.println("Troubles when try to export account data to DB");
        }
    }

    /**
     * func to add new row with already created account to DB
     * @param account - object created in AccountManager
     */
    public void sqlAddNewAccount(@NotNull Account account) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.url);
        try {
            Connection conn = dataSource.getConnection();
            String sqlNewAccount = "INSERT INTO card VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sqlNewAccount);
            statement.setInt(1, account.getUserId());
            statement.setString(2, account.getCardNumber());
            statement.setString(3, account.getCardPin());
            statement.setInt(4, (int) account.getCardBalance());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Troubles with insert new acc to DB");
        }
    }
}
