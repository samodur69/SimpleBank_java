package banking.data;

import banking.Accounts.Account;
import banking.FileUtil;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class TestBase {
    private String url = "jdbc:sqlite:" + FileUtil.path;

    /**
     * after initialization testing connection
     * @param filename - adding DB filename to path from com line args to create full path to DB
     */
    public TestBase(String filename) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        this.url += filename;
        dataSource.setUrl(this.url);
        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                createTable();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * connection to DB util. use for simplify methods code
     * @return connection
     */
    public Connection connect() {
        Connection conn = null;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(this.url);
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * creating every time new table if not exists in DB
     */
    public void createTable() {
        try (Connection conn = this.connect()) {
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

    public Account importAccountInfo(String cardNumber, String inputPin) {
        Account accInfo = new Account();
        try (Connection conn = this.connect()){
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM card WHERE number = ?");
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.getString("number").equals(cardNumber)) {
                String checkPin = resultSet.getString("pin");
                if (checkPin.equals(inputPin)) {
                    accInfo.setUserId(resultSet.getInt("id"));
                    accInfo.setCardNumber(resultSet.getString("number"));
                    accInfo.setCardPin(checkPin);
                    accInfo.setCardBalance(resultSet.getInt("balance"));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Troubles when try to import account data from DB");
            return null;
        }
        return accInfo;
    }

    public void updateAccountBalance(Account account) {
        try (Connection conn = this.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE card SET balance = ? WHERE number = ?");
            statement.setInt(1, account.getCardBalance());
            statement.setString(2, account.getCardNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Troubles when try to export account data to DB");
        }
    }

    /**
     * func to add new row with already created account to DB
     * @param account - object created in AccountManager
     */
    public void sqlAddNewAccount(Account account) {
        try (Connection conn = this.connect()) {
            String sqlNewAccount = "INSERT INTO card VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sqlNewAccount);
            statement.setInt(1, account.getUserId());
            statement.setString(2, account.getCardNumber());
            statement.setString(3, account.getCardPin());
            statement.setInt(4, account.getCardBalance());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Troubles with adding new acc to DB");
        }
    }

    public void makeTransaction (String targetAccountNumber, int amount) {
        String sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
        try (Connection conn = this.connect()){
            PreparedStatement statement  = conn.prepareStatement(sql);
            statement.setInt(1, amount);
            statement.setString(2, targetAccountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cant update target account balance");
        }
    }

    public void deleteAccount (String accountNumber) {
        String sql = "DELETE FROM card WHERE number = ?";
        try (Connection conn = this.connect()) {
            PreparedStatement statement  = conn.prepareStatement(sql);
            statement.setString(1, accountNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cant delete account");
        }
    }

    public boolean checkTargetCardNumber(String accountNumber) {
        boolean cardExist = false;
        String sql = "SELECT * FROM card WHERE number = ?";
        try (Connection conn = this.connect()) {
            PreparedStatement statement  = conn.prepareStatement(sql);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cardExist = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL error when check account number in DB");
        }
        return cardExist;
    }
}
