package de.roman.meter;


import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	 
    private ArrayList<MeterOverview> _data;
    Context _c;
    private int positionSel;
    
    
    CustomAdapter (ArrayList<MeterOverview> data, Context c){
        _data = data;
        _c = c;
    }
    
   
    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }
    
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
    }
 
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
         View v = convertView;
         if (v == null)
         {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_meter_overview_item, null);
         }
 
           ImageView image = (ImageView) v.findViewById(R.id.capture);
           TextView meterView = (TextView)v.findViewById(R.id.meter);
           TextView countView = (TextView)v.findViewById(R.id.count);
           TextView dateView = (TextView)v.findViewById(R.id.date);
 
           MeterOverview meter = _data.get(position);
           
           final int selectedid = position;
           
           image.setImageResource(meter.icon);
           meterView.setText(meter.from);
           countView.setText(meter.sub);
           dateView.setText(meter.date);
           
           image.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					// Get the layout inflater
					LayoutInflater inflater = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					AlertDialog.Builder adb=new AlertDialog.Builder(_c);
			        adb.setTitle(R.string.dialog_add_count_title);
			        adb.setNegativeButton(R.string.dialog_add_count_cancel, null);
			        // Inflate and set the layout for the dialog
					// Pass null as the parent view because its going in the dialog layout
			        adb.setView(inflater.inflate(R.layout.dialog_add_meter_count, null));
			        adb.setPositiveButton(R.string.dialog_add_count_save, new AlertDialog.OnClickListener() {
			        	public void onClick(DialogInterface dialog, int which) {
			        				
			        		// TODO zählerstand übermitteln
			        		// TODO listView updaten
			        		
			        		}});
			        
			        adb.show();      
				}
           });
                        
        return v;
}

	
}
