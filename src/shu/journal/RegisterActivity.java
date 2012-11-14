package shu.journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends Activity implements OnClickListener {
	
	private EditText editFirstName;
	private EditText editSurname;
	private EditText editUsername;
	private EditText editPassword;
	private EditText editAnswerOne;
	private EditText editAnswerTwo;
	private EditText editAnswerThree;
	
	private Spinner securityQuestions;
	
	private Button registerUserButton;
	private Button resetFieldsButton;
	
	DBAdapter dbAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        
        registerUserButton = (Button) findViewById(R.id.registerUserButton);
        resetFieldsButton = (Button) findViewById(R.id.resetFieldsButton);
        
        editFirstName = (EditText) findViewById(R.id.first_name);
        editSurname = (EditText) findViewById(R.id.last_name);
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        
        editAnswerOne = (EditText) findViewById(R.id.answerOne);
        editAnswerTwo = (EditText) findViewById(R.id.answerTwo);
        editAnswerThree = (EditText) findViewById(R.id.answerThree);
        
        
        securityQuestions = (Spinner) findViewById(R.id.security_question_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.id.security_question_spinner, android.R.layout.simple_spinner_item);
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
    	String firstName = editFirstName.getText().toString();
		String surname = editSurname.getText().toString();
		String username = editUsername.getText().toString();
		String password = editPassword.getText().toString();
		
		String answerOne = editAnswerOne.getText().toString();
		String answerTwo = editAnswerTwo.getText().toString(); 
		String answerThree = editAnswerThree.getText().toString();
		
		String questionOne = securityQuestions.getItemAtPosition(0).toString();
		String questionTwo = securityQuestions.getItemAtPosition(1).toString();
		String questionThree = securityQuestions.getItemAtPosition(2).toString();
		
		dbAdapter.insertUser(username, password, null, firstName, surname, questionOne, questionTwo, questionThree, answerOne, answerTwo, answerThree);
    }
    
    public void onClick(View v)
    {    	
    	switch(v.getId())
    	{
    	case R.id.registerUserButton:
    		getDataForFields();
    		break;
    	case R.id.resetFieldsButton:
    		editFirstName.setText("");
    		editFirstName.setText("");
    		editSurname.setText("");
    		editUsername.setText("");
    		editPassword.setText("");
    		editAnswerOne.setText("");
    		editAnswerTwo.setText("");
    		editAnswerThree.setText("");
    		break;
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
