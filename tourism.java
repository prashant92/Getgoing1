package com.example.getgoing;

import java.util.ArrayList;

import com.example.getgoing.restaurant.restclass;

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

public class tourism extends ListActivity {

	tourclass t1;
	SQLiteDatabase sd3;
	static String city2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intt1=getIntent();
		city2=intt1.getStringExtra("city11");
		
		t1 = new tourclass(this,"tourdatabase.db", null,1);
		ArrayList<String> users1=t1.getAllUserList1();
		sd3= t1.getWritableDatabase();
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.tourism,R.id.View4,users1));
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
				String addr=t1.getAllUserList2(str);
				
				
				
				AlertDialog ad = new AlertDialog.Builder(tourism.this)
				.setMessage("Address is : "+addr )
				.setIcon(R.drawable.logoz)
				.setTitle(str)
				
				.setCancelable(true)
				.create();
				ad.show();
				
				
			}
		});
	}
	
	
	
	public class tourclass extends SQLiteOpenHelper
	{

		public tourclass(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db3) {
			// TODO Auto-generated method stub
			db3.execSQL("create table tour(city text, place text, address text,timing text,entryfee integer)");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db3, int arg1, int arg2) {
			// TODO Auto-generated method stub
			onCreate(db3);
		}
		public ArrayList<String> getAllUserList1()
		{
			ArrayList<String> userlist=new ArrayList<String>();
			SQLiteDatabase db=this.getReadableDatabase();
			String query;
			
			if(city2.compareTo("delhi")==0)
			{
			query="select place from tour where city='delhi'";
			}
			else
			{query="select place from tour where city='lucknow'";}
		
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
			String query2,query3,query4;
			String name = null;
			String timing =null,entryfee=null,name1=null;
			
			if(city2.compareTo("delhi")==0)
			{
			query2="select address from tour where place="+"'"+s1+"'";
			query3="select timing from tour where place="+"'"+s1+"'";
			query4="select entryfee from tour where place="+"'"+s1+"'";
			
			}
			else
			{
				query2="select address from tour where place="+"'"+s1+"'";
				query3="select timing from tour where place="+"'"+s1+"'";
				query4="select entryfee from tour where place="+"'"+s1+"'";
				
			}
			Cursor cursor=db.rawQuery(query2,null);
			Cursor cursor1=db.rawQuery(query3, null);
			Cursor cursor2=db.rawQuery(query4, null);
			
			
			
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
							timing=cursor1.getString(0);
													
						}
					while(cursor1.moveToNext());
				
				
				}
			if(cursor2.moveToFirst())
			{
				do
					{
						entryfee=cursor2.getString(0);
												
					}
				while(cursor2.moveToNext());
			
			
			}
		
			name1=name+"\nTiming is : "+timing+"\nEntry fee : "+entryfee;
			return name1;
			
		
		
	}
		
		
	}
	
}
	


