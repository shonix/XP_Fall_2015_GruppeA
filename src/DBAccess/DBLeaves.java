package DBAccess;

import Server.User;
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

    protected ResultSet getLeaves(int ID)
    {
        CachedRowSetImpl crs = null;

        try
        {
            createConnection();
            resultSet = preparedStatement.executeQuery("SELECT * FROM mydb.leaves");// min sql Select.
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
}
