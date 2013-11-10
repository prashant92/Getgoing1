package com.example.getgoing;



import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	
	AutoCompleteTextView at;
	TextView tx1;
	Button bt1;
	SQLiteDatabase sd;
		MyClass cls;
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cls = new MyClass(this, "CityDatabase.db", null, 1);
		sd= cls.getWritableDatabase();
		
		at=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		tx1=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1);
		at.setText("");
		
		ArrayList<String> users=cls.getAllUserList();
		ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,users);
        at.setAdapter(adapter);
        at.setThreshold(1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}

	public class MyClass extends SQLiteOpenHelper 
	{
    
		public MyClass(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub  
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table City(name text)");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			onCreate(db);
			
		}
		
		public ArrayList<String> getAllUserList()
		{
			ArrayList<String> userlist=new ArrayList<String>();
			SQLiteDatabase db=this.getReadableDatabase();
			
			String query="select * from City";
			
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



}
		
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent intt=new Intent(this, option.class);
		
		intt.putExtra("city", at.getText().toString());
		startActivity(intt);
		
	}

	
	
	

	
	
	}




