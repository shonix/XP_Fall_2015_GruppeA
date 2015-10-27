package DBAccess;

import Server.User;

import java.sql.SQLException;

/**
 * Created by Peter on 27-10-2015.
 */
public class DBGameStat extends DBElement
{
    protected void changeGame(String type, char winLoseDraw, User u)
    {
        String statIncrease = "";
        try
        {
            switch (winLoseDraw)
            {
                case 'w':
                    statIncrease = "win";
                    break;
                case 'l':
                    statIncrease = "loss";
                    break;
                case 'd':
                    statIncrease = "draw";
                    break;
                default:
                    return;
            }
            createConnection();
            String query = "UPDATE mydb.leaves (" + statIncrease + ") VALUES (?) where ID = " + u.getID();
            preparedStatement = connect.prepareStatement(query);

//            preparedStatement.setInt();

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
