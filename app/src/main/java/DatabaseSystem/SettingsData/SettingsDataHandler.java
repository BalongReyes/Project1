package DatabaseSystem.SettingsData;

import java.sql.SQLException;
import java.util.ArrayList;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import DatabaseSystem.Database;
import MainSystem.Main;
import MethodsSystem.MethodAlgorithms;

public class SettingsDataHandler {

    private static ArrayList<SettingsDataTable> array = new ArrayList<>();

    public static void refreshData() throws SQLException {
        array.clear();

        Database.execute("SELECT * FROM settings", (result) -> {
            while (result.next()) {
                SettingsDataTable data = new SettingsDataTable(result);
                if (!data.isError()) {
                    array.add(data);
                }
            }
        });

        if (Main.debugDataHandlerRefresh) {
            Console.out("SettingsDataHandler refreshed", ConsoleColors.YELLOW);
        }
    }

    public static SettingsDataTable[] getAllData(boolean refresh) throws SQLException {
        if (refresh || array.isEmpty()) {
            refreshData();
        }
        return array.toArray(SettingsDataTable[]::new);
    }

    public static SettingsDataTable findDataById(int id, boolean refresh) throws SQLException {
        SettingsDataTable[] currentArray = getAllData(refresh);

        int index = MethodAlgorithms.initBinarySearch(currentArray, SettingsDataTable.ID, id);
        return index != -1 ? currentArray[index] : null;
    }

    public static SettingsDataTable findDataByAccountId(int accoutId, boolean refresh) throws SQLException {
        SettingsDataTable[] currentArray = getAllData(refresh);

        int index = MethodAlgorithms.initBinarySearch(currentArray, SettingsDataTable.ACCOUNT_ID, accoutId);
        return index != -1 ? currentArray[index] : null;
    }

// ===========================================================================================================
    public static void deleteData(int id) {
        try {
            Database.execute(
                    "DELETE FROM settings WHERE id = '" + id + "'"
            );
        } catch (SQLException e) {
            Console.errorOut("Deleting data from table settings error", e);
        }
    }

    public static void deleteDataByAccountId(int accountId) {
        try {
            Database.execute(
                    "DELETE FROM settings WHERE accountId = '" + accountId + "'"
            );
        } catch (SQLException e) {
            Console.errorOut("Deleting data from table settings error", e);
        }
    }

    public static void insertData(SettingsDataTable data) {
        try {
            Database.execute(
                    "INSERT INTO settings (id, accountId, autoLogoutTime, preferredDate) VALUES ("
                    + "NULL"
                    + ", '" + data.getAccountId() + "'"
                    + ", '" + data.getAutoLogoutTime() + "'"
                    + ", '" + data.getPreferredDate() + "'"
                    + ")"
            );
        } catch (SQLException e) {
            Console.errorOut("Inserting data from table settings error", e);
        }
    }

    public static void insertDefaultData(int accountId) {
        try {
            Database.execute(
                    "INSERT INTO settings (id, accountId, autoLogoutTime, preferredDate) VALUES ("
                    + "NULL"
                    + ", '" + accountId + "'"
                    + ", '120'"
                    + ", '1'"
                    + ")"
            );
        } catch (SQLException e) {
            Console.errorOut("Inserting data from table settings error", e);
        }
    }

    public static void updateData(SettingsDataTable data, int id) {
        try {
            Database.execute(
                    "UPDATE settings SET "
                    + "accountId = '" + data.getAccountId() + "'"
                    + ", autoLogoutTime = '" + data.getAutoLogoutTime() + "'"
                    + ", preferredDate = '" + data.getPreferredDate() + "'"
                    + " WHERE id = " + id
            );
        } catch (SQLException e) {
            Console.errorOut("Updating data from table settings error", e);
        }
    }

    public static boolean updatePreferredDateData(int preferredDate, int id) {
        try {
            Database.execute(
                    "UPDATE settings SET "
                    + "preferredDate = '" + preferredDate + "'"
                    + " WHERE id = " + id
            );
        } catch (SQLException e) {
            Console.errorOut("Updating preferredDate data from table settings error", e);
            return false;
        }
        return true;
    }

    public static boolean updateAutoLogoutTimeData(int autoLogoutTime, int id) {
        try {
            Database.execute(
                    "UPDATE settings SET "
                    + "autoLogoutTime = '" + autoLogoutTime + "'"
                    + " WHERE id = " + id
            );
        } catch (SQLException e) {
            Console.errorOut("Updating autoLogoutTime data from table settings error", e);
            return false;
        }
        return true;
    }

}
