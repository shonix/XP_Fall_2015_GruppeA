package DBAccess;

import Server.User;
import java.sql.SQLException;

/**
 * Created by Peter Jensen on 27-10-2015.
 */
public class DBLeaves extends DBElement
{
    protected void createLeave(int userID)
    {
        try
        {
            createConnection();
            String query = "INSERT INTO mydb.leaves (userID) VALUES (?)";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1,userID);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        closeConnection();
    }
}
