package DBAccess;

import Server.User;

import java.sql.ResultSet;

/**
 * Created by Peter Jensen on 27-10-2015.
 */
public class DBController
{
    private DBStatus status = new DBStatus();
    private DBGameStat gameStat = new DBGameStat();
    private DBLeaves leaves = new DBLeaves();
    private DBUser user = new DBUser();

    //Return a user object after it has logged in.
    public User login(String userName, String userPassword)
    {
        return user.logUserIn(userName, userPassword);
    }

    //Works add a new user to the DB.
    public void addUser(User u, String password)
    {
        user.createUser(u, password);
        leaves.createLeave(u.getID());
        status.createStatus(u.getID());
    }

    //Return all user names.
    public ResultSet getAllUsers()
    {
        return user.readAllUsers();
    }

    //Change the active status of the user, true -> false or false -> true
    public void deleteUser(int ID)
    {
        status.active(ID);
    }

    /*
    Change the score for a person. Gametype, Win, lose or draw, (win = w, lose = l, draw = d) user infomation.
    Add the game type to the users list if it doesn't exist.
     */
    public void gameStatChange(String type, char winLoseDraw, int ID)
    {
        gameStat.changeGame(type, winLoseDraw, ID);
        leaves.addTotalGame(ID);
    }

    //Add 1 to leave count and 1 to totalgames, and also update percentage leave.
    public void incLeave(int ID)
    {
        leaves.updateLeaves(ID);
    }

    //Return leave for one user. ID, count, percentage, totalgame.
    public ResultSet getLeave(int ID)
    {
        return leaves.getLeave(ID);
    }

    /*
    Return a resultset with data in the following order : ID, gameType, win, loss, draw, total. (only gameType is a string.)
    For one game for one user.
     */
    public ResultSet getGameStat(String gameName, int ID)
    {
        return gameStat.readGameStat(gameName, ID);
    }

    /*
    Return a resultset with data in the following order : ID, gameType, win, loss, draw, total. (only gameType is a string.)
    for all games for that specific user.
     */
    public ResultSet getGamesStat(int ID)
    {
        return gameStat.readGamesStat(ID);
    }

    //Change the ban status of the user, true -> false or false -> true
    public void banUser(int ID)
    {
        status.ban(ID);
    }

    //Return if the user is banned.
    public boolean isBan(int ID)
    {
        return status.isBan(ID);
    }

    //Return if the user is active.
    public boolean isActive(int ID)
    {
        return status.isActive(ID);
    }
}
