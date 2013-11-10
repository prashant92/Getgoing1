package com.example.getgoing;
import java.util.ArrayList;
import com.example.getgoing.Hotel.hotclass;
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
public class restaurant extends ListActivity {
	
	restclass r1;
	SQLiteDatabase sd2;
	static String city2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intt1=getIntent();
		city2=intt1.getStringExtra("city11");
		
		r1 = new restclass(this,"Restdatabase.db", null,1);
		ArrayList<String> users1=r1.getAllUserList1();
		sd2= r1.getWritableDatabase();
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.restaurant,R.id.View3,users1));
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
				int i = myList.getSelectedItemPosition();
	           //begin
				
				
				
				String str=(myList).getItemAtPosition(arg2).toString();
				String addr=r1.getAllUserList2(str);
				
				
				
				AlertDialog ad = new AlertDialog.Builder(restaurant.this)
				.setMessage("Address is "+addr )
				.setIcon(R.drawable.logoz)
				.setTitle(str)
				
				.setCancelable(true)
				.create();
				ad.show();
				
				
			//end here	
			}
		});
	}
	
	public class restclass extends SQLiteOpenHelper
	{
		public restclass(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase db2) {
			// TODO Auto-generated method stub
			db2.execSQL("create table rest(city text, restname text, address text)");
			
		}
		@Override
		public void onUpgrade(SQLiteDatabase db2, int arg1, int arg2) {
			// TODO Auto-generated method stub
			onCreate(db2);
			
		}
		public ArrayList<String> getAllUserList1()
		{
			ArrayList<String> userlist=new ArrayList<String>();
			SQLiteDatabase db=this.getReadableDatabase();
			String query;
			
			if(city2.compareTo("delhi")==0)
			{
			query="select restname from rest where city='delhi'";
			
			}
			else
			{query="select restname from rest where city='lucknow'";}
		
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
			String query2;
			String name = null;
			
			if(city2.compareTo("delhi")==0)
			{
			query2="select address from rest where restname="+"'"+s1+"'";
			
			
			}
			else
			{
				query2="select restname from rest where restname="+"'"+s1+"'";
			}
			Cursor cursor=db.rawQuery(query2,null);
			
			
			if(cursor.moveToFirst())
				{
					do
						{
							name=cursor.getString(0);
													
						}
					while(cursor.moveToNext());
				
				
				}
		
			
			return name;
			
		
		
	}
}
}