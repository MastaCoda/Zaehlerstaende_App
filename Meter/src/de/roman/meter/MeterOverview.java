package de.roman.meter;

public class MeterOverview
{
	int icon;
	String from;
	String sub;
	String date;

	public String getName()
	{
		return from;
	}

	public void setName(String from)
	{
		this.from = from;
	}

	public String getSub()
	{
		return sub;
	}

	public void setSub(String sub)
	{
		this.sub = sub;
	}

	public int getIcon()
	{
		return icon;
	}

	public void setIcon(int icon)
	{
		this.icon = icon;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

}
