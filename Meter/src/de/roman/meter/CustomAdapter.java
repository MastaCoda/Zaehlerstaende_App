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

/**
 * Custom Adapter for the meter ListView. An Adapter object acts as a bridge
 * between an AdapterView and the underlying data for that view. The Adapter
 * provides access to the data items. The Adapter is also responsible for making
 * a View for each item in the data set.
 * 
 * @author Roman Schneider
 * 
 */
@SuppressLint("SimpleDateFormat")
public class CustomAdapter extends BaseAdapter
{

    private ArrayList<MeterOverview> _data;
    Context _c;

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
        final TextView countView = (TextView) v.findViewById(R.id.count);
        final TextView dateView = (TextView) v.findViewById(R.id.date);

        final MeterOverview meter = _data.get(position);

        // imagedetail.setImageResource(meter.iconDetail);
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
                // Get the layout inflater
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

                                if (Float.parseFloat(strCount) > Float.MAX_VALUE)
                                {
                                    Toast toast = Toast.makeText(_c,
                                            "Value is too high",
                                            Toast.LENGTH_LONG);
                                    toast.show();

                                } else if (Float.parseFloat(strCount) < Float
                                        .parseFloat(meter.count))
                                {
                                    Toast toast = Toast
                                            .makeText(
                                                    _c,
                                                    "Value can�t be lower than last count!",
                                                    Toast.LENGTH_LONG);
                                    toast.show();
                                } else
                                {
                                    // start asynctask to insert metercount
                                    new InsertMeterCountTask(meter.meterID,
                                            meter.userID, strCount).execute(_c);

                                    countView.setText(strCount + " "
                                            + meter.unit);

                                    SimpleDateFormat simpleDate = new SimpleDateFormat(
                                            "dd.MM.yyyy");
                                    Date date = new Date();
                                    dateView.setText(simpleDate.format(date));
                                }
                            }
                        });

                adb.show();
            }
        });

        return v;
    }

    /**
     * AsyncTask to add a meter count o a specific meter. Makes a toast when
     * finished
     * 
     * @author Roman Schneider
     * 
     */
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

        protected Long doInBackground(Context... contexts)
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
                endpoint.insertMeterCountToUser(meterId, userId, count)
                        .execute();
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
                            "Your new meter count has been added.\nPress refresh button to see result",
                            Toast.LENGTH_LONG);
            toast.show();

        }

    }
}
