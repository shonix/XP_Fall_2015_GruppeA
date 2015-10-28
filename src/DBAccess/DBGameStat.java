package DBAccess;

import Server.User;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Peter Jensen on 27-10-2015.
 */
public class DBGameStat extends DBElement
{
    protected void changeGame(String gameType, char winLoseDraw, User u)
    {
        String stat;
        try
        {
            switch (winLoseDraw)
            {
                case 'w':
                    stat = "win";
                    break;
                case 'l':
                    stat = "loss";
                    break;
                case 'd':
                    stat = "draw";
                    break;
                default:
                    return;
            }
            createConnection();
            String query = "UPDATE gameStat SET " + stat + " = " + stat + " + 1, total = total + 1 WHERE userID = ? and gameType = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, u.getID());
            preparedStatement.setString(2, gameType);
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

    protected ResultSet readGameStat(String gameName, int ID)
    {
        CachedRowSetImpl crs = null;
        try
        {
            createConnection();
            resultSet = preparedStatement.executeQuery("SELECT name FROM mydb.gameStat");// min sql Select.
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
