
package DatabaseSystem.AccountsData;

import DatabaseSystem.DataTable.DataTable;
import DatabaseSystem.DataTable.DataTableType;
import MethodsSystem.MethodString;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds the values of the table accounts
 * @author SEDIAN
 */
public class AccountsDataTable implements DataTable{

    public static final int ID = 1;
    public static final int NAME = 2;
    public static final int USER_ID = 3;
    public static final int USERNAME = 4;
    public static final int ROLE = 5;    
    
    private Integer id;
    private String name;
    private int userId;
    private String username;
    private int role;
    private String password;
    private java.sql.Date lastChange;
    
// Constructor ===============================================================================================

    public AccountsDataTable(Integer id, String name, int userId, String username, String password, int role, Date lastChange){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastChange = lastChange;
    }
    
    public AccountsDataTable(ResultSet results) throws SQLException{
        try{
            id = results.getInt("id");
            name = results.getString("name");
            userId = results.getInt("userID");
            username = results.getString("username");
            password = results.getString("password");
            role = results.getInt("role");
            lastChange = results.getDate("lastChange");
        }catch(SQLException e){
            error = true;
            throw e;
        }
    }
    
// Error =====================================================================================================
    
    private boolean error = false;
    
    public boolean isError(){
        return error;
    }
    
// Methods ===================================================================================================

    public boolean haveData(String data){
        return 
            MethodString.checkLike(name, data) ||
            MethodString.checkLike(username, data);
    }
    
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    
    public boolean idEquals(Integer value){
        if(id != null && value != null){
            return id.equals(value);
        }
        return false;
    }
    
    public AccountsDataTable createNewData(Integer id, String name, int userId, String username, int role, java.sql.Date lastChange){
        return new AccountsDataTable(id, name, userId, username, password, role, lastChange);
    }
    
    public AccountsDataTable createNewDataUnhashed(Integer id, String name, int userId, String username, int role, String password, java.sql.Date lastChange){
        if(password == null){
            return createNewData(id, name, userId, username, role, lastChange);
        }
        return new AccountsDataTable(id, name, userId, username, AccountsDataHandler.hashPassword(password), role, lastChange);
    }
    
// Getter ====================================================================================================
    
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getRole(){
        return role;
    }

    public int getUserId(){
        return userId;
    }

    public String getUsername(){
        return username;
    }

    protected String getPassword(){
        return password;
    }

    public Date getLastChange(){
        return lastChange;
    }
    
// Implementations ===========================================================================================

    @Override
    public Object getData(int i){
        return switch(i){
            case 1 -> id;
            case 2 -> name;
            case 3 -> userId;
            case 4 -> username;
            case 5 -> role;
            default -> null;
        };
    }
    
    @Override
    public DataTableType getDataType(int i){
        return switch(i){
            case 2, 4 -> DataTableType.TYPE_STRING;
            case 1, 3, 5 -> DataTableType.TYPE_INTEGER;
            default -> DataTableType.TYPE_NULL;
        };
    }
    
// Static Methods ============================================================================================

    public static AccountsDataTable createInstanceUnhashed(Integer id, String name, int userId, String username, int role, String password, java.sql.Date lastChange){
        return new AccountsDataTable(id, name, userId, username, AccountsDataHandler.hashPassword(password), role, lastChange);
    }
    
}
