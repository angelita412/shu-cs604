package shu.journal;

import java.sql.Date;
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
	
	String current_date;
	TextView dateLabel;
	Button addEntryButton;
	Button cancelEntryButton;
	EditText journalPage;
	
	DBAdapter db = new DBAdapter(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);
        getTimeDate();
        
        dateLabel = (TextView)findViewById(R.id.pageDate);
        journalPage = (EditText)findViewById(R.id.journalPage);
        addEntryButton = (Button)findViewById(R.id.addPage);
        cancelEntryButton = (Button)findViewById(R.id.cancelPage);

        addEntryButton.setOnClickListener(this);
        cancelEntryButton.setOnClickListener(this);
        
       
    }
    
    public void getTimeDate() {
    	Time today = new Time(Time.getCurrentTimezone());
    	today.setToNow();
    	current_date = today.monthDay+"/"+today.month+"/"+today.year;
    	dateLabel.setText("Today is " + current_date);
	}
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addPage:
            	db.open();
            	db.insertPage("username", current_date,""+journalPage.getText());
            	db.close();
            	
            break;
            case R.id.cancelPage:
            	journalPage.setText("");
            	Intent i = new Intent(this, ListPagesActivity.class);
            	startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
    	
        return true;
    }
}
