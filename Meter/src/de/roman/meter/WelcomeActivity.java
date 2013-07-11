package de.roman.meter;

import java.io.IOException;
import java.util.regex.Pattern;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

import de.roman.meter.userendpoint.Userendpoint;
import de.roman.meter.userendpoint.model.MeterCollection;
import de.roman.meter.userendpoint.model.User;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;

public class WelcomeActivity extends Activity
{
	SharedPreferences settings;

	private String userEmail = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		Pattern emailPattern = Patterns.EMAIL_ADDRESS;
		Account[] accounts = AccountManager.get(this).getAccounts();
		for (Account account : accounts)
		{
			if (emailPattern.matcher(account.name).matches())
			{
				userEmail = account.name;
				break;
			}
		}

		String[] temp = userEmail.split("@");
		userEmail = temp[0];

		new CheckForAccountTask().execute(getApplicationContext());
	}

	public class CheckForAccountTask extends AsyncTask<Context, Integer, User>
	{

		ProgressDialog dialog;

		@Override
		protected void onPreExecute()
		{
			// Setup Progress Dialog
			dialog = new ProgressDialog(WelcomeActivity.this);
			dialog.setCancelable(false);
			dialog.setMessage("waiting for server...");
			dialog.show();
		}

		protected User doInBackground(Context... contexts)
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
			User result = null;
			try
			{

				result = endpoint.getUserByEmail(userEmail).execute();

				if (result == null)
				{
					// insert new user
					User user = new User();
					user.setEmail(userEmail);
					result = endpoint.insertUser(user).execute();
					result = endpoint.getUserByEmail(userEmail).execute();
				}
				
				// save user id
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WelcomeActivity.this);
				prefs.edit().putLong("UserId", result.getId().getId()).commit();
				
				
				// get meter list of user
				MeterCollection meterList = endpoint.getMeterListWithUserId(result.getId().getId()).execute();
				String meters = meterList.toString();
				int i = 0;
				i = i+1;
				
				// dismiss progress dialog
				dialog.dismiss();
				
				// Start up HomeActivity after checking account and insert user if account didn´t exist
				Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
				intent.putExtra("meters", meters);
				startActivity(intent);
				
				// finish it after launching HomeActivity
				finish();

			} catch (IOException e)
			{
				e.printStackTrace();
			}
			return result;
		}
	}
}
