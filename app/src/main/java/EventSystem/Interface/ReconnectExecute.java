
package EventSystem.Interface;

import java.sql.SQLException;

@FunctionalInterface
public interface ReconnectExecute{

    public void reconnect() throws SQLException;
    
}
