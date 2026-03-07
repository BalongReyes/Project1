
package DatabaseSystem.SettingsData;

import DatabaseSystem.DataTable.DataTable;
import DatabaseSystem.DataTable.DataTableType;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Holds the values of the table items
 * @author SEDIAN
 */
public class SettingsDataTable implements DataTable{
    
    public static final int ID = 1;
    public static final int ACCOUNT_ID = 2;
    public static final int AUTO_LOGOUT_TIME = 3;
    public static final int PREFERRED_DATE = 4;
    
// -----------------------------------------------------------------------------------------------------------
    
    private Integer id;
    private Integer accountId;
    private Integer autoLogoutTime;
    private Integer preferredDate;
    
// Constructor ===============================================================================================

    public SettingsDataTable(Integer id, Integer accountId, Integer autoLogoutTime, Integer preferredDate){
        this.id = id;
        this.accountId = accountId;
        this.autoLogoutTime = autoLogoutTime;
        this.preferredDate = preferredDate;
    }

    public SettingsDataTable(ResultSet results) throws SQLException{
        try{
            id = results.getInt("id");
            accountId = results.getInt("accountId");
            autoLogoutTime = results.getInt("autoLogoutTime");
            preferredDate = results.getInt("preferredDate");
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

    public boolean idEquals(Integer value){
        if(id != null && value != null){
            return id.equals(value);
        }
        return false;
    }
    
// Getter ====================================================================================================
    
    @Override
    public Object getData(int i){
        return switch(i){
            case 1 -> id;
            case 2 -> accountId;
            case 3 -> autoLogoutTime;
            case 4 -> preferredDate;
            default -> null;
        };
    }
    
    @Override
    public DataTableType getDataType(int i){
        return switch(i){
            case 1, 2, 3, 4 -> DataTableType.TYPE_INTEGER;
            default -> DataTableType.TYPE_NULL;
        };
    }
    
    public Integer getId(){
        return id;
    }

    public Integer getAccountId(){
        return accountId;
    }

    public Integer getAutoLogoutTime(){
        return autoLogoutTime;
    }

    public Integer getPreferredDate(){
        return preferredDate;
    }

    public void setPreferredDate(Integer preferredDate){
        this.preferredDate = preferredDate;
    }
    
    public void setAutoLogoutTime(Integer autoLogoutTime){
        this.autoLogoutTime = autoLogoutTime;
    }
    
// Overrided Methods =========================================================================================

    @Override
    public String toString(){
        return String.valueOf(id);
    }
    
}
