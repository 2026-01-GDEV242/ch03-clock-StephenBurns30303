
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes and meridian. The internal
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight). The clock display is responsible for converting this to the US style.
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Stephen M. Burns
 * @version 2026.02.25
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay americanHours;
    
    private String displayString;    // simulates the actual display
    private String meridians;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        meridians = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, String meridian)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        meridians=meridian;
        setTime(hour, minute, meridian);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        
        if(minutes.getValue() == 0) 
        {  // it just rolled over!
            
            hours.increment();
            
            if(hours.getValue()%12==0)
            {
                if(meridians=="AM")
                {
                    meridians="PM";
                }
                else
                {
                    meridians="AM";
                }

            }
        }
        
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, String meridian)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        meridians=meridian;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        
        americanHours= new NumberDisplay(24);
        americanHours.setValue(hours.getValue()%12);
        
        if(americanHours.getValue()%12==0)
        {
            americanHours.setValue(12);
        }
        
        
        displayString = americanHours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " " + meridians;
    }
}
