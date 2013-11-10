package com.example.getgoing;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class option extends Activity implements OnClickListener {
	
	TextView tx1;
	Button bt1;
	Button bt2;
	Button bt3;
	static String city1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option);
		
		tx1=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1);
		bt2=(Button) findViewById(R.id.button2);
		bt3=(Button) findViewById(R.id.button3);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		
		Intent intt=getIntent();
		city1=intt.getStringExtra("city");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		if(v==bt2)
		{
		Intent intt1= new Intent(this,Hotel.class);
		intt1.putExtra("city11", city1);
		startActivity(intt1);
	}
		else if(v==bt3)
		{
		Intent intt1= new Intent(this,restaurant.class);
		intt1.putExtra("city11", city1);
		startActivity(intt1);
	}
		else if(v==bt1)
		{
			Intent intt1=new Intent(this,tourism.class);
			intt1.putExtra("city11", city1);
			startActivity(intt1);
		}
		}

	
}
