package shu.journal;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JournalEntryUpdateActivity extends Activity implements OnClickListener {
	
	TextView dateLabel;
	Button updateEntryButton;
	Button deleteEntryButton;
	EditText journalPage;
	Long user_id;
	static Cursor cursor;
	
	DBAdapter dbAdapter = new DBAdapter(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_page);
        dbAdapter.open();
        Bundle extras = getIntent().getExtras();
        user_id = extras.getLong(dbAdapter.KEY_USERID);
        
        dateLabel = (TextView)findViewById(R.id.updatePageDate);
        journalPage = (EditText)findViewById(R.id.updateJournalPage);
        updateEntryButton = (Button)findViewById(R.id.updatePage);
        deleteEntryButton = (Button)findViewById(R.id.deletePage);

        updateEntryButton.setOnClickListener(this);
        deleteEntryButton.setOnClickListener(this);     
        dateLabel.setText("Written in " + cursor.getString(1));
        journalPage.setText(cursor.getString(2));
    }
    
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.updatePage:
            	dbAdapter.open();
            	dbAdapter.updatePage(cursor.getLong(0),""+journalPage.getText());
            	dbAdapter.close();
            	finish();
            break;
            case R.id.deletePage:
            	dbAdapter.deletePage(cursor.getLong(0));
            	finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
    	
        return true;
    }
}
