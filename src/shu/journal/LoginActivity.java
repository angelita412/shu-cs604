package shu.journal;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener {

	private Button loginButton;
	private Button registerButton;
	private Button forgotPasswordButton;

	private EditText editUsername;
	private EditText editPassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		loginButton = (Button) findViewById(R.id.btnLogin);
		registerButton =(Button)findViewById(R.id.btnRegister);
		forgotPasswordButton = (Button) findViewById(R.id.btnForgotPassword);

		editUsername = (EditText) findViewById(R.id.editUsername);
		editPassword = (EditText) findViewById(R.id.editPassword);

		loginButton.setOnClickListener(loginClickListener);
		registerButton.setOnClickListener(this);
		forgotPasswordButton.setOnClickListener(this);
	}

	OnClickListener loginClickListener = new OnClickListener() {	
		public void onClick(View v) {
			String username = editUsername.getText().toString();
			String password = editPassword.getText().toString();
			int attempts = 0;

			if(attempts == 3) //lock account out after 3 attempts
			{

			}

			if(username.length() >= 5 && username.length() <= 10 &&
					password.length() >= 6 && password.length() <= 15){
				//check if valid from database
				//showDialog(0);
				//check if locked
				//showDialog(1);
				//else
				//show journalActivity

				Intent i = new Intent(getApplicationContext(), JournalActivity.class);
				startActivity(i);
			}else{
				if(attempts == 3){
					showDialog(1);
				}else{
					showDialog(0);
					attempts++;
				}
			}
		}
	};


	public void onClick(View v) {
		Intent i;

		switch(v.getId()) {
		case R.id.btnRegister:
			i = new Intent(this, RegisterActivity.class);
			startActivity(i);
			break;
		case R.id.btnForgotPassword:
			i = new Intent(this, ResetPasswordSecurityActivity.class);
			startActivity(i);
			break;
		}
	}

	@Override protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id){
		case 0:
			dialog = new AlertDialog.Builder(this)
			.setTitle("Invalid username or password.")
			.setMessage("There was an error validating your account, please try again.")
			.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.create();
			break;
		case 1:
			dialog = new AlertDialog.Builder(this)
			.setTitle("Account locked")
			.setMessage("Your account has been locked after 3 unsuccessful attempts. Try resetting your password or unlocking your account.")
			.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setNeutralButton("reset password",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent i = new Intent(getApplicationContext(), ResetPasswordSecurityActivity.class);
					startActivity(i);
				}
			})
			.setNegativeButton("unlock account",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent i = new Intent(getApplicationContext(), ResetPasswordSecurityActivity.class);
					startActivity(i);
				}
			})
			.create();
			break;
		}

		return dialog;
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//  getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}