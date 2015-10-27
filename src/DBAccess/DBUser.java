package DBAccess;

import Server.User;

import java.sql.SQLException;

/**
 * Created by Peter on 27-10-2015.
 */
public class DBUser extends DBElement
{

    protected User logUserIn(String userName, String userPassword)
    {

        return null;
    }

    //Return the ID of the newly created user.
    protected void createUser(User u, String password)
    {
        try
        {
            createConnection();
            String query = "INSERT INTO mydb.user (name, password, IP) VALUES (?, ?, ?)";
            preparedStatement = connect.prepareStatement(query , preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, u.getIP().toString());
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
