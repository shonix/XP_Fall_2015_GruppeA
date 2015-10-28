package DBAccess;

import Server.User;

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

    //Change the active status of the user, true -> false or false -> true
    public void deleteUser(int ID)
    {
        status.active(ID);
    }

    //Change the score for a person. Gametpye, Win, lose or draw, (win = w, lose = l, draw = d) user infomation (for user ID).
    //Not tested
    public void gameStatChange(String type, char winLoseDraw, User u)
    {
        gameStat.changeGame(type, winLoseDraw, u);
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
