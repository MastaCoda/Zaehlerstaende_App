package de.roman.meter;

import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;


import de.roman.meter.metrendpoint.Metrendpoint;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class CustomAdapter extends BaseAdapter
{

	private ArrayList<MeterOverview> _data;
	Context _c;
	//private int positionSel;

	CustomAdapter(ArrayList<MeterOverview> data, Context c)
	{
		_data = data;
		_c = c;
	}

	public int getCount()
	{
		return _data.size();
	}

	public Object getItem(int position)
	{
		return _data.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater) _c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_meter_overview_item, null);
		}

		ImageView image = (ImageView) v.findViewById(R.id.capture);
		TextView meterView = (TextView) v.findViewById(R.id.meter);
		TextView countView = (TextView) v.findViewById(R.id.count);
		TextView dateView = (TextView) v.findViewById(R.id.date);

		final MeterOverview meter = _data.get(position);

		image.setImageResource(meter.icon);
		meterView.setText(meter.name);
		countView.setText(meter.count + " " + meter.unit);
		if (meter.date != null)
		{
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd.MM.yyyy");
			Date date = meter.date;
			dateView.setText(simpleDate.format(date));
		}

		image.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{

				// final int selectedid = position;
				// Get the layout inflater
				//meter.userID;
				LayoutInflater inflater = (LayoutInflater) _c
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				AlertDialog.Builder adb = new AlertDialog.Builder(_c);
				adb.setTitle(R.string.dialog_add_count_title);
				adb.setNegativeButton(R.string.dialog_add_count_cancel, null);
				// Inflate and set the layout for the dialog
				// Pass null as the parent view because its going in the dialog
				// layout
				final View view = inflater.inflate(
						R.layout.dialog_add_meter_count, null);

				adb.setView(view);
				adb.setPositiveButton(R.string.dialog_add_count_save,
						new AlertDialog.OnClickListener()
						{
							EditText edtTxtCount = (EditText) view
									.findViewById(R.id.editTextCount);

							public void onClick(DialogInterface dialog,
									int which)
							{
								String strCount = edtTxtCount.getText()
										.toString();
								
								//start asynctask to insert metercount
								new InsertMeterCountTask(meter.meterID, meter.userID, strCount).execute(_c);
							}
						});

				adb.show();
			}
		});

		return v;
	}

	public class InsertMeterCountTask extends AsyncTask<Context, Integer, Long>
	{
		public Long meterId;
		public Long userId;
		public String count;
		public Context c;
		
		public InsertMeterCountTask(Long meterId, Long userId, String count)
		{
			this.meterId = meterId;
			this.userId = userId;
			this.count = count;
		}
		
		protected Long doInBackground(Context...contexts)
		{
			Metrendpoint.Builder endpointBuilder = new Metrendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer()
					{
						public void initialize(HttpRequest httpRequest)
						{
						}
					});
			Metrendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();

			try
			{
				endpoint.insertMeterCountToUser(meterId, userId, count).execute();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			c = contexts[0];
			

			return (long) 0;
		}

		@Override
		protected void  onPostExecute (Long result)  {
			Toast toast = Toast.makeText(c, "Your new meter count has been added.\nPress refresh button to see result", Toast.LENGTH_LONG);
			toast.show();         
		       
		}

	}
}
