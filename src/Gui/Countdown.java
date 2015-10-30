package Gui;
import javafx.scene.control.Label;


/**
 *
 * @author Markus
 */
public class Countdown extends Thread
{
    
    int totalTime;
            
    public Countdown(int totalTime)
    {
        this.totalTime = totalTime;
    }
    
    public void run()
    {
        while(totalTime>=0)
        {
            System.out.println(totalTime);
            
            totalTime--;
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    
}