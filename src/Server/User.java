package Server;

import java.net.Inet4Address;

/**
 * @author Markus
 */
public class User
{
    private int ID;
    private String name;
    private Inet4Address IP;

    private int totalGames;
    private int leaveCount;
    private float leavePercentage;

    //Constructor for new user   
    public User(String name, Inet4Address IP)
    {
        this.name = name;
        this.IP = IP;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Inet4Address getIP()
    {
        return IP;
    }

    public void setIP(Inet4Address IP)
    {
        this.IP = IP;
    }

    public int getTotalGames()
    {
        return totalGames;
    }

    public void setTotalGames(int totalGames)
    {
        this.totalGames = totalGames;
    }

    public int getLeaveCount()
    {
        return leaveCount;
    }

    public void setLeaveCount(int leaveCount)
    {
        this.leaveCount = leaveCount;
    }

    public float getLeavePercentage()
    {
        return leavePercentage;
    }

    public void setLeavePercentage(float leavePercentage)
    {
        this.leavePercentage = leavePercentage;
    }
}
