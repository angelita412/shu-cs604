package shu.journal;

import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ResetPasswordActivity  extends Activity implements OnClickListener {
	
	private EditText editPassword;
	private EditText editConfirmPassword;
	private Text username;
	
	private Button updatePasswordButton;
	
	DBAdapter dbAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password);
        
        updatePasswordButton = (Button) findViewById(R.id.updatePasswordButton);

        editPassword = (EditText) findViewById(R.id.password);
        editConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        updatePasswordButton.setOnClickListener(this);
        dbAdapter = new DBAdapter(getApplicationContext());
        dbAdapter.open();
    }
    
    //What's this listener do?
    OnClickListener submitListener = new OnClickListener() {
    	public void onClick(View v)
    	{
    		String password = editPassword.getText().toString();
    		String confirm_password = editConfirmPassword.getText().toString();
    		if(password.equals(confirm_password)){
    			dbAdapter.updatePassword(Integer.parseInt(dbAdapter.KEY_USERID),confirm_password);
    		}
        }
    };
    
    
    public void onClick(View v)
    {    	
    	//return true;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
