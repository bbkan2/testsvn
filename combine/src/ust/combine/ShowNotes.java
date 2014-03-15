package ust.combine;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ShowNotes extends Activity {

	// SQLite helper: responsible for create, update and delete database
	private MySQLiteHelper sqlHelper;
	
	// database object that executer query and update to database / table
	private SQLiteDatabase mydb;
	
	// The name of table's column that we wish to retrieve.
	// To save memory consumption, we will only retrieve 
	// those fields that we need in the current activity/view
	private String[] allColumns;	

	// cursor that points to the movies records that retrieved from movie database table
	private Cursor myCursor;

	private EditText content;
	
	private long rowId;
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        //Set the "Back" Button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
        //Getting the bundle information. 
        //From which, we will know which movie to display details information
        Bundle bundle = this.getIntent().getExtras();
        rowId = bundle.getLong("rowId");

		// We specify only those table fields/columns that we need in this activity / view.
		allColumns = new String[] { 
				  MySQLiteHelper.NOTES_COLUMN_ID,
	        		MySQLiteHelper.NOTES_COLUMN_CONTENT, 
	        		MySQLiteHelper.NOTES_COLUMN_TAG_ID, 
	        		MySQLiteHelper.NOTES_COLUMN_FAVORITE,
	        		MySQLiteHelper.NOTES_COLUMN_MODIFY_TIME,
	        		MySQLiteHelper.NOTES_COLUMN_ALARM
			      };	
			
		
		//Pass "this" as the context
		sqlHelper = new MySQLiteHelper(this); 
		
		// Get a SQLiteDatabase object so that we can run database query 
		mydb = sqlHelper.getWritableDatabase();
		
		// Calling query method to retrieve records (table rows) cursor.
		myCursor = mydb.query(MySQLiteHelper.NOTES_TABLE_NAME,
		        allColumns, "_ID="+String.valueOf(rowId), null, null, null, null);     
		
		// Get a reference to the UI component of activity_show_movie.xml
		/*TextView movieTitle = (TextView) findViewById(R.id.showMovieTitle);
		TextView movieGross = (TextView) findViewById(R.id.showMovieGross);
		TextView movieDirector = (TextView) findViewById(R.id.showMovieDirector);
		TextView movieCast = (TextView) findViewById(R.id.showMovieCast);
		TextView movieStory = (TextView) findViewById(R.id.showMovieStory);*/
		
		
		content =(EditText)findViewById(R.id.contentID);

	//	int getID;
		myCursor.moveToFirst();
		if (!myCursor.isAfterLast()) {
			//Set the activity screen title
		//	getID = myCursor.getInt(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_ID));
			
				content.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.NOTES_COLUMN_CONTENT)));

				setTitle(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.NOTES_COLUMN_CONTENT)));

			/*
			
			movieTitle.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_TITLE)));
			movieGross.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_GROSS)));
			movieDirector.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_DIRECTOR)));
			movieCast.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_CAST)));
			movieStory.setText(myCursor.getString(myCursor.getColumnIndex(MySQLiteHelper.COLUMN_STORY)));*/			
		}	
	//	content.setFreezesText(true);
		content.requestFocus();
	//	content.setCursorVisible(false);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show_notes, menu);
        return true;
    }
    
	public void update(){
    	ContentValues values = new ContentValues();
    	values.put(MySQLiteHelper.NOTES_COLUMN_CONTENT, content.getText().toString());
    	mydb.update(MySQLiteHelper.NOTES_TABLE_NAME, values, "_id "+"="+String.valueOf(rowId) ,null);
    	

    // 	dbAdapter.notifyDataSetChanged();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	update();
                NavUtils.navigateUpFromSameTask(this);
                
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
