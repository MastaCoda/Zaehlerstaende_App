package de.roman.meter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

import de.roman.meter.metrendpoint.Metrendpoint;
import de.roman.meter.metrendpoint.model.MeterCountCollection;
import de.roman.meter.userendpoint.Userendpoint;
import de.roman.meter.userendpoint.model.MeterCollection;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("SimpleDateFormat")
public class HomeActivity extends FragmentActivity implements
		ActionBar.TabListener
{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	/**
	 * Preference object where the app stores the name of the preferred user.
	 */
	SharedPreferences settings;
	Long userId;

	String accountName;

	Intent welcomeIntent;
	public String metersJson;

	Userendpoint service;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// get saved User Id
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		userId = settings.getLong("UserId", 0);

		welcomeIntent = getIntent();
		metersJson = welcomeIntent.getStringExtra("meters");

		// Create the adapter that will return a fragment for each of the
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
				{
					@Override
					public void onPageSelected(int position)
					{
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++)
		{
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.add:
				Dialog alertDialog = addMeter();
				alertDialog.show();
				return true;
			case R.id.info:
				Dialog alertDialogCateg = infoDialog();
				alertDialogCateg.show();
				return true;
			case R.id.refresh:
				int index = mViewPager.getCurrentItem();
				Fragment fragment = mSectionsPagerAdapter.getItem(index);

//				Bundle args = new Bundle();
//				args.putString("meters", metersJson);
//				fragment.setArguments(args);
//				
//				fragment.on
				new RefreshTask().execute(this);

				int x = 0;
				x = x + 1;
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
	}

	// function for building the dialog for adding a meter
	public Dialog addMeter()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Get the layout inflater
		LayoutInflater inflater = this.getLayoutInflater();
		builder.setTitle(R.string.dialog_title);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		final View view = inflater.inflate(R.layout.dialog_add_meter, null);
		builder.setView(view);

		final Spinner spinCat = (Spinner) view.findViewById(R.id.spinCategory);
		final EditText edtTxtName = (EditText) view
				.findViewById(R.id.edTMeterName);
		final Spinner spinUnit = (Spinner) view.findViewById(R.id.spinUnit);
		final EditText edtTxtCmnt = (EditText) view
				.findViewById(R.id.edTComment);

		builder.setPositiveButton(R.string.dialog_add_meter_save,
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id)
					{

						// get data from dialog elements
						String strCateg = spinCat.getSelectedItem().toString();
						String strName = edtTxtName.getText().toString();
						String strUnit = spinUnit.getSelectedItem().toString();
						String strCmnt = edtTxtCmnt.getText().toString();

						new InsertMeterTask(strName, strCmnt, strCateg,
								strUnit, userId)
								.execute(getApplicationContext());
					}
				}).setNegativeButton(R.string.dialog_add_meter_cancel, null);
		return builder.create();
	}

	// function for building the dialog for info
	public Dialog infoDialog()
	{
		// Get the layout inflater
		LayoutInflater inflater = this.getLayoutInflater();

		// set layout for Dialog
		final View layout = (inflater.inflate(R.layout.dialog_info, null));

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.dialog_info_title);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(layout).setNegativeButton(R.string.dialog_info_cancel,
				null);
		AlertDialog dialog = builder.create();
		return dialog;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{

		public SectionsPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			// getItem is called to instantiate the fragment for the given page.
			// Return a SectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new SectionFragment();
			Bundle args = new Bundle();
			String title = getPageTitle(position).toString();
			args.putString("meters", metersJson);
			args.putString(SectionFragment.ARG_SECTION_TITLE, title);
			// args.putInt(SectionFragment.ARG_SECTION_TITLE, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount()
		{
			Resources res = getResources();
			String[] tabs = res.getStringArray(R.array.categories);
			// /getPreferenceArray(HomeActivity.this, "bla");

			// Show total amount of pages.
			return tabs.length;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			Resources res = getResources();
			String[] tabs = res.getStringArray(R.array.categories);

			return tabs[position];
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class SectionFragment extends Fragment
	{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_TITLE = "section_title";

		public SectionFragment()
		{
		}

		String strJArrayStrom;
		String strJArrayWasser;
		String strJArrayGas;
		String strJArrayOel;

		String meterJson;
		public static String meterJsonNew;

		ListView meterListView;
		ArrayList<MeterOverview> meterList;

		// AdapterView.AdapterContextMenuInfo info;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.list_meter_overview,
					container, false);

			meterJson = (getArguments().getString("meters"));

			seperateJsonArray(rootView, meterJson);

			return rootView;
		}

		public void seperateJsonArray(View rootView, String meterJson)
		{
			try
			{
				JSONObject jsonObj = new JSONObject(meterJson);

				JSONArray jArray = jsonObj.getJSONArray("items");
				JSONArray jArrayStrom = new JSONArray();
				JSONArray jArrayGas = new JSONArray();
				JSONArray jArrayOEL = new JSONArray();
				JSONArray jArrayWASSER = new JSONArray();

				for (int i = 0; i < jArray.length(); i++)
				{

					JSONObject tempJsonObject = jArray.getJSONObject(i);
					// Pulling items to seperate arrays
					if (tempJsonObject.getString("type").equals("STROM"))
					{
						jArrayStrom.put(tempJsonObject);
					} else if (tempJsonObject.getString("type").equals("GAS"))
					{
						jArrayGas.put(tempJsonObject);
					} else if (tempJsonObject.getString("type")
							.equals("WASSER"))
					{
						jArrayWASSER.put(tempJsonObject);
					} else if (tempJsonObject.getString("type").equals("OEL"))
					{
						jArrayOEL.put(tempJsonObject);
					}
				}
				strJArrayStrom = jArrayStrom.toString();
				strJArrayWasser = jArrayWASSER.toString();
				strJArrayGas = jArrayGas.toString();
				strJArrayOel = jArrayOEL.toString();

			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			// get objects to fill listview
			meterListView = (ListView) rootView.findViewById(R.id.MeterList);

			meterList = new ArrayList<MeterOverview>();

			Log.d("ARG", getArguments().getString(ARG_SECTION_TITLE));

			if (getArguments().getString(ARG_SECTION_TITLE).equals(
					MeterTypes.STROM.name()))
			{
				setListEntries(strJArrayStrom);

			} else if (getArguments().getString(ARG_SECTION_TITLE).equals(
					MeterTypes.WASSER.name()))
			{
				setListEntries(strJArrayWasser);

			} else if (getArguments().getString(ARG_SECTION_TITLE).equals(
					MeterTypes.GAS.name()))
			{
				setListEntries(strJArrayGas);

			} else if (getArguments().getString(ARG_SECTION_TITLE).equals(
					MeterTypes.OEL.name()))
			{
				setListEntries(strJArrayOel);

			}

			meterListView
					.setAdapter(new CustomAdapter(meterList, getActivity()));

			
			meterListView.setOnItemClickListener(new OnItemClickListener() {
				   public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					   Long lngUserId = meterList.get(position).userID;
					   String strName = meterList.get(position).name;
					   String strUnit = meterList.get(position).unit;
					   Long meterId = meterList.get(position).meterID;
					   int s = position;
					   View b = v;
					   long di = id;
					   
//					   HomeActivity activity = new HomeActivity();
//					   HomeActivity.MeterCountTask meterCountTask = activity.new MeterCountTask(meterId, lngUserId);
//					   meterCountTask.execute((Integer)null);
					   
					   
					   //new MeterCountTask(meterId, lngUserId).execute((Integer) null);
					   
					   //meterCountTask = new MeterCountTask(meterId).execute();
				   }
		   });
		}

		public void setListEntries(String strJArrayMeterType)
		{
			try
			{
				JSONArray jArray = new JSONArray(strJArrayMeterType);

				for (int i = 0; i < jArray.length(); i++)
				{
					JSONObject tempJsonObject = jArray.getJSONObject(i);

					MeterOverview meter;
					meter = new MeterOverview();
					meter.setIcon(R.drawable.content_new);
					meter.setName(tempJsonObject.getString("name"));
					MeterUnits unit = MeterUnits.valueOf(tempJsonObject
							.getString("unit"));
					meter.setUnit(unit.toString());
					meter.setCount(tempJsonObject.getString("lastCount"));
					if (tempJsonObject.has("lastCountDate"))
					{
						try
						{
							String[] tempStrArryDate = tempJsonObject
									.getString("lastCountDate").split("T");
							Date tempDate = new SimpleDateFormat("yyyy-MM-dd")
									.parse(tempStrArryDate[0]);
							meter.setDate(tempDate);
						} catch (ParseException e)
						{
							e.printStackTrace();
						}
					}

					JSONObject keyObject = tempJsonObject.getJSONObject("key");
					meter.setMeterID(keyObject.getLong("id"));
					JSONObject parentKeyObect = keyObject
							.getJSONObject("parent");
					meter.setUserID(parentKeyObect.getLong("id"));

					meterList.add(meter);
				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

	public class InsertMeterTask extends AsyncTask<Context, Integer, Long>
	{
		public String meterName;
		public String meterComment;
		public String meterType;
		public String unit;
		public Long userId;
		public Context c;

		public InsertMeterTask(String meterName, String meterComment,
				String meterType, String unit, Long userId)
		{
			this.meterName = meterName;
			this.meterComment = meterComment;
			this.meterType = meterType;
			this.unit = unit;
			this.userId = userId;
		}

		protected Long doInBackground(Context... contexts)
		{
			Userendpoint.Builder endpointBuilder = new Userendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer()
					{
						public void initialize(HttpRequest httpRequest)
						{
						}
					});
			Userendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();

			try
			{
				endpoint.insertMeterToUser(meterComment, meterName, meterType,
						unit, userId).execute();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			c = contexts[0];

			return (long) 0;
		}

		@Override
		protected void onPostExecute(Long result)
		{
			Toast toast = Toast
					.makeText(
							c,
							"Your new meter has been added.\nPress refresh button to see result",
							Toast.LENGTH_LONG);
			toast.show();

		}

	}

	public class RefreshTask extends AsyncTask<Context, Integer, String>
	{

		ProgressDialog dialog;
		String meters;

		@Override
		protected void onPreExecute()
		{
			// Setup Progress Dialog
			dialog = new ProgressDialog(HomeActivity.this);
			dialog.setCancelable(false);
			dialog.setMessage("Refreshing data!\nPlease wait...");
			dialog.show();
		}

		protected String doInBackground(Context... contexts)
		{
			Userendpoint.Builder endpointBuilder = new Userendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer()
					{
						public void initialize(HttpRequest httpRequest)
						{
						}
					});
			Userendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();
			try
			{

				// get meter list of user
				MeterCollection meterList = endpoint.getMeterListWithUserId(
						userId).execute();
				meters = meterList.toString();

				// dismiss progress dialog
				dialog.dismiss();

			} catch (IOException e)
			{
				e.printStackTrace();
			}
			return meters;
		}

		@Override
		protected void onPostExecute(String meters)
		{
			metersJson = meters;

		}
	}
	
	public class MeterCountTask extends AsyncTask<Integer, Integer, String>
	{

		ProgressDialog dialog;
		String metersCounts;
		Long meterId;
		Long lngUserId;
		
		public MeterCountTask(Long meterId, Long lngUserId)
		{
			this.meterId = meterId;
			this.lngUserId = lngUserId;
		}

		@Override
		protected void onPreExecute()
		{
			// Setup Progress Dialog
//			dialog = new ProgressDialog(get);
//			dialog.setCancelable(false);
//			dialog.setMessage("Loading MeterCounts!\nPlease wait...");
//			dialog.show();
		}

		protected String doInBackground(Integer... contexts)
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

				// get meter list of user
				MeterCountCollection meterCountList = endpoint.getMeterCountListWithUserId(meterId, lngUserId).execute();
				metersCounts = meterCountList.toString();

				

			} catch (IOException e)
			{
				e.printStackTrace();
			}
			return metersCounts;
		}

		@Override
		protected void onPostExecute(String meterCounts)
		{
			// dismiss progress dialog
			dialog.dismiss();

		}
	}
	
	

}
