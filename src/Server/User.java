package Server;

import java.net.InetAddress;

/**
 * @author Markus
 */
public class User
{
    private int ID;

    private String name;
    private InetAddress IP;
    private int totalGames;

    private int leaveCount;
    private float leavePercentage;
    //Constructor for new user
    public User(String name, InetAddress IP)
    {
        this.name = name;
        this.IP = IP;
    }

    public User(int ID, String name, int totalGames, int leaveCount, float leavePercentage)
    {
        this.ID = ID;
        this.name = name;
        this.totalGames = totalGames;
        this.leaveCount = leaveCount;
        this.leavePercentage = leavePercentage;
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

    public InetAddress getIP()
    {
        return IP;
    }

    public void setIP(InetAddress IP)
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
