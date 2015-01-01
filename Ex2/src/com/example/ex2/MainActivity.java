package com.example.ex2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private ListView mLogList;
	private Button mClearLog, mPopup, mDial, mSetNum;
	private TextView mPhoneMunber;
	private static ArrayAdapter<String> adapter = null;
	
	public final int REQUEST_PHONE_NUM = 1;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        //get layout elements
        mLogList = (ListView)findViewById(R.id.list_log);
        mClearLog = (Button)findViewById(R.id.button_clear_log);
        mPopup = (Button)findViewById(R.id.button_popup);
        mDial = (Button)findViewById(R.id.button_dial);
        mSetNum = (Button)findViewById(R.id.button_enter_number);
        mPhoneMunber = (TextView)findViewById(R.id.text_phone_num);
        
        //add functionality to buttons
        mClearLog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clearLog();
			}
		});
        mPopup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDialog();
			}
		});
        mDial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialToNum();
			}
		});
        mSetNum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openPhoneNumActivity();
			}
		});
        
        if (adapter == null) {
        	adapter = new ArrayAdapter<String>(this, R.layout.simple_entry, R.id.text1);
        }
        mLogList.setAdapter(adapter);        
        addLineToLog("onCreate");
    }
    
    
    @Override
	protected void onStart() {
		super.onStart();
		addLineToLog("onStart");
	}


	@Override
	protected void onResume() {
		super.onResume();
		addLineToLog("onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		addLineToLog("onPause");
	}


	@Override
	protected void onStop() {
		super.onStop();
		addLineToLog("onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		addLineToLog("onDestroy");
	}


	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    
    	if (requestCode == REQUEST_PHONE_NUM) {
    		if (resultCode == RESULT_OK) {
    			String num = data.getStringExtra(SetPhoneNumberActivity.RESULT_EXTRA);
    			mPhoneMunber.setText(num);
    		}
    		if (resultCode == RESULT_CANCELED) {
    			mPhoneMunber.setText("");
    		}
    	}
    	
    }
    
    private void openPhoneNumActivity() {
    	Intent intent = new Intent(this, SetPhoneNumberActivity.class);
		startActivityForResult(intent, REQUEST_PHONE_NUM);
    }
    
    private void openDialog() {
    	Intent intent = new Intent(this, CostumDialogActivity.class);
		startActivityForResult(intent, REQUEST_PHONE_NUM);
    }
    
	private void dialToNum() {
		String numToDial = mPhoneMunber.getText().toString();
		if (numToDial.matches("\\d+")) //for simplicity, assume phone number contains only digits
		{
			//TODO start dialing activity
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numToDial));
			startActivity(intent);
		}
		else {
			Toast toast = Toast.makeText(this, "invalid phone number", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
    
    private void addLineToLog(String line) {
    	adapter.add(line);
    	mLogList.setSelection(mLogList.getCount() - 1);
    }
    
    private void clearLog() {
    	adapter.clear();
    }

}
