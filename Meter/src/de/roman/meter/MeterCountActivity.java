package de.roman.meter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		//ListView lv= (ListView)findViewById(R.layout.);
		
		//get Extras
		homeIntent = getIntent();
		
		unit = homeIntent.getStringExtra("unit");
		meterCounts = homeIntent.getStringExtra("meterCounts");
		setTitle(homeIntent.getStringExtra("type") + " - " + homeIntent.getStringExtra("title"));
		
		seperateJsonArray(meterCounts);
		
		SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2, 
                new String[] {"First Line", "Second Line" }, 
                new int[] {android.R.id.text1, android.R.id.text2 });
		
		setListAdapter(adapter);
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
                datum.put("First Line", tempJsonObject.getString("value") + " " + unit);
                String[] tempStrArryDate = tempJsonObject
						.getString("date").split("T");
				try
				{
					Date tempDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(tempStrArryDate[0]);
					
					SimpleDateFormat simpleDate = new SimpleDateFormat("dd.MM.yyyy");
					Date date = tempDate;
					datum.put("Second Line",simpleDate.format(date));
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
	
}
