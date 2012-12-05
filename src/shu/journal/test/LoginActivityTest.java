package shu.journal.test;

import shu.journal.DBAdapter;
import shu.journal.LoginActivity;
import shu.journal.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class LoginActivityTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {
	
	private int loginAttempts;
	private EditText username;
	private EditText password;
	private DBAdapter dbadapter;
	
	public LoginActivityTest() {
		super("shu.journal", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		LoginActivity loginActivity = getActivity();
		
		loginAttempts = 0;
		
		username = (EditText) loginActivity.findViewById(R.id.editUsername);
		password = (EditText) loginActivity.findViewById(R.id.editPassword);
		dbadapter = new DBAdapter(loginActivity.getBaseContext());//(getApplicationContext());
		dbadapter.open();
	}

	public void testLogin()
	{
		String usernameInput = "A B C D DPAD_DOWN ";
		String passwordInput = "Q W E R T Y U I O P DPAD_DOWN ";
		
		sendKeys("DPAD_CENTER");
		sendKeys(usernameInput);
		sendKeys(passwordInput);
		sendKeys("ENTER");
		
		boolean loginIsSuccessful;
		
		if ( dbadapter.checkPassword(username.getText().toString(), password.getText().toString()) == -1)
		{
			loginIsSuccessful = false;
		}
		else
		{
			loginIsSuccessful = true;
		}
		
		assertTrue("Login successful", loginIsSuccessful );
	}

	/*
	public void testLoginAttempts()
	{
		
	}*/
}
