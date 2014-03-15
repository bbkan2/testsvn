package ust.combine;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String NOTES_TABLE_NAME = "notes";
	public static final String NOTES_COLUMN_ID = "_id";
	public static final String NOTES_COLUMN_CONTENT = "content";
	public static final String NOTES_COLUMN_TAG_ID = "tag_id";
	public static final String NOTES_COLUMN_FAVORITE = "favorite";
	public static final String NOTES_COLUMN_MODIFY_TIME = "modify_time";
	public static final String NOTES_COLUMN_ALARM = "alarm";

	public static final String TAG_TABLE_NAME = "tag";
	public static final String TAG_COLUMN_ID = "_id";
	public static final String TAG_COLUMN_TAG = "tag_name";
	public static final String TAG_COLUMN_COLOR = "color";
	
	
	private static final String DATABASE_NAME = "not3r.db";
	private static final int DATABASE_VERSION = 34;	
	
	private static final String NOTES_TABLE_CREATE = "CREATE TABLE " + NOTES_TABLE_NAME 
			+ "(" 
			+ NOTES_COLUMN_ID + " integer primary key autoincrement, " 
			+ NOTES_COLUMN_CONTENT + " text not null DEFAULT 'New Notes', " 
			+ NOTES_COLUMN_TAG_ID + " text not null DEFAULT '1', " 
			+ NOTES_COLUMN_FAVORITE + " text not null DEFAULT '0', " 
			+ NOTES_COLUMN_MODIFY_TIME + " text not null DEFAULT '1', " 
			+ NOTES_COLUMN_ALARM + " text not null DEFAULT '1' " 
			+ ");";	

	private static final String TAG_TABLE_CREATE = "CREATE TABLE " + TAG_TABLE_NAME 
			+ "(" 
			+ TAG_COLUMN_ID + " integer primary key autoincrement, " 
			+ TAG_COLUMN_TAG + " text not null, " 
			+ TAG_COLUMN_COLOR + " text not null " 
			+ ");";	

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.w(MySQLiteHelper.class.getName(),
				"CREATE TABLE SQL: " + NOTES_TABLE_CREATE);		
		db.execSQL(NOTES_TABLE_CREATE);
		db.execSQL(TAG_TABLE_CREATE);
		

		
		db.execSQL("INSERT INTO " + NOTES_TABLE_NAME + " VALUES ("
				+ "1, "
				+ "'Taken 2', "
				+ "'comp3111', "
				+ "'Liam Neeson, Famke Janssen and Maggie Grace', "
				+ "'aaaa', "
				+ "'In Istanbul' "
	
				+ ");"
				);
		
		db.execSQL("INSERT INTO " + NOTES_TABLE_NAME + " VALUES ("

				+ "2, "
				+ "'Looper', "
				+ "'abc', "
				+ "'aaaa', "
				+ "'aaaa', "
				+ "'one day le' "

				+ ");"
				);		
		
		

		db.execSQL("INSERT INTO " + NOTES_TABLE_NAME + " VALUES ("
				+ "3, "
				+ "'Frankenweenie', "
				+ "'comp2611', "
				+ "'Winona Ryder, Catherine OHara and Martin Short', "
				+ "'aaaa', "
				+ "'Young Victor conduc' "
				+ ");"
				);
		
		db.execSQL("INSERT INTO " + NOTES_TABLE_NAME + " VALUES ("
				+ "4, "
				+ "'Shrek', "
				+ "'comp3111', "
				+ "'Mike Myers, Eddie Murphy and Cameron Diaz', "
				+ "'aaaa', "
				+ "'An ogre, in order to regain his swamp' "
				+ ");"
				);

	/*	db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
				+ "5, "
				+ "'Shrek2', "
				+ "'comp2611', "
				+ "'Mike Myers, Eddie Murphy and Cameron Diaz', "
				+ "'Princess Fionas parents invite her and Shrek to dinner to celebrate her marriage. If only they knew the newlyweds were both ogres.', "
				+ "'$441.2M'"
				+ ");"
				);


		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
				+ "6, "
				+ "'Spider-Man', "
				+ "'comp3711', "
				+ "'William Steig (book), Andrew Adamson', "
				+ "'When bitten by a genetically modified spider, a nerdy, shy, and awkward high school student gains spider-like abilities that he eventually must use to fight evil as a superhero after tragedy befalls his family.', "
				+ "'$403.7M'"
				+ ");"
				);


		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
				+ "7, "
				+ "'Spider-Man 2', "
				+ "'abc', "
				+ "'Tobey Maguire, Kirsten Dunst and Alfred Molina', "
				+ "'Peter Parker is beset with troubles in his failing personal life as he battles a brilliant scientist named Doctor Otto Octavius, who becomes Doctor Octopus (aka Doc Ock), after an accident causes him to bond psychically with mechanical tentacles that do his bidding.', "
				+ "'$373.5M'"
				+ ");"
				);

		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
				+ "8, "
				+ "'Spider-Man 3', "
				+ "'comp3711', "
				+ "'Tobey Maguire, Kirsten Dunst and Topher Grace', "
				+ "'A strange black entity from another world bonds with Peter Parker and causes inner turmoil as he contends with new villains, temptations, and revenge.', "
				+ "'$336.5M'"
				+ ");"
				);

		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
				+ "9, "
				+ "'Toy Story', "
				+ "'comp3711', "
				+ "'Tom Hanks, Tim Allen and Don Rickles', "
				+ "'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boys room.', "
				+ "'$191.8M'"
				+ ");"
				);



		db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ("
				+ "10, "
				+ "'Toy Story 2', "
				+ "'abc', "
				+ "'Tom Hanks, Tim Allen and Joan Cusack', "
				+ "'When Woody is stolen by a toy collector, Buzz and his friends vow to rescue him, but Woody finds the idea of immortality in a museum tempting.', "
				+ "'$245.9M'"
				+ ");"
				);*/
		db.execSQL("INSERT INTO " + TAG_TABLE_NAME + " VALUES ("

				+ "1, "
				+ "'abc', "
				+ "'#ccc000'"
				+ ");"
				);	
		db.execSQL("INSERT INTO " + TAG_TABLE_NAME + " VALUES ("

				+ "2, "
				+ "'comp3711', "
				+ "'#ccc555'"
				+ ");"
				);
		db.execSQL("INSERT INTO " + TAG_TABLE_NAME + " VALUES ("

				+ "3, "
				+ "'comp2611', "
				+ "'#ccbbb0'"
				+ ");"
				);
		db.execSQL("INSERT INTO " + TAG_TABLE_NAME + " VALUES ("

				+ "4, "
				+ "'comp3111', "
				+ "'#c12340'"
				+ ");"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		
		db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TAG_TABLE_NAME);
		onCreate(db);
	}

}
