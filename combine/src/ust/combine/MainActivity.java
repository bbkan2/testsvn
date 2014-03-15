package ust.combine;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity{

	
	// SQLite helper: responsible for create, update and delete database
	private MySQLiteHelper sqlHelper;
	
	// database object that executes query and update to database / tables
	private SQLiteDatabase mydb;
	
	
	// The name of table's column that we wish to retrieve.
	// To save memory consumption, we will only retrieve 
	// those fields that we need in the current activity/view
	private String[] allColumns;	
	
	
	// cursor that points to the movies records that retrieved from movie database table
	private Cursor myCursor;
	
	// CursorAdapter that works as the middle-man 
	// between cursor (data-source) and list views
	private SimpleCursorAdapter dbAdapter;
	private ListView lv;

	private EditText myFilter;
	
	
	private EditText test1;
	private Button b1;
	
	private String[] fromColumns;
	private  int[] toViews;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*
        load activity_main.xml
        the xml file loaded here are expected to have a ListView Object, 
        */
        setContentView(R.layout.activity_main);

        
        
      	// Declaring an ArrayAdapter that will be serving as data source for our list view
        // In the last week's lab, we declared ArrayAdapter as data-source. 
        // They are similar in parameters that are required.
        // Only cursor support multiple data fields while ArrayAdapter is one-dimensional (only one field).
        /*
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
				this,
				R.layout.row, 
				R.id.listItemText,
				brands);
         */				


 
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
		        allColumns, null, null, null, null,  MySQLiteHelper.NOTES_COLUMN_ID +" DESC");

		
        // Specify the columns/fields to retrieve from database table
        fromColumns = new String[]{
        		MySQLiteHelper.NOTES_COLUMN_CONTENT, 
        		MySQLiteHelper.NOTES_COLUMN_TAG_ID 
        		};
        
        // Specify the view id in the row.xml to load the data.  
        // Each table column specified the above fromColumns will corresponds to 
        // a view component below
        toViews = new int[]{
        		R.id.notesContent,
        		R.id.notesTag
        		}; 

        // Creating SimpleCursorAdapter that 
        dbAdapter = new SimpleCursorAdapter(this, 
        		R.layout.row, 
        		myCursor,
                fromColumns, 
                toViews, 
                0);		
		
        // Connect the dbAdapter to the ListView
        myFilter = (EditText) findViewById(R.id.myFilter);
       
         test1 = (EditText) findViewById(R.id.testET);
        
        lv = (ListView) findViewById(R.id.lvID);
		lv.setAdapter(dbAdapter); 
		
		
	//	ViewGroup ll = (ViewGroup)findViewById(R.id.llId);
		myFilter.setVisibility(View.GONE);
		
	//	dbAdapter.notifyDataSetChanged();
		
		dbAdapter.setFilterQueryProvider(new FilterQueryProvider() {
	         public Cursor runQuery(CharSequence constraint) {
	        	 
	        	 
	        	 
	             return fetchName(constraint.toString());
	             
	         }
	     });
		
		 
		  myFilter.addTextChangedListener(new TextWatcher() {
		 
		   public void afterTextChanged(Editable s) {
		   }
		 
		   public void beforeTextChanged(CharSequence s, int start, 
		     int count, int after) {
		   }
		 
		   public void onTextChanged(CharSequence s, int start, 
		     int before, int count) {
			   test1.setText(s.toString());
			//   SimpleCursorAdapter filterAdapter = (SimpleCursorAdapter)lv.getAdapter();
			   dbAdapter.getFilter().filter(s.toString());
		   }
		  });
		  
		    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		        public void onItemClick(AdapterView<?> av, View view, int position, long id) {

		    		Intent intent = new Intent();
		    		intent.setClass(MainActivity.this, ShowNotes.class);
		    		
		    		Bundle bundle = new Bundle();
		    		bundle.putLong("rowId", id);
		    		intent.putExtras(bundle);
		    		
		    		startActivity(intent);
		        }
		    });
		    
		    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		        public boolean onItemLongClick(AdapterView<?> av, View view, int position, long id) {

		        	mydb.delete(MySQLiteHelper.NOTES_TABLE_NAME, "_ID" + "=" + id, null);
		        
		        	myFilter.setText("");

					return true;
		        }
		    });
		  
	//	    lv.getChildAt(int).setBackgroundColor(Color.BLACK);
		    
		    
			test1.setVisibility(View.GONE);
			myFilter.addTextChangedListener(new TextWatcher() {
				 
				   public void afterTextChanged(Editable s) {
				   }
				 
				   public void beforeTextChanged(CharSequence s, int start, 
				     int count, int after) {
				   }
				 
				   public void onTextChanged(CharSequence s, int start, 
				     int before, int count) {
					//   SimpleCursorAdapter filterAdapter = (SimpleCursorAdapter)lv.getAdapter();
					   dbAdapter.getFilter().filter(s.toString());
				   }
				  });
    }
    public void setFilter(View v)
    {
    	myFilter.setText("t");
    }

	
	public Cursor fetchName(String inputText) throws SQLException {
		 
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = mydb.query(MySQLiteHelper.NOTES_TABLE_NAME,
			        allColumns, null, null, null, null, MySQLiteHelper.NOTES_COLUMN_ID +" DESC");
		  }
		  else {
		   mCursor = mydb.query(true, MySQLiteHelper.NOTES_TABLE_NAME, allColumns ,
				   MySQLiteHelper.NOTES_COLUMN_CONTENT + " like '%" + inputText + "%'"+ " OR 'tag: '||" +MySQLiteHelper.NOTES_COLUMN_TAG_ID + " like '" + inputText + "'", null,
		     null, null, MySQLiteHelper.NOTES_COLUMN_ID +" DESC", null);
		  }
		  
		  /*String value = "%"+inputText+"%";

		    return db.query(TABLE_NAME, asColumnsToReturn, "COLUMN_TITLE like ? ", new String[]{value}, null, null, null);*/
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
		 }
	public void test(View v)
	{
		if(myFilter.getVisibility()==View.VISIBLE )
		{
			myFilter.setVisibility(View.GONE);
		}
		else 
		{
		myFilter.setVisibility(View.VISIBLE);
		myFilter.requestFocus();
		}
		
	}


	
	public void add(View v){
    	ContentValues values = new ContentValues();
    	values.put(MySQLiteHelper.NOTES_COLUMN_CONTENT, "TEST_TITLE");
    	values.put(MySQLiteHelper.NOTES_COLUMN_TAG_ID, "TEST_TAG");
    	values.put(MySQLiteHelper.NOTES_COLUMN_FAVORITE, "TEST");
    	values.put(MySQLiteHelper.NOTES_COLUMN_MODIFY_TIME, "TEST");
    	mydb.insert(MySQLiteHelper.NOTES_TABLE_NAME, null, values);
    	
    	
    	myFilter.setText("");

     	// dbAdapter.notifyDataSetChanged();
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
