package shu.journal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity implements OnClickListener {
	
	private Button registerButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        registerButton =(Button)findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegister:
            	Intent i = new Intent(this, RegisterActivity.class);
            	startActivity(i);
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
