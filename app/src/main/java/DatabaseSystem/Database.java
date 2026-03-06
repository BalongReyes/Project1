
package DatabaseSystem;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import EventSystem.Interface.ResultExecute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database{
    
    protected static String databaseName = "sdproject01";
    protected static String username = "root";
    protected static String password = "";
    protected static String ip = "localhost";
    protected static String port = "3306";
    
    private static Connection connection;
    
    public static void openConnection(){
        Console.line().out("CONNECTING TO DATABASE '" + databaseName + "'", ConsoleColors.GREEN);
        try{
            Console.out("Connecting: " + "jdbc:mysql://" + ip + ":" + port + "/" + databaseName);
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + databaseName, username, password);
            Console.out("Connection success");
        }catch(SQLException e){
            Console.errorOut("Connection failed", e);
        }
    }
    
    public static void closeConnection(){
        if(connection == null) return;
        Console.line().out("CLOSING CONNECTION", ConsoleColors.GREEN);
        try{
            connection.close();
            Console.out("Connection closed");
        }catch(SQLException e){
            Console.errorOut("Connection closing error", e);
        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
    
    public static boolean isOpen(){
        if(connection == null) return false;
        try{
            if(connection.isClosed()) return false;
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    public static void executeBatch(String... queries) throws SQLException{
        if(connection == null) throw new SQLException("Database offline");
        try(
            Statement statement = connection.createStatement();
        ){
            for(String query : queries){
                statement.addBatch(query);
            }
            statement.executeBatch();
        }
    }
    
    public static void execute(String query) throws SQLException{
        if(connection == null) throw new SQLException("Database offline");
        try(
            Statement statement = connection.createStatement();
        ){
            statement.execute(query);
        }
    }
    
    public static void execute(String query, ResultExecute resultExecute) throws SQLException{
        if(connection == null) throw new SQLException("Database offline");
        try( 
            Statement statement = connection.createStatement(); 
            ResultSet result = statement.executeQuery(query);
        ){
            resultExecute.execute(result);
        }
    }
    
}
