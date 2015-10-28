package DBAccess;

import Server.User;

import java.sql.SQLException;

/**
 * Created by Peter Jensen on 27-10-2015.
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
        try
        {
            createConnection();
            String query = "UPDATE status SET ban = ? WHERE userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, userID);
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
