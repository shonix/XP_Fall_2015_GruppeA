package DBAccess;

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
            String query = "INSERT INTO mydb.status (userID) VALUES (?)";
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
        try
        {
            createConnection();
            String query = "UPDATE status SET ban = !ban WHERE userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, userID);
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

    protected void active(int userID)
    {
        try
        {
            createConnection();
            String query = "UPDATE status SET active = !active WHERE userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, userID);
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

    public boolean isBan(int ID)
    {
        Boolean ban = true;
        try
        {
            createConnection();
            String query = "SELECT ban FROM mydb.status where userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) ban = resultSet.getBoolean(1);
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
        return ban;
    }

    public boolean isActive(int ID)
    {
        Boolean active = false;
        try
        {
            createConnection();
            String query = "SELECT active FROM mydb.status where userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) active = resultSet.getBoolean(1);
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
        return active;
    }
}
