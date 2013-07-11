package de.roman.meter;

import java.io.IOException;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

import de.roman.meter.userendpoint.Userendpoint;
import de.roman.meter.userendpoint.model.User;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
	String accountName;

	/**
	 * Credentials object that maintains tokens to send to the backend.
	 */
	GoogleAccountCredential credential;


	Userendpoint service;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// get android account and check if user exists

		

		// Inside your Activity class onCreate method
//		settings = getSharedPreferences("TicTacToeSample", 0);
//		credential = GoogleAccountCredential.usingAudience(this,
//				"server:client_id:279679354439.apps.googleusercontent.com");
//		setAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
//
//		Userendpoint.Builder builder = new Userendpoint.Builder(
//				AndroidHttp.newCompatibleTransport(), new GsonFactory(),
//				credential);
//		service = builder.build();
//
//		if (credential.getSelectedAccountName() != null)
//		{
//			// Already signed in, begin app!
//		} else
//		{
//			// Not signed in, show login window or request an account.
//		}

		// ////////////////////////

		// Create the adapter that will return a fragment for each of the three
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

		new EndpointsTask().execute(getApplicationContext());
	}

	// setAccountName definition
	private void checkForAccount(String email)
	{
		
		
		
		
		
		SharedPreferences.Editor editor = settings.edit();
		//editor.putString(PREF_ACCOUNT_NAME, accountName);
		editor.commit();
		credential.setSelectedAccountName(accountName);
		this.accountName = accountName;
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
			case R.id.action_add_meter:
				Dialog alertDialog = addMeter();
				alertDialog.show();
				return true;
			case R.id.action_add_meter_category:
				Dialog alertDialogCateg = addMeterCategory();
				alertDialogCateg.show();
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
		builder.setView(inflater.inflate(R.layout.dialog_add_meter, null))
				.setPositiveButton(R.string.dialog_add_meter_save,
						new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								// Zähler anlegen und übertragen
								// update listview
							}
						})
				.setNegativeButton(R.string.dialog_add_meter_cancel, null);
		return builder.create();
	}

	// function for building the dialog for adding a meter category
	public Dialog addMeterCategory()
	{
		// Get the layout inflater
		LayoutInflater inflater = this.getLayoutInflater();

		// set layout for Dialog
		final View layout = (inflater.inflate(
				R.layout.dialog_add_meter_category, null));

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.dialog_add_meter_categ_title);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(layout)
				.setPositiveButton(R.string.dialog_add_meter_categ_save,
						new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								EditText edtMeterCategory = (EditText) layout
										.findViewById(R.id.edTMeterCategory);
								String category = edtMeterCategory.getText()
										.toString();
							}
						})
				.setNegativeButton(R.string.dialog_add_meter_categ_cancel, null);
		AlertDialog dialog = builder.create();
		return dialog;
	}

	// checks whether the category or unit already exists
	public boolean checkStringDuplicate(String checkString, int typ)
	{
		boolean alreadyExists = false;
		String[] array;
		switch (typ)
		{
			case 0:
				array = getResources().getStringArray(R.array.categories);
				break;
			case 1:
				array = getResources().getStringArray(R.array.meter_units);
				break;
			default:
				array = new String[]
				{ "default" };
		}

		for (int i = 0; i <= array.length; i++)
		{
			if (array[i].equals(checkString))
			{
				alreadyExists = true;
			}
		}

		return alreadyExists;
	}

	public static void setPreferenceArray(Context context, String key,
			ArrayList<String> values)
	{
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		JSONArray a = new JSONArray();
		for (int i = 0; i < values.size(); i++)
		{
			a.put(values.get(i));
		}
		if (!values.isEmpty())
		{
			editor.putString(key, a.toString());
		} else
		{
			editor.putString(key, null);
		}
		editor.commit();
	}

	public static ArrayList<String> getPreferenceArray(Context context,
			String key)
	{
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		String json = prefs.getString(key, null);
		ArrayList<String> arrayList = new ArrayList<String>();
		if (json != null)
		{
			try
			{
				JSONArray jsonArray = new JSONArray(json);
				for (int i = 0; i < jsonArray.length(); i++)
				{
					String url = jsonArray.optString(i);
					arrayList.add(url);
				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		return arrayList;
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
			args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1);
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
		public static final String ARG_SECTION_NUMBER = "section_number";

		public SectionFragment()
		{
		}

		ListView msgList;
		ArrayList<MeterOverview> details;
		AdapterView.AdapterContextMenuInfo info;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.list_meter_overview,
					container, false);

			msgList = (ListView) rootView.findViewById(R.id.MessageList);

			details = new ArrayList<MeterOverview>();

			// Dummy Einträge
			MeterOverview meter;
			meter = new MeterOverview();
			meter.setIcon(R.drawable.content_new);
			meter.setName("Wasser Ravensburg");
			meter.setSub("5000 " + getString(R.string.einheit_wasser));
			meter.setDate("01.01.2013");
			details.add(meter);

			meter = new MeterOverview();
			meter.setIcon(R.drawable.content_new);
			meter.setName("Wasser Zingerle");
			meter.setSub("1005000 " + getString(R.string.einheit_wasser));
			meter.setDate("01.01.2013");
			details.add(meter);

			meter = new MeterOverview();
			meter.setIcon(R.drawable.content_new);
			meter.setName("Wasser Kirchberg");
			meter.setSub("5000 " + getString(R.string.einheit_wasser));
			meter.setDate("01.01.2013");
			details.add(meter);

			msgList.setAdapter(new CustomAdapter(details, getActivity()));

			// new EndpointsTask().execute(getActivity());

			return rootView;
		}
	}

	public class EndpointsTask extends AsyncTask<Context, Integer, Long>
	{
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
			// try
			// {
			// // UserEntity user = new UserEntity().setName("Silllllli");
			// // //user.setId("3");
			// // //String noteID = new Date().toString();
			// // //user.setName("Roman");
			// // user.setPassword("lkmlkmlsdf");
			// // //user.setEmailAddress("E-Mail Address");
			// // UserEntity result =
			// endpoint.insertUser(user).execute();//insertUser(user).execute();
			// } //catch (IOException e)
			// //{
			// // e.printStackTrace();
			// //}
			return (long) 0;
		}
	}
	
	

}
