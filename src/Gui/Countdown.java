package Gui;
import javafx.scene.control.Label;


/**
 *
 * @author Markus
 */
public class Countdown extends Thread
{
    
    Label label;
    int totalTime;
            
    public Countdown(Label label, int totalTime)
    {
        this.label = label;
        this.totalTime = totalTime;
    }
    
    public void run()
    {
        while(totalTime>=0)
        {
            label.setText("" + totalTime);
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