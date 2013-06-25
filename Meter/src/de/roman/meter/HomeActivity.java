package de.roman.meter;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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
	
	public Dialog addMeterCategory()
	{
		// Get the layout inflater
		LayoutInflater inflater = this.getLayoutInflater();
		
		// set layout for Dialog
		final View layout = (inflater.inflate(R.layout.dialog_add_meter_category, null));
		
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
								// Kategorie hinzufügen
							}
						})
				.setNegativeButton(R.string.dialog_add_meter_categ_cancel, null);
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
			// Return a DummySectionFragment (defined as a static inner class
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
			String[] tabs = res.getStringArray(R.array.tab_names);

			// Show total amount of pages.
			return tabs.length;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			Resources res = getResources();
			String[] tabs = res.getStringArray(R.array.tab_names);

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
			View rootView = inflater.inflate(R.layout.list_meter_overview, container, false);

			msgList = (ListView) rootView.findViewById(R.id.MessageList);

			details = new ArrayList<MeterOverview>();
			
			//Dummy Einträge
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

			return rootView;
		}	
	}

}
