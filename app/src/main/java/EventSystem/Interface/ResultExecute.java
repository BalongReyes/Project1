
package EventSystem.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultExecute{

    public void execute(ResultSet result) throws SQLException;
    
}
