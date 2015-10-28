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
    }

    //Not tested!
    public void deleteUser(User u)
    {
        status.inaktiv(u.getID());
    }

    //Change the score for a person. Gametpye, Win, lose or draw, (win = w, lose = l, draw = d) user infomation (for user ID).
    //Not tested
    public void gameStatChange(String type, char winLoseDraw, User u)
    {
        gameStat.changeGame(type, winLoseDraw, u);
    }

    //If the person leaves the game, (Not forfit!)
    //Not tested
    public void banUser(User u)
    {
        status.ban(u.getID());
    }

//    public void
}
