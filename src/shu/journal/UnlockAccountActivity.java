package shu.journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.res.Resources;
import android.database.Cursor;

public class UnlockAccountActivity extends Activity implements OnClickListener {
	
	private EditText editUsername;
	private EditText editAnswerOne;
	private EditText editAnswerTwo;
	private EditText editAnswerThree;
	
	private Spinner securityQuestions;
	
	private Button unlockUserButton;
	
	DBAdapter dbAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        
        unlockUserButton = (Button) findViewById(R.id.unlockUserButton);
                
        editUsername = (EditText) findViewById(R.id.username);
        
        editAnswerOne = (EditText) findViewById(R.id.answerOne);
        editAnswerTwo = (EditText) findViewById(R.id.answerTwo);
        editAnswerThree = (EditText) findViewById(R.id.answerThree);
        
        
        securityQuestions = (Spinner) findViewById(R.id.security_question_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.securityQuestions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        securityQuestions.setAdapter(adapter);
        
        dbAdapter = new DBAdapter(getApplicationContext());
        dbAdapter.open();
    }
    
    //What's this listener do?
    OnClickListener submitListener = new OnClickListener() {
    	public void onClick(View v)
    	{
    		//getDataForFields();
    	}
    };
    
    private void getDataForFields()
    {
		String username = editUsername.getText().toString();
		
		
		String answerOne = editAnswerOne.getText().toString();
		String answerTwo = editAnswerTwo.getText().toString(); 
		String answerThree = editAnswerThree.getText().toString();
		
		Cursor cursor = dbAdapter.getSecQuestions(username);
		if (cursor != null && cursor.moveToFirst())
		{
			String s_question1 = "s_question1";
			String s_question2 = "s_question2";
			String s_question3 = "s_question3";
			
			String questionOne = cursor.getString(cursor.getColumnIndex(s_question1));
			String questionTwo = cursor.getString(cursor.getColumnIndex(s_question2));
			String questionThree = cursor.getString(cursor.getColumnIndex(s_question3));
		}
		
		
		
		if(dbAdapter.checkSecAnswer(answerOne, answerTwo, answerThree, username))
		{
			unlockUserAccount(username);
		}
    }
    
    private void unlockUserAccount(String username)
    {
    	dbAdapter.unlockAccount(username);
    }
    
    public void onClick(View v)
    {    	
    	switch(v.getId())
    	{
    	case R.id.unlockUserButton:
    		getDataForFields();
    		break;
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
