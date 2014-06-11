package mis.examples.doodle.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import mis.examples.doodle.database.PollEntryContract.PollEntry;
import mis.examples.doodle.model.Poll;

public class PollDataHelper {

    Context context;

    public PollDataHelper(Context context) {
	this.context = context;
    }

    public boolean addPoll(Poll poll) {
	DoodleDBHelper dbHelper = new DoodleDBHelper(context);
	SQLiteDatabase db = dbHelper.getWritableDatabase();

	ContentValues values = new ContentValues();
	values.put(PollEntry.COLUMN_NAME_ID, poll.getId());
	values.put(PollEntry.COLUMN_NAME_TITLE, poll.getTitle());
	values.put(PollEntry.COLUMN_NAME_DESCRIPTION, poll.getDescription());
	values.put(PollEntry.COLUMN_NAME_DOODLEKEY, poll.getDoodleKey());
	values.put(PollEntry.COLUMN_NAME_DATE, "datetime()");

	try {
	    long newRowId = db.insert(PollEntry.TABLE_NAME, null, values);
	    dbHelper.close();

	    return (newRowId > 0);

	} catch (Exception e) {
	    e.printStackTrace();
	    dbHelper.close();
	    return false;
	}
    }

    public ArrayList<Poll> readPolls() {
	DoodleDBHelper dbHelper = new DoodleDBHelper(context);
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	String sortOrder = PollEntry.COLUMN_NAME_DATE + " DESC";

	Cursor c = db.rawQuery("SELECT * FROM " + PollEntry.TABLE_NAME
		+ " ORDER BY " + sortOrder, null);

	ArrayList<Poll> polls = new ArrayList<Poll>();

	boolean valid = c.moveToFirst();
	while (valid) {
	    Poll p = new Poll();
	    p.setId(c.getString(c
		    .getColumnIndexOrThrow(PollEntry.COLUMN_NAME_ID)));
	    p.setTitle(c.getString(c
		    .getColumnIndexOrThrow(PollEntry.COLUMN_NAME_TITLE)));
	    p.setDescription(c.getString(c
		    .getColumnIndexOrThrow(PollEntry.COLUMN_NAME_DESCRIPTION)));
	    p.setDoodleKey(c.getString(c
		    .getColumnIndexOrThrow(PollEntry.COLUMN_NAME_DOODLEKEY)));

	    polls.add(p);

	    valid = c.moveToNext();
	}

	dbHelper.close();

	return polls;
    }

    public boolean deletePoll(Poll poll) {
	DoodleDBHelper dbHelper = new DoodleDBHelper(context);
	SQLiteDatabase db = dbHelper.getWritableDatabase();

	return db.delete(PollEntry.TABLE_NAME, PollEntry.COLUMN_NAME_ID
		+ " = '" + poll.getId() + "'", null) > 0;
    }
}
