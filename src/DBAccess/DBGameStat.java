package DBAccess;

import Server.User;

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
        String type = "";
        try
        {
            switch (winLoseDraw)
            {
                case 'w':
                    type = "win";
                    break;
                case 'l':
                    type = "loss";
                    break;
                case 'd':
                    type = "draw";
                    break;
                default:
                    return;
            }
            createConnection();
            String query = "UPDATE leaves SET " + type + " = " + type + " + 1 WHERE ID = " + u.getID() ;
            preparedStatement = connect.prepareStatement(query);


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
        ResultSet rs = null;
        try
        {
            createConnection();
            rs = preparedStatement.executeQuery("SELECT * FROM mydb.gameStat where userID = " + ID + " and gameType = " + gameName);// min sql Select.
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
        return rs;
    }
}
