package DBAccess;

import Server.User;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Peter Jensen on 27-10-2015.
 */
public class DBUser extends DBElement
{

    protected User logUserIn(String userName, String userPassword)
    {
        User u = null;
        try
        {
            createConnection();
            String query = "SELECT ID, name, active, ban, totalGames, count " +
                    "FROM mydb.user \n" +
                    "left JOIN mydb.status on user.ID = status.userID\n" +
                    "left join mydb.leaves on user.ID = leaves.userID\n" +
                    "where user.name like ? and user.password like ?";
            System.out.println(userName);
            System.out.println(userPassword);
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            resultSet = preparedStatement.executeQuery();
            try
            {
                if (resultSet.next() && resultSet.getBoolean("active") == true && resultSet.getBoolean("ban") != true)
                {
//                    public User(int ID, String name, int totalGames, int leaveCount, float leavePercentage)
                    float percentage;
                    int totalGames = resultSet.getInt("totalGames");
                    int leaveCount = resultSet.getInt("count");
                    if (leaveCount > 0)
                    {
                        percentage = ((float)leaveCount/(float)totalGames)*100;
                    }
                    else
                    {
                        percentage = 0;
                    }
                    u = new User(resultSet.getInt(1), userName, resultSet.getInt("totalGames"), resultSet.getInt("count"), percentage);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
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
        return u;
    }

    protected ResultSet readAllUsers()
    {
        ResultSet rs;
        CachedRowSetImpl crs = null;

        try
        {
            createConnection();
            rs = preparedStatement.executeQuery("SELECT name FROM mydb.gameStat");// min sql Select.
            crs = new CachedRowSetImpl();
            crs.populate(rs);
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

    //Return the ID of the newly created user.
    protected void createUser(User u, String password)
    {
        try
        {
            createConnection();
            String query = "INSERT INTO mydb.user (name, password, IP) VALUES (?, ?, ?)";
            preparedStatement = connect.prepareStatement(query, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, u.getIP().getHostAddress());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
            {
                u.setID(resultSet.getInt(1));// sets the integer to

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
    }
}
