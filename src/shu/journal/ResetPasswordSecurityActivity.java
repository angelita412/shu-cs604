package shu.journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.database.Cursor;

public class ResetPasswordSecurityActivity  extends Activity implements OnClickListener {
	
	private EditText editUsername;
	private EditText editPassword;
	private EditText editAnswerOne;
	private EditText editAnswerTwo;
	private EditText editAnswerThree;
	
	private Button resetAccountButton;
	
	DBAdapter dbAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_user_password);
        
		println("Please enter your user name and the current password or answer all three questions");
        
        resetAccountButton = (Button) findViewById(R.id.resetAccountButton);
                
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        
        editAnswerOne = (EditText) findViewById(R.id.answerOne);
        editAnswerTwo = (EditText) findViewById(R.id.answerTwo);
        editAnswerThree = (EditText) findViewById(R.id.answerThree);
        
        resetAccountButton.setOnClickListener(this);
        
        dbAdapter = new DBAdapter(getApplicationContext());
        dbAdapter.open();
    }
    
    private void println(String string) {
		// TODO Auto-generated method stub
		
	}

	//What's this listener do?
    OnClickListener submitListener = new OnClickListener() {
    	public void onClick(View v)
    	{
    		String username = editUsername.getText().toString();
    		String password = editPassword.getText().toString();
    		String answerOne = editAnswerOne.getText().toString();
    		String answerTwo = editAnswerTwo.getText().toString(); 
    		String answerThree = editAnswerThree.getText().toString();
    		
    		Cursor cursor = dbAdapter.getSecQuestions(username);
    		if (cursor != null && cursor.moveToFirst())
    		{
    			String questionOne = cursor.getString(cursor.getColumnIndex(answerOne));
    			String questionTwo = cursor.getString(cursor.getColumnIndex(answerTwo));
    			String questionThree = cursor.getString(cursor.getColumnIndex(answerThree));
    		}
    		
    		Intent i;
    		dbAdapter.close();
    		
        	i = new Intent(/*this, ResetPasswordActivity.class*/);
        	
    		if(username !=null){
    			
    			if(password !=null && (answerOne !=null || answerTwo !=null || answerThree !=null)){
        			if(password != null ){
        				startActivity(i);
        			}else if(answerOne !=null || answerTwo !=null || answerThree !=null){
        				if(dbAdapter.checkSecAnswer(answerOne, answerTwo, answerThree, username))
        	    		{
        			    	startActivity(i);
        	    		}
        			}else{
        				println("The information you enter doesn't match our records.");	
        			}
        		}else{
        			println("You must enter the current password or answer all three questions");
        		}
        			
        		}
    		}

		private void println(String string) {
			// TODO Auto-generated method stub
			
		}
    };
    public void onClick(View v)
    {    	
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
