package DBAccess;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.ResultSet;
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

    //Return ID, count, percentage, totalgames.
    protected ResultSet getLeave(int ID)
    {
        CachedRowSetImpl crs = null;
        try
        {
            createConnection();

            String query = "SELECT * FROM mydb.leaves WHERE userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();// min sql Select.

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
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
        return crs;
    }

    //Update the users leave count/amount in the database!!.
    protected void updateLeaves(int ID)
    {

        try
        {
            createConnection();
//            preparedStatement.execute("UPDATE leave SET count = count + 1, percentage = count/total WHERE userID = +");// min sql Select.

            String query = "UPDATE leaves SET count = count + 1, percentage = count/totalGames*100 WHERE userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.execute();

            addTotalGame(ID);
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

    protected void addTotalGame(int ID)
    {
        try
        {
            createConnection();
            String query = "UPDATE leaves SET totalGames = totalGames + 1 WHERE userID = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
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
        closeConnection();
    }
}
