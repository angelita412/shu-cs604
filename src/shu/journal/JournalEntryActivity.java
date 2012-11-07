package shu.journal;

import java.util.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JournalEntryActivity extends Activity implements OnClickListener {
	
	TextView dateLabel;
	Button addEntryButton;
	Button cancelEntryButton;
	EditText journalPage;
	Long user_id;
	
	DBAdapter db = new DBAdapter(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);
        
        
        dateLabel = (TextView)findViewById(R.id.pageDate);
        journalPage = (EditText)findViewById(R.id.journalPage);
        addEntryButton = (Button)findViewById(R.id.addPage);
        cancelEntryButton = (Button)findViewById(R.id.cancelPage);

        addEntryButton.setOnClickListener(this);
        cancelEntryButton.setOnClickListener(this);     
        getTimeDate();
    }
    
    public void getTimeDate() {
    	Date date = new Date();
    	String strDate = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
    	dateLabel.setText("Today is " + strDate);
	}
    
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addPage:
            	db.open();
            	db.insertPage(user_id,""+journalPage.getText());
            	db.close();
            break;
            case R.id.cancelPage:
            	db.open();
            	finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
    	
        return true;
    }
}
