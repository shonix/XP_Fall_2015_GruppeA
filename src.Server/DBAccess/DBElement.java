package DBAccess;

import java.sql.*;

/**
 * Created by Peter Jensen on 27-10-2015.
 */
public abstract class DBElement
{
    //Global attributes initiated
    //SQL Attributes
    protected Connection connect = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;
    //Private methods
    //Method for defining connnection to database
    protected void createConnection() throws SQLException, ClassNotFoundException
    {
        //JBDC driver is defined
        Class.forName("com.mysql.jdbc.Driver");
        //driver is used to getting and setting connection using location(local), user and password
        connect = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "sqluser", "sqluserpw");
    }
    //Method for closing all used DB attributes
    protected void closeConnection()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (connect != null)
            {
                connect.close();
            }
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
        }
        catch (Exception e)
        {

        }
    }
}
