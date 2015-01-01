package com.example.ex2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SetPhoneNumberActivity extends Activity {
	
	public static final String RESULT_EXTRA = "result";
	
	Button mDoneBtn;
	EditText mPhoneNumberTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_phone_num);
		
		mPhoneNumberTxt = (EditText)findViewById(R.id.phone_number);
		mDoneBtn = (Button)findViewById(R.id.button_phone_num_ok);
		mDoneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mPhoneNumberTxt.getText().length() > 0) {
					Intent returnIntent = new Intent();
					returnIntent.putExtra(RESULT_EXTRA,mPhoneNumberTxt.getText().toString());
					setResult(RESULT_OK,returnIntent);
					finish();
				}
				else {
					Intent returnIntent = new Intent();
					setResult(RESULT_CANCELED, returnIntent);
					finish();
				}
			}
		});
	}
}
