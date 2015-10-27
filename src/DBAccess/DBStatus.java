package DBAccess;

import Server.User;

import java.sql.SQLException;

/**
 * Created by Peter on 27-10-2015.
 */
public class DBStatus extends DBElement
{
    protected void createStatus(int ID)
    {
        try
        {
            createConnection();
            String query = "INSERT INTO mydb.leaves (userID) VALUES (?)";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1,ID);
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

    protected void ban(int userID)
    {

    }

    protected void inaktiv(int userID)
    {

    }
}
