package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import Donor.Donor;

public class DatabaseHandler extends SQLiteOpenHelper
{
    // Database properties

    // database version number. Shall be updated if Database body has changed
    private static final int DATABASE_VERSION=1;

    // name of the database that we'll be using
    private static final String DATABASE_NAME="DonorManagement";

    // name of the table in the database
    public static final String TABLE_NAME="Donor";

    // Id is the primary key of the database
    public static final String KEY_ID="id";

    // Database has six columns. (Seven with Id)
    // Below are the six columns. Each of this variables will be used to access those columns.
    public static final String KEY_NAME="name";
    public static final String KEY_AGE="age";
    public static final String KEY_PHONE="phone";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_BLOODGROUP="bloodGroup";
    public static final String KEY_LOCATION="location";


    // constructor for the class
    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACT_TABLE="CREATE TABLE "+ TABLE_NAME +"("
                + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME +" TEXT,"
                + KEY_AGE +" INTEGER,"
                + KEY_PHONE + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_BLOODGROUP + " TEXT,"
                + KEY_LOCATION + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public static void setDonor(Donor donor)
    {

    }

    public void addDonor(Donor donor) throws SQLiteException
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(KEY_NAME , donor.getName());
        value.put(KEY_AGE , donor.getAge());
        value.put(KEY_PHONE , donor.getPhone());
        value.put(KEY_PASSWORD , donor.getPassword());
        value.put(KEY_BLOODGROUP , donor.getBloodGroup());
        value.put(KEY_LOCATION , donor.getLocation());

        db.insert(TABLE_NAME , null , value);
        db.close();

    }
}
