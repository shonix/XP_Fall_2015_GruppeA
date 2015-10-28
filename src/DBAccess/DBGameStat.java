package DBAccess;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Peter Jensen on 27-10-2015.
 */
public class DBGameStat extends DBElement
{
    protected byte changeGame(String gameType, char winLoseDraw, int ID)
    {
        String stat;
        byte err = -1;
        try
        {
            stat = winLoseDraw(winLoseDraw);
            if (stat == null)
            {
            return err;
            }
            createConnection();
            String query = "UPDATE gameStat SET " + stat + " = " + stat + " + 1, total = total + 1 WHERE userID = ? and gameType = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, gameType);
            int rowsUpdated = preparedStatement.executeUpdate();

            err = 0;
            if (rowsUpdated == 0)
            {
                closeConnection();
                addThisGame(gameType, stat, ID);
                err = 1;
            }
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
        return err;
    }

    private String winLoseDraw(char winLoseDraw)
    {
        switch (winLoseDraw)
        {
            case 'w':
                return "win";
            case 'l':
                return "loss";
            case 'd':
                return "draw";
            default:
                return null;
        }
    }

    protected void addThisGame(String gameType, String winLoseDraw, int ID)
    {
            try
            {
                createConnection();
                String query = "INSERT INTO mydb.gameStat (userID, gameType, " + winLoseDraw + ", total) VALUES (?, ?, 1, 1)";
                preparedStatement = connect.prepareStatement(query, preparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, ID);
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

            String query = "SELECT * FROM mydb.gameStat WHERE userID = ? and gameType = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, gameName);
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


    //Return a resultset with data in the following order : ID, gameType, win, loss, draw, total. (only gameType is a string.)
    protected ResultSet readGamesStat(int ID)
    {
        CachedRowSetImpl crs = null;
        try
        {
            createConnection();

            String query = "SELECT * FROM mydb.gameStat WHERE userID = ?";
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
}
