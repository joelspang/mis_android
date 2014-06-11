package mis.examples.doodle.database;

import android.provider.BaseColumns;

public final class PollEntryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PollEntryContract() {
    }

    /* Schema definition */
    public static abstract class PollEntry implements BaseColumns {
	public static final String TABLE_NAME = "polls";
	public static final String COLUMN_NAME_ID = "id";
	public static final String COLUMN_NAME_TITLE = "title";
	public static final String COLUMN_NAME_DESCRIPTION = "description";
	public static final String COLUMN_NAME_DOODLEKEY = "doodlekey";
	public static final String COLUMN_NAME_DATE = "date";
    }

    /* Queries */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE "
	    + PollEntry.TABLE_NAME + " ( " + PollEntry.COLUMN_NAME_ID
	    + " VARCHAR(32) NOT NULL, " + PollEntry.COLUMN_NAME_TITLE
	    + " VARCHAR(255) NOT NULL, " + PollEntry.COLUMN_NAME_DESCRIPTION
	    + " VARCHAR(255) NOT NULL, " + PollEntry.COLUMN_NAME_DOODLEKEY
	    + " VARCHAR(8) NOT NULL, " + PollEntry.COLUMN_NAME_DATE
	    + " DATETIME NOT NULL" + " )";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
	    + PollEntry.TABLE_NAME;

}
