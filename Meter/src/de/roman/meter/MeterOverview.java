package de.roman.meter;

import java.util.Date;

/**
 * Represents an instance of a meter with its properties.
 * 
 * @author Roman Schneider
 * 
 */
public class MeterOverview
{
    int icon;
    String name;
    String count;
    Date date;
    Long userID;
    Long meterID;
    String unit;
    String type;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Long getUserID()
    {
        return userID;
    }

    public void setUserID(Long userID)
    {
        this.userID = userID;
    }

    public Long getMeterID()
    {
        return meterID;
    }

    public void setMeterID(Long meterID)
    {
        this.meterID = meterID;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
