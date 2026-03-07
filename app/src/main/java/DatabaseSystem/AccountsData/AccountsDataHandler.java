
package DatabaseSystem.AccountsData;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import DatabaseSystem.Database;
import FrameSystem.LoginGroup.Managers.ManagerLogin;
import MainSystem.Main;
import MethodsSystem.MethodAlgorithms;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccountsDataHandler{
    
    private static ArrayList<AccountsDataTable> array = new ArrayList<>();
    
    public static void refreshData() throws SQLException{
        array.clear();
        
        Database.execute("SELECT * FROM accounts", (result) -> {
            while(result.next()){
                AccountsDataTable data = new AccountsDataTable(result);
                if(ManagerLogin.isLoggedIn() && ManagerLogin.getAccountLoggedIn().idEquals(data.getId())){
                    ManagerLogin.updateAccountLoggedIn(data);
                }
                if(!data.isError()) array.add(data);
            }
        });
        
        if(array.isEmpty()){
            Console.out("No accounts found, inserting default account");
            insertData(AccountsDataTable.createInstanceUnhashed(null, "SuperAdmin", 1, "SuperAdmin", 1, "SuperAdmin", java.sql.Date.valueOf(LocalDate.now())));
            refreshData();
            return;
        }
        
        if(Main.debugDataHandlerRefresh) Console.out("AccountsDataHandler refreshed", ConsoleColors.YELLOW);
    }
    
    public static AccountsDataTable[] getAllData(boolean refresh) throws SQLException{
        if(refresh || array.isEmpty()) refreshData();
        return array.toArray(AccountsDataTable[]::new);
    }
    
// Find ======================================================================================================
    
    public static AccountsDataTable findDataById(int id, boolean refresh) throws SQLException{
        return findData(AccountsDataTable.ID, id, refresh);
    }
    
    public static AccountsDataTable findDataByUserId(int userId, boolean refresh) throws SQLException{
        return findData(AccountsDataTable.USER_ID, userId, refresh);
    }
    
    public static AccountsDataTable findDataByUsername(String username, boolean refresh) throws SQLException{
        return findData(AccountsDataTable.USERNAME, username, refresh);
    }
    
    private static AccountsDataTable findData(int dataIndex, Object target, boolean refresh) throws SQLException{
        AccountsDataTable[] currentArray = getAllData(refresh);
        int index = MethodAlgorithms.initBinarySearch(currentArray, dataIndex, target);
        return index != -1 ? currentArray[index] : null;
    }
    
// Check Duplicate ===========================================================================================
    
    public static boolean checkDuplicateUserId(int oldUserId, int newUserId){
        if(oldUserId == newUserId) return false;
        try{
            return findDataByUserId(newUserId, false) != null;
        }catch(SQLException e){
            Console.errorOut("Check duplicate userId error", e);
        }
        return true;
    }
    
    public static boolean checkDuplicateUsername(String oldUsername, String newUsername){
        if(oldUsername.equals(newUsername)) return false;
        try{
            return findDataByUsername(newUsername, false) != null;
        }catch(SQLException e){
            Console.errorOut("Check duplicate username error", e);
        }
        return true;
    }
    
// ===========================================================================================================
    
    private static MessageDigest md;
    
    public static AccountsDataTable loginAccount(String usernameOrId, char[] charPassword) throws SQLException{
        String password = String.valueOf(charPassword);
        
        refreshData();
        AccountsDataTable account = null;
        try{
            account = findDataByUserId(Integer.parseInt(usernameOrId), false);
        }catch(NumberFormatException e){
        }
        if(account == null){
            account = findDataByUsername(usernameOrId, false);
        }
        
        if(account == null) return null;
        if(account.checkPassword(hashPassword(password))) return account;
        
        return null;
    }
    
    public static boolean verifyLogin(AccountsDataTable data, String password){
        return data.checkPassword(hashPassword(password));
    }
    
    protected static String hashPassword(String password){
        return new BigInteger(1, md.digest(password.getBytes())).toString();
    }
    
// ===========================================================================================================
    
    public static void deleteData(int id){
        try{
            Database.execute(
                "DELETE FROM accounts WHERE id = '" + id + "'"
            );
        }catch(SQLException e){
            Console.errorOut("Deleting data from table accounts error", e);
        }
    }
    
    public static void insertData(AccountsDataTable data){
        try{
            Database.executeBatch(
                "INSERT INTO accounts (id, name, userID, username, password, role, lastChange) VALUES ("
                + "NULL"
                + ", '" + data.getName() + "'"
                + ", '" + data.getUserId()+ "'"
                + ", '" + data.getUsername()+ "'"
                + ", '" + data.getPassword()+ "'"
                + ", '" + data.getRole()+ "'"
                + ", '" + data.getLastChange()+ "'"
                + ");",
                "SET @lastid := LAST_INSERT_ID();",
                "INSERT INTO settings (id, accountId, autoLogoutTime, preferredDate) VALUES ("
                + "NULL"
                + ", @lastid"
                + ", '120'"
                + ", '1'"
                + ");"
            );
        }catch(SQLException e){
            Console.errorOut("Inserting data from table accounts error", e);
        }
    }
    
    public static void updateData(AccountsDataTable data, int id){
        try{
            Database.execute(
                "UPDATE accounts SET "
                + "name = '" + data.getName() + "'"
                + ", userId = '" + data.getUserId()+ "'"
                + ", username = '" + data.getUsername()+ "'"
                + ", password = '" + data.getPassword()+ "'"
                + ", role = '" + data.getRole()+ "'"
                + ", lastChange = '" + data.getLastChange()+ "'"
                + " WHERE id = " + id
            );
        }catch(SQLException e){
            Console.errorOut("Updating data from table accounts error", e);
        }
    }
    
    public static void updatePassword(AccountsDataTable data, String password, int id){
        try{
            Database.execute(
                "UPDATE accounts SET "
                + "password = '" + hashPassword(password) + "'"
                + ", lastChange = '" + java.sql.Date.valueOf(LocalDate.now()) + "'"
                + " WHERE id = " + id
            );
        }catch(SQLException e){
            Console.errorOut("Updating password data from table accounts error", e);
        }
    }
    
// Static Initialization Block ===============================================================================

    static{
        try{
            md = MessageDigest.getInstance("SHA-256");
        }catch(NoSuchAlgorithmException e){
            Console.errorOut("Message Digest Error", e);
        }
    }
    
}