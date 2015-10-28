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
        ResultSet rs = null;
        try
        {
            createConnection();
            String query = "SELECT ID, name, active, ban, totalGames, count FROM mydb.user JOIN mydb.status JOIN mydb.leaves WHERE name LIKE ? and password LIKE ?";
            System.out.println(userName);
            System.out.println(userPassword);
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            rs = preparedStatement.executeQuery();
            try
            {
                if (rs.next() && rs.getBoolean("active") == true && rs.getBoolean("ban") != true)
                {
//                    public User(int ID, String name, int totalGames, int leaveCount, float leavePercentage)
                    float percentage;
                    int totalGames = rs.getInt("totalGame");
                    int leaveCount = rs.getInt("count");
                    if (leaveCount > 0)
                    {
                        percentage = ((float)leaveCount/(float)totalGames)*100;
                        String tempPercentage = String.format("%.2f", percentage);
                        percentage = Float.parseFloat(tempPercentage);
                    }
                    else
                    {
                        percentage = 0;
                    }
                    User u = new User(rs.getInt(1), userName, rs.getInt("totalGame"), rs.getInt("count"), percentage);
                    return u;
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
        } closeConnection();

        return null;
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
