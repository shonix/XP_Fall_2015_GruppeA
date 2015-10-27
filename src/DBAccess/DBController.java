package DBAccess;

import Server.User;

/**
 * Created by Peter on 27-10-2015.
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

    public void addUser(User u, String password)
    {
        user.createUser(u, password);
        leaves.createLeave(u.getID());
    }

    public void deleteUser(User u)
    {
     status.inaktiv(u.getID());
    }

    //Change the score for a person. Gametpye, Win, lose or draw, (win = w, lose = l, draw = d) user infomation (for user ID).
    public void gameStatChange(String type, char winLoseDraw, User u)
    {
        gameStat.changeGame(type, winLoseDraw, u);
    }

    //If the person leaves the game, (Not forfit!)
    public void leave(User u)
    {
        status.ban(u.getID());
    }

//    public void
}
