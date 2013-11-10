package com.example.getgoing;

import java.util.ArrayList;

import com.example.getgoing.MainActivity.MyClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Hotel extends ListActivity{
	
	hotclass h1;
	SQLiteDatabase sd1;
	static String city2;
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	Intent intt1=getIntent();
	city2=intt1.getStringExtra("city11");
	
	h1 = new hotclass(this,"Hoteldatabase.db", null,1);
	ArrayList<String> users1=h1.getAllUserList1();
	
	sd1= h1.getWritableDatabase();
	//setListAdapter(new ArrayAdapter<String>(this, R.layout.hotel,R.id.View1,users1));
	//ListView list=getListView();
	//list.setTextFilterEnabled(true);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, users1);
	final ListView myList=getListView();
    myList.setAdapter(adapter);
    
    myList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String str=(myList).getItemAtPosition(arg2).toString();
			String addr=h1.getAllUserList2(str);
			
			
			
			AlertDialog ad = new AlertDialog.Builder(Hotel.this)
			.setMessage("Address is : "+addr )
			.setIcon(R.drawable.logoz)
			.setTitle(str)
			
			.setCancelable(true)
			.create();
			ad.show();
			
			
		}
	});
}	

public class hotclass extends SQLiteOpenHelper
{

	public hotclass(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db1) {
		// TODO Auto-generated method stub
		db1.execSQL("create table hotel(city text, hotelname text, address text, tarriff number)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db1, int arg1, int arg2) {
		// TODO Auto-generated method stub
		onCreate(db1);
		
	}
	
	public ArrayList<String> getAllUserList1()
	{
		ArrayList<String> userlist=new ArrayList<String>();
		SQLiteDatabase db=this.getReadableDatabase();
		String query;
		
		if(city2.compareTo("delhi")==0)
		{
		query="select hotelname from hotel where city='delhi'";
		}
		else
		{query="select hotelname from hotel where city='lucknow'";}
	
		Cursor cursor=db.rawQuery(query,null);
		
		
		if(cursor.moveToFirst())
			{
				do
					{
						String name=cursor.getString(0);
						userlist.add(name);
					
					}
				while(cursor.moveToNext());
			
			
			}
		return userlist;
	}
	 
	public String getAllUserList2(String s1)
	{
		String userlist2=new String();
		SQLiteDatabase db=this.getReadableDatabase();
		String query2,query3;
		String name = null;
		String tar =null,name1=null;
		
		if(city2.compareTo("delhi")==0)
		{
		query2="select address from hotel where hotelname="+"'"+s1+"'";
		query3="select tarriff from hotel where hotelname="+"'"+s1+"'";
		
		}
		else
		{
			query2="select address from hotel where hotelname="+"'"+s1+"'";
			query3="select tarriff from hotel where hotelname="+"'"+s1+"'";
			
		}
		Cursor cursor=db.rawQuery(query2,null);
		Cursor cursor1=db.rawQuery(query3, null);
		
		
		if(cursor.moveToFirst())
			{
				do
					{
						name=cursor.getString(0);
												
					}
				while(cursor.moveToNext());
			
			
			}

		if(cursor1.moveToFirst())
			{
				do
					{
						tar=cursor1.getString(0);
												
					}
				while(cursor1.moveToNext());
			
			
			}
	
		name1=name+"\nTariff for a 2 bed room : "+tar;
		return name1;
		
	
	
}
	
}
@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}


}
