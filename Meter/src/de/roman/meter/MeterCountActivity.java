package de.roman.meter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MeterCountActivity extends ListActivity
{
	Intent homeIntent;
	String meterCounts;
	String unit;

	List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metercount);

		// get Extras
		homeIntent = getIntent();

		unit = homeIntent.getStringExtra("unit");
		meterCounts = homeIntent.getStringExtra("meterCounts");
		setTitle(homeIntent.getStringExtra("type") + " - "
				+ homeIntent.getStringExtra("title"));

		seperateJsonArray(meterCounts);

		SimpleAdapter adapter = new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_2, new String[]
				{ "First Line", "Second Line" }, new int[]
				{ android.R.id.text1, android.R.id.text2 });

		setListAdapter(adapter);

		TextView tvOverallUsage = (TextView) findViewById(R.id.tvOverallUsageValue);
		TextView tvUsageMonthValue = (TextView) findViewById(R.id.tvUsageMonthValue);
		TextView tvUsageYearValue = (TextView) findViewById(R.id.tvUsageYearValue);
		TextView tvUsageLastYearValue = (TextView) findViewById(R.id.tvUsageLastYearValue);

		tvOverallUsage.setText(calculateUsage() + " " + unit);
		tvUsageMonthValue.setText(calculateMonthlyUsage() + " " + unit);
		tvUsageYearValue.setText(calculateYearlyUsage() + " " + unit);
		tvUsageLastYearValue.setText(calculateYearlyUsageLastYear() + " " + unit); 

	}

	@SuppressLint("SimpleDateFormat")
	public void seperateJsonArray(String meterCounts)
	{
		try
		{
			JSONObject jsonObj = new JSONObject(meterCounts);

			JSONArray jArray = jsonObj.getJSONArray("items");

			for (int i = 0; i < jArray.length(); i++)
			{

				JSONObject tempJsonObject = jArray.getJSONObject(i);

				Map<String, String> datum = new HashMap<String, String>(2);
				datum.put("First Line", tempJsonObject.getString("value") + " "
						+ unit);
				String[] tempStrArryDate = tempJsonObject.getString("date")
						.split("T");
				try
				{
					Date tempDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(tempStrArryDate[0]);

					SimpleDateFormat simpleDate = new SimpleDateFormat(
							"dd.MM.yyyy");
					Date date = tempDate;
					datum.put("Second Line", simpleDate.format(date));
				} catch (ParseException e)
				{
					e.printStackTrace();
				}

				data.add(datum);
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public String calculateUsage()
	{
		String result = "";
		float flUsage = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(meterCounts);

			JSONArray jArray = jsonObj.getJSONArray("items");

			if (jArray.length() < 2)
			{
//				flUsage = Float.parseFloat(jArray.getJSONObject(0).getString(
//						"value"));
				result = "N/A";
			} else
			{
				flUsage = Float.parseFloat(jArray.getJSONObject(
						jArray.length() - 1).getString("value"))
						- Float.parseFloat(jArray.getJSONObject(0).getString(
								"value"));
				result = Float.toString(flUsage);
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@SuppressLint("SimpleDateFormat")
	public String calculateMonthlyUsage()
	{
		String result = "";
		float flUsage = 0;
		List<Float> values = new ArrayList<Float>();
		
		// get current month
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		int month = cal.get(Calendar.MONTH);
		
		//get values added current month
		try
		{
			JSONObject jsonObj = new JSONObject(meterCounts);

			JSONArray jArray = jsonObj.getJSONArray("items");

			for (int i = 0; i < jArray.length(); i++)
			{

				JSONObject tempJsonObject = jArray.getJSONObject(i);

				String[] tempStrArryDate = tempJsonObject.getString("date")
						.split("T");
				try
				{
					Date tempDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(tempStrArryDate[0]);

					Date date = tempDate;
					cal.setTime(date);
					
				} catch (ParseException e)
				{
					e.printStackTrace();
				}
				
				if (cal.get(Calendar.MONTH) == month)
				{
					values.add(Float.parseFloat(tempJsonObject.getString("value")));
				}
				
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		if (values.size()<2)
		{
			//flUsage = values.get(0);
			result = "N/A";
		}else{
			flUsage = values.get(values.size()-1) - values.get(0);
			result = Float.toString(flUsage);
		}
		
		
		return result;
	}

	@SuppressLint("SimpleDateFormat")
	public String calculateYearlyUsage()
	{
		String result = "";
		float flUsage = 0;
		List<Float> values = new ArrayList<Float>();
		
		// get current month
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		int year = cal.get(Calendar.YEAR);
		
		//get values added current month
		try
		{
			JSONObject jsonObj = new JSONObject(meterCounts);

			JSONArray jArray = jsonObj.getJSONArray("items");

			for (int i = 0; i < jArray.length(); i++)
			{

				JSONObject tempJsonObject = jArray.getJSONObject(i);

				String[] tempStrArryDate = tempJsonObject.getString("date")
						.split("T");
				try
				{
					Date tempDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(tempStrArryDate[0]);

					Date date = tempDate;
					cal.setTime(date);
					
				} catch (ParseException e)
				{
					e.printStackTrace();
				}
				
				if (cal.get(Calendar.YEAR) == year)
				{
					values.add(Float.parseFloat(tempJsonObject.getString("value")));
				}
				
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		if (values.size()<2)
		{
			//flUsage = values.get(0);
			result = "N/A";
		}else{
			flUsage = values.get(values.size()-1) - values.get(0);
			result = Float.toString(flUsage);
		}
		
		
		return result;
	}
	
	@SuppressLint("SimpleDateFormat")
	public String calculateYearlyUsageLastYear()
	{
		String result;
		float flUsage = 0;
		List<Float> values = new ArrayList<Float>();
		
		// get current month
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		int year = cal.get(Calendar.YEAR)-1;
		
		//get values added current month
		try
		{
			JSONObject jsonObj = new JSONObject(meterCounts);

			JSONArray jArray = jsonObj.getJSONArray("items");

			for (int i = 0; i < jArray.length(); i++)
			{

				JSONObject tempJsonObject = jArray.getJSONObject(i);

				String[] tempStrArryDate = tempJsonObject.getString("date")
						.split("T");
				try
				{
					Date tempDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(tempStrArryDate[0]);

					Date date = tempDate;
					cal.setTime(date);
					
				} catch (ParseException e)
				{
					e.printStackTrace();
				}
				
				if (cal.get(Calendar.YEAR) == year)
				{
					values.add(Float.parseFloat(tempJsonObject.getString("value")));
				}
				
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		if (values.size()<2)
		{
			//flUsage = values.get(0);
			result = "N/A";
		}else{
			flUsage = values.get(values.size()-1) - values.get(0);
			result = Float.toString(flUsage);
		}
		
		
		return result;
	}

}
