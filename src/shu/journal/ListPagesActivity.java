package shu.journal;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListPagesActivity extends ListActivity implements OnClickListener{
	StringBuilder sb = new StringBuilder("");
	DBAdapter db = new DBAdapter(this);
	
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int EDIT_ID = Menu.FIRST;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pages);
        db.open();
        db.insertPage("username", "00/00/0000", "entry1");
        db.insertPage("username", "10/00/0000", "entry1");
        db.insertPage("username", "02/00/0000", "entry1");
        db.insertPage("username", "03/00/0000", "entry1");
        fillData();
                
        //getListView returns the current instance of local list view
        registerForContextMenu(getListView());       
    }  
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add(0, DELETE_ID, 0, "Delete");
        //menu.add(0, EDIT_ID, 0, "Edit");
    }
  	
  	public boolean onContextItemSelected(MenuItem item) {
  		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
  	    /*switch(item.getItemId()) {
  	    case DELETE_ID:
  	        db.deletePage(info.id);
  	        fillData();
  	        return true;
  	    case EDIT_ID:
  	    	Intent i = new Intent(this, NewEvent.class);
  	    	NewEvent.action = NewEvent.EDIT_EVENT;
  	    	NewEvent.cursor = db.getTitle(info.id);
  	    	startActivity(i);
  	        return true;
  	    }*/
  	    return super.onContextItemSelected(item);
  	}
  	
  	//Will show selected item from the listview
  	protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //Intent i = new Intent(this, DisplayEvent.class);
	    	//DisplayEvent.cursor = db.getPageById(id);
	    	//startActivity(i);
    }
  	 //Fills the listview with data from the DB
	  private void fillData() {
		Cursor c = db.getAllPages();
      startManagingCursor(c);

     @SuppressWarnings("static-access")
		String[] from = new String[] {db.KEY_DATE,db.KEY_USERNAME};
     
     int[] to = new int[] { R.id.show_date, R.id.show_username,};
      
      //Now create an array adapter and set it to display using our row
	SimpleCursorAdapter events =
      new SimpleCursorAdapter(this, R.layout.list_row, c, from, to,0);
      setListAdapter(events);
  }
	  
	//Options menu
	@Override
  public boolean onCreateOptionsMenu(Menu menu){
  	super.onCreateOptionsMenu(menu);
  	MenuInflater inflater = getMenuInflater();
  	inflater.inflate(R.menu.activity_main, menu);
  	return true;
  }
	
	//Actions for individual menu items
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
  	switch(item.getItemId()){
  	/*case R.id.settings:
  		startActivity(new Intent(this, Settings.class));
  		return true;
  	case R.id.add_event_menu:
  		Intent i = new Intent(this, NewEvent.class);
  		NewEvent.action = NewEvent.ADD_EVENT;
  		startActivity(i);
  		return true;*/
  	}
  	return false;
  }
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	}
}